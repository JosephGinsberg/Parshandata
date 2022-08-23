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
		String[] values = {"splitBy", "search"};
		AppFunctions.errorChecker(values, json);
		String jsonresult = "{\"ok\":true,\"msg\":\"searched successfully\",\"matches\":[";
		String splitby = json.getString(values[0]);
		JSONArray search = json.getJSONArray(values[1]);
		int len = search.length();
		for(int i = 0; i < len; i++){
			JSONObject current = search.getJSONObject(i);
			if(current.getString("param").equals("input")){
				JSONArray removing = AppFunctions.removeSet(current.getString("value"));
				//System.out.println(removing.getBoolean(0));
				current.put("remove", removing);
				//System.out.println("doing");
				search.put(i, current);
			}
		}
		len = material.length;
		//int total = 0;
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
					String in = list[j];
					String[] searchtrop = {trop[j]};
					if(splitby.equals("pasuk")){
						searchtrop = trop;
					}
					ArrayList<Integer> matches = searching(search, 0, in, place, searchtrop);
					if(matches.size() != 0){
						jsonresult += ",";
						String listvalue = "";
						String v = "";
						if(!splitby.equals("pasuk")){
							listvalue = list[j].replace("׀", " ׀");
							v += j;
						}
						else{
							for(int x : matches){
								v += ", " + x;
							}
							v = v.replaceFirst(", ", "");
						}
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
		//System.out.println("total: " + total);
		return jsonresult.replace("[,{", "[{") + "],";
	}

	public static String[][][] setup(JSONObject json) throws JSONException, FileNotFoundException {
		String[] values = {"taamTachton", "useKeri", "books"};
		AppFunctions.errorChecker(values, json);
		String searchText = "";
		Boolean taam = json.getBoolean(values[0]);
		Boolean keri = json.getBoolean(values[1]);
		JSONArray books = json.getJSONArray(values[2]);
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
		String[] values = {"param", "connector"};
		AppFunctions.errorChecker(values, current);
		String param = current.getString(values[0]);
		String connector = current.getString(values[1]);
		ArrayList<Integer> indexlist = new ArrayList<Integer>();

		if(param.equals("condition")){
			ArrayList<Integer> output = searching(search, index + 1, term, place, trop);
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
			String[] nextvalues = {"count", "counttype", "matchtype", "type"};
			AppFunctions.errorChecker(nextvalues, current);
			int totalcount = current.getInt(nextvalues[0]);
			String counttype = current.getString(nextvalues[1]);
			String matchtype = current.getString(nextvalues[2]);
			String type = current.getString(nextvalues[3]);
			String[][] both = AppFunctions.splitter(type, term, place);
			String[] newterms = both[0];
			String[] nexttrop = both[1];
			int newlen = newterms.length;
			for(int j = 0; j < newlen; j++){
				if(matchtype.equals("ends")){
					j = newterms.length - 1;
				}
				String[] newtrop = {nexttrop[j]};
				if(searching(search, index + 1, newterms[j], place, newtrop).size() != 0){
					indexlist.add(j);
				}
				if(matchtype.equals("begins")){
					j = newterms.length;
				}
			}
			if(matchtype.equals("does not contain")){
				indexlist = AppFunctions.opposite(indexlist);
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
			String[] nextvalues = {"value", "count", "counttype", "matchtype", "remove", "type"};
			AppFunctions.errorChecker(nextvalues, current);
			String val = current.getString(nextvalues[0]);
			int totalcount = current.getInt(nextvalues[1]);
			String counttype = current.getString(nextvalues[2]);
			String matchtype = current.getString(nextvalues[3]);
			JSONArray conditions = current.getJSONArray(nextvalues[4]);
			String type = current.getString(nextvalues[5]);
			Boolean[] array = {conditions.getBoolean(0), conditions.getBoolean(1), conditions.getBoolean(2), conditions.getBoolean(3), conditions.getBoolean(4), conditions.getBoolean(5)};
			String thisterm = otherBibles.choose(term, array);
			indexlist = AppFunctions.finder(thisterm, val, type, matchtype, trop);                           
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