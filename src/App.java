import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import Level_1.bibleLists;
import Level_1.otherBibles;
import Level_2.tropLists;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException{
		
		long startTime = System.currentTimeMillis();
		
		File fileJSON = new File("test.json");
		String JSON = Level_1.tools.FileToString(fileJSON);
		JSONObject json = new JSONObject(JSON);

		String result = mastersearch(setup(json), json);
		result += "\"runtime\": " + (System.currentTimeMillis() - startTime) + "}";

		FileOutputStream out = new FileOutputStream("output.json");
		out.write(result.getBytes("UTF-8"));
		out.close();

		/*long execTime = System.currentTimeMillis() - startTime;
		System.out.println(execTime);*/
	}

	public static String mastersearch(String[][][] material, JSONObject json) throws JSONException {
		String jsonresult = "{\"answers\":[";
		String splitby = json.getString("splitBy");
		int len = material.length;
		int total = 0;
		for(int l = 0; l < len; l++){
			String[][] thismaterial = material[l];
			int thismateriallen = thismaterial.length;
			for(int i = 0; i < thismateriallen; i++){
				String thispasuk = thismaterial[i][0];
				String[] trop = tropLists.tropFinder(bibleLists.tropWords(thispasuk));
				String list[] = {thispasuk};
				Boolean value = true;
				if(splitby.equals("word")){
					list = bibleLists.words(thispasuk);
					value = false;
				}
				else if(splitby.equals("letter")){
					list = bibleLists.separateLetters(thispasuk);
					value = false;
				}
				int listlen = list.length;
				for(int j = 0; j < listlen; j++){
					String[] searchtrop = {trop[j]};
					if(splitby.equals("pasuk")){
						searchtrop = trop;
					}
					else if(splitby.equals("letter")){
						String[] nowletter = {list[j]};
						searchtrop = tropLists.tropFinder(nowletter);
					}
					if(searching(json.getJSONArray("search"), 0, list[j], searchtrop).size() != 0){
						String listvalue = "same";
						if(!value){
							listvalue = list[j];
						}
						jsonresult += ",";
						jsonresult += new JSONStringer().object()
										.key("splitvalue").value(listvalue)
										.key("fullverse").value(thispasuk)
										.key("bookname").value(thismaterial[i][1])
										.key("perek").value(thismaterial[i][2])
										.key("pasuk").value(thismaterial[i][3])
										.endObject().toString();
						total++;
					}
				}
			}
		}
		System.out.println("total: " + total);
		return jsonresult.replaceFirst(",", "") + "],";
	}

	public static String[][][] setup(JSONObject json) throws JSONException, FileNotFoundException {
		String searchText = "";
		Boolean taam = json.getBoolean("taamTachton");
		Boolean keri = json.getBoolean("keriUkesiv");
		JSONArray books = json.getJSONArray("books");
		int bookslen = books.length();
		String[][][] material = new String[bookslen][][];
		for (int i = 0; i < bookslen; i++) {
			searchText = Bible.maker.book_numbers(books.getString(i))[0];
			if(taam){
				searchText = Bible.keriUkesiv.tachton(searchText, true);
			}
			else{
				 searchText = Bible.keriUkesiv.elyon(searchText, true);
			}
			if(keri){
				searchText = Bible.keriUkesiv.keri(searchText);
			}
			else{
				 searchText = Bible.keriUkesiv.kesiv(searchText);
			}
			material[i] = bibleLists.numseperator(bibleLists.numpasukim(searchText), books.getString(i));
		}
		return material;
	}

	public static ArrayList<Integer> searching(JSONArray search, int index, String term, String[] trop) throws JSONException {
		JSONObject current = search.getJSONObject(index);
		String param = current.getString("param");
		String connector = current.getString("connector");

		if(param.equals("condition")){
			String type = current.getString("type");
			int i = 0;
			if(!connector.equals("none")){
				int len = search.length();
				int thislevel = current.getInt("level");
				for(i = index + 1; i < len; i++){
					JSONObject nextobject = search.getJSONObject(i);
					int ilevel = nextobject.getInt("level");
					if(thislevel == ilevel && nextobject.getString("param").equals("condition")){
						//System.out.println(i);
						break;
					}
				}
				if(type.equals("contains")){
					return logics(connector, searching(search, index + 1, term, trop), searching(search, i, term, trop));
				}
				else if(type.equals("does not contain")){
					return logics(connector, opposite(searching(search, index + 1, term, trop)), searching(search, i, term, trop));
				}
			}
			else{
				if(type.equals("contains")){
					return searching(search, index + 1, term, trop);
				}
				else if(type.equals("does not contain")){
					return opposite(searching(search, index + 1, term, trop));
				}
			}
		}

		if(param.equals("abstract")){
			String type = current.getString("type");
			ArrayList<Integer> anyhas = new ArrayList<Integer>();
			String[] newterms = {term};
			if(type.equals("word")) {
				newterms = bibleLists.words(term);
			}
			else if(type.equals("tropword")) {
				newterms = bibleLists.tropWords(term);
			}
			else if(type.equals("letter")) {
				newterms = bibleLists.separateLetters(term);
			}
			int i = 0;
			if(!connector.equals("none")){
				int len = search.length();
				int thislevel = current.getInt("level");
				for(i = index + 1; i < len; i++){
					JSONObject nextobject = search.getJSONObject(i);
					int ilevel = nextobject.getInt("level");
					if(thislevel == ilevel && nextobject.getString("param").equals("abstract")){
						break;
					}
				}
				for(String s : newterms){
					if(searching(search, index + 1, s, trop).size() != 0){
						anyhas.add(-1);
						break;
					}
				}
				return logics(connector, anyhas, searching(search, i, term, trop));
			}
			else if(connector.equals("none")){
				for(String s : newterms){
					if(searching(search, index + 1, s, trop).size() != 0){
						anyhas.add(-1);
						break;
					}
				}
			}
			return anyhas;
		}

		ArrayList<Integer> indexlist = new ArrayList<Integer>();
		if(param.equals("input")){
			String val = current.getString("value");
			String type = current.getString("type");
			int totalcount = current.getInt("count");
			String counttype = current.getString("counttype");
			if(type.equals("letter")){
				term = otherBibles.justLetters(term);
			}
			else if(type.equals("nekudah")){
				term = otherBibles.justNekudot(term);
			}
			int termlen = term.length();
			int vallen = val.length();
			if(!type.equals("trop")){
				for(int i = 0; i <= (termlen - vallen); i++){
					if(term.substring(i, i + vallen).equals(val)){
						indexlist.add(i);
					}
				}
			}
			else{
				int troplen = trop.length;
				for(int i = 0; i < troplen; i++){
					if(trop[i].equals(val)){
						indexlist.add(i);
					}
				}
			}
			int listlen = indexlist.size();
			if(counttype.equals("equal")){
				if(!(listlen == totalcount)){
					indexlist.clear();
				}
			}
			else if(counttype.equals("greater")){
				if(!(listlen > totalcount)){
					indexlist.clear();
				}
			}
			else if(counttype.equals("less")){
				if(!(listlen < totalcount)){
					indexlist.clear();
				}
			}
			if(connector.equals("and") || connector.equals("or")){
				return logics(connector, indexlist, searching(search, index + 1, term, trop));
			}
			if(connector.equals("before") || connector.equals("after")){
				return distance(connector, indexlist, searching(search, index + 1, term, trop), current.getInt("distance"), current.getString("distancetype"));
			}
			else{
				return indexlist;
			}
		}
		return indexlist;
	}

	public static ArrayList<Integer> logics(String operation, ArrayList<Integer> one, ArrayList<Integer> two){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.addAll(one);
		result.addAll(two);
		int onesize = one.size();
		int twosize = two.size();
		if(operation.equals("and")){
			if(onesize == 0 || twosize == 0){
				result.clear();
			}
		}
		else if(operation.equals("or")){
			if(onesize == 0 && twosize == 0){
				result.clear();
			}
		}
		return result;
	}

	public static ArrayList<Integer> distance(String operation, ArrayList<Integer> one, ArrayList<Integer> two, int distance, String type){
		ArrayList<Integer> result = new ArrayList<Integer>();
		int onesize = one.size();
		int twosize = two.size();
			for(int i = 0; i < onesize; i++){
				int thisone = one.get(i);
				for(int j = 0; j < twosize; j++){
					int thistwo = two.get(j);
					if(operation.equals("before") && type.equals("greater")){
						if((thistwo - thisone > distance)){
							result.add(thisone);
						}
					}
					else if(operation.equals("before") && type.equals("equal")){
						if((thistwo - thisone == distance)){
							result.add(thisone);
						}
					}
					else if(operation.equals("after") && type.equals("greater")){
						if((thisone - thistwo > distance)){
							result.add(thisone);
						}
					}
					else if(operation.equals("after") && type.equals("equal")){
						if((thisone - thistwo == distance)){
							result.add(thisone);
						}
					}
				}
			}
			if(!(result.size() == one.size() * two.size())){
				result.clear();
			}
		return result;
	}

	public static ArrayList<Integer> opposite(ArrayList<Integer> input){
		ArrayList<Integer> param = input;
		int paramlen = param.size();
		if(paramlen > 0){
			param.clear();
		}
		else{
			param.add(-1);
		}
		return param;
	}
}