package BibleStatistics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import BibleStatistics.Bible.keriUkesiv;
import BibleStatistics.Bible.maker;
import BibleStatistics.Level_1.bibleLists;
import BibleStatistics.Level_1.otherBibles;
import BibleStatistics.Level_1.tools;

public class App {
	public static void main(String[] args) throws IOException, JSONException{

		String JSON = tools.FileToString("backend/src/main/java/BibleStatistics/test.json");
		String result = searchAll(JSON);

		FileOutputStream out = new FileOutputStream("backend/src/main/java/BibleStatistics/output.json");
		out.write(result.getBytes("UTF-8"));
		out.close();
	}

	public static String searchAll(String JSON) throws JSONException, FileNotFoundException{
		long startTime = System.currentTimeMillis();
		JSONObject json = new JSONObject(JSON);
		String result = mastersearch(setup(json), json);
		result += "\"runtime\": " + (System.currentTimeMillis() - startTime) + "}";
		return result;
	}

	public static String mastersearch(String[][][] material, JSONObject json) throws JSONException, FileNotFoundException {
		String jsonresult = "{\"matches\":[";
		String splitby = json.getString("splitBy");
		JSONArray search = json.getJSONArray("search");
		int len = material.length;
		//int total = 0;
		JSONArray remover = json.getJSONArray("remove");
		int removerlen = remover.length();
		Boolean[] conditions = {true, true, true, true, true, true};
			for(int j = 0; j < removerlen; j++){
				if(remover.getString(j).equals("letter")){
					conditions[0] = false;
				}
				else if(remover.getString(j).equals("nekudah")){
					conditions[1] = false;
				}
				else if(remover.getString(j).equals("trop")){
					conditions[2] = false;
				}
				else if(remover.getString(j).equals("other")){
					conditions[3] = false;
				}
				else if(remover.getString(j).equals("space")){
					conditions[4] = false;
				}
				else if(remover.getString(j).equals("makaf")){
					conditions[5] = false;
				}
			}
		for(int l = 0; l < len; l++){
			String[][] thismaterial = material[l];
			int thismateriallen = thismaterial.length;
			for(int i = 0; i < thismateriallen; i++){
				String thispasuk = thismaterial[i][0];
				String[] place = {thismaterial[i][1], thismaterial[i][2], thismaterial[i][3]};
				//System.out.println(thismaterial[i][1] + " "  + thismaterial[i][2] + ":" + thismaterial[i][3]);
				String[][] both = AppFunctions.splitter(splitby, thispasuk, place);
				String[] list = both[0];
				String[] trop = both[1];
				int listlen = list.length;
				for(int j = 0; j < listlen; j++){
					String in = otherBibles.choose(list[j], conditions);
					//String in = list[j];
					String[] searchtrop = {trop[j]};
					if(splitby.equals("pasuk")){
						searchtrop = trop;
					}
					ArrayList<Integer> matches = searching(search, 0, in, place, searchtrop);
					if(matches.size() != 0){
						String listvalue = "";
						if(!splitby.equals("pasuk")){
							listvalue = in;
						}
						jsonresult += ",";
						String v = "";
						for(int x : matches){
							v += ", " + x;
						}
						v = v.replaceFirst(", ", "");
						jsonresult += new JSONStringer().object()
										.key("splitvalue").value(listvalue)
										.key("indices").value(v)
										.key("fullverse").value(thispasuk)
										.key("bookname").value(thismaterial[i][1])
										.key("perek").value(thismaterial[i][2])
										.key("pasuk").value(thismaterial[i][3])
										.endObject().toString();
						//total++;
					}
				}
			}
		}
		//^CSystem.out.println("total: " + total);
		return jsonresult.replaceFirst(",", "") + "],";
	}

	public static String[][][] setup(JSONObject json) throws JSONException, FileNotFoundException {
		String searchText = "";
		Boolean taam = json.getBoolean("taamTachton");
		Boolean keri = json.getBoolean("useKeri");
		JSONArray books = json.getJSONArray("books");
		int bookslen = books.length();
		String[][][] material = new String[bookslen][][];
		for (int i = 0; i < bookslen; i++) {
			searchText = maker.book_numbers(books.getString(i))[0];
			if(taam){
				searchText = keriUkesiv.tachton(searchText, true);
			}
			else{
				 searchText = keriUkesiv.elyon(searchText, true);
			}
			if(keri){
				searchText = keriUkesiv.keri(searchText);
			}
			else{
				 searchText = keriUkesiv.kesiv(searchText);
			}
			material[i] = bibleLists.numseperator(bibleLists.numpasukim(searchText), books.getString(i));
		}
		return material;
	}

	public static ArrayList<Integer> searching(JSONArray search, int index, String term, String[] place, String[] trop) throws JSONException, FileNotFoundException {
		
		JSONObject current = search.getJSONObject(index);
		String param = current.getString("param");
		String connector = current.getString("connector");
		String type = current.getString("type");
		ArrayList<Integer> indexlist = new ArrayList<Integer>();

		if(param.equals("condition")){
			ArrayList<Integer> output = searching(search, index + 1, term, place, trop);
			if(type.equals("does not contain")){
				output = AppFunctions.opposite(output);
			}
			if(connector.equals("and") || connector.equals("or")){
				int i = AppFunctions.nextFinder(search, index, param);
				return AppFunctions.logics(connector, output, searching(search, i, term, place, trop));
			}
			else if(connector.equals("distance")){
				int i = AppFunctions.nextFinder(search, index, "any");
				JSONObject next = search.getJSONObject(index + 1);
				return AppFunctions.distance(next, indexlist, searching(search, i, term, place, trop));
			}
			else{
				return output;
			}
		}

		else if(param.equals("abstract")){
			int totalcount = current.getInt("count");
			String counttype = current.getString("counttype");
			String[][] both = AppFunctions.splitter(type, term, place);
			String[] newterms = both[0];
			String[] nexttrop = both[1];
			int newlen = newterms.length;
			for(int j = 0; j < newlen; j++){
				String[] newtrop = {nexttrop[j]};
				if(searching(search, index + 1, newterms[j], place, newtrop).size() != 0){
					indexlist.add(j);
				}
			}
			indexlist = AppFunctions.countchecker(indexlist, totalcount, counttype);
			if(connector.equals("and") || connector.equals("or")){
				int i = AppFunctions.nextFinder(search, index, param);
				return AppFunctions.logics(connector, indexlist, searching(search, i, term, place, trop));
			}
			else if(connector.equals("distance")){
				int i = AppFunctions.nextFinder(search, index, param);
				JSONObject next = search.getJSONObject(index + 1);
				return AppFunctions.distance(next, indexlist, searching(search, i, term, place, trop));
			}
			else{
				return indexlist;
			}
		}

		else if(param.equals("distance")){
			if(connector.equals("and") || connector.equals("or")){
				int i = AppFunctions.nextFinder(search, index, "input");
				return AppFunctions.logics(connector, searching(search, index + 1, term, place, trop), searching(search, i, term, place, trop));
			}
			else{
				return searching(search, index + 1, term, place, trop);
			}
		}

		else if(param.equals("input")){
			String val = current.getString("value");
			int totalcount = current.getInt("count");
			String counttype = current.getString("counttype");
			String matchtype = current.getString("matchtype");
			if(type.equals("letter")){
				term = otherBibles.justLetters(term);
			}
			else if(type.equals("nekudah")){
				term = otherBibles.justNekudot(term);
			}
			int termlen = term.length();
			int vallen = val.length();
			if(!type.equals("trop")){
				if(matchtype.equals("contains")){
					for(int i = 0; i <= (termlen - vallen); i++){
						if(term.substring(i, i + vallen).equals(val)){
							indexlist.add(i);
						}
					}
				}
				else if(matchtype.equals("is")){
					if(term.equals(val)){
						indexlist.add(0);
					}
				}
			}
			else{
				int troplen = trop.length;
				for(int i = 0; i < troplen; i++){
					if(trop[i].contains(val)){
						indexlist.add(i);
					}
				}	
			}
			indexlist = AppFunctions.countchecker(indexlist, totalcount, counttype);
			if(connector.equals("and") || connector.equals("or")){
				return AppFunctions.logics(connector, indexlist, searching(search, index + 1, term, place, trop));
			}
			if(connector.equals("distance")){
				JSONObject next = search.getJSONObject(index + 1);
				return AppFunctions.distance(next, indexlist, searching(search, index + 1, term, place, trop));
			}
			else{
				return indexlist;
			}
		}
		return indexlist;
	}
}