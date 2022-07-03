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
	public static void main(String[] args) throws IOException, JSONException{
		
		long startTime = System.currentTimeMillis();

		String JSON = Level_1.tools.FileToString("test.json");
		JSONObject json = new JSONObject(JSON);

		String result = mastersearch(setup(json), json);
		result += "\"runtime\": " + (System.currentTimeMillis() - startTime) + "}";

		FileOutputStream out = new FileOutputStream("output.json");
		out.write(result.getBytes("UTF-8"));
		out.close();

		/*long execTime = System.currentTimeMillis() - startTime;
		System.out.println(execTime);*/
	
	}

	public static String mastersearch(String[][][] material, JSONObject json) throws JSONException, FileNotFoundException {
		String jsonresult = "{\"matches\":[";
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
				else if(splitby.equals("tropword")){
					list = bibleLists.tropWords(thispasuk);
					value = false;
				}
				else if(splitby.equals("letter")){
					list = bibleLists.separateLetters(thispasuk);
					value = false;
				}
				int listlen = list.length;
				for(int j = 0; j < listlen; j++){
					ArrayList<Integer> matches = searching(json.getJSONArray("search"), 0, list[j], trop);
					if(matches.size() != 0){
						String listvalue = "same";
						if(!value){
							listvalue = list[j];
						}
						jsonresult += ",";
						String v = "";
						for(int x : matches){
							v += " ," + x;
						}
						v = v.replaceFirst(" ,", "");
						jsonresult += new JSONStringer().object()
										.key("splitvalue").value(listvalue + ": " + v)
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

	public static ArrayList<Integer> searching(JSONArray search, int index, String term, String[] trop) throws JSONException, FileNotFoundException {
		
		JSONObject current = search.getJSONObject(index);
		String param = current.getString("param");
		String connector = current.getString("connector");
		String type = current.getString("type");
		ArrayList<Integer> indexlist = new ArrayList<Integer>();

		if(param.equals("condition")){
			ArrayList<Integer> output = searching(search, index + 1, term, trop);
			if(type.equals("does not contain")){
				output = opposite(output);
			}
			if(connector.equals("and") || connector.equals("or")){
				int i = nextFinder(search, index, param);
				return logics(connector, output, searching(search, i, term, trop));
			}
			else if(connector.equals("before") || connector.equals("after")){
				int i = nextFinder(search, index, "any");
				JSONObject next = search.getJSONObject(index + 1);
				return distance(next, indexlist, searching(search, i, term, trop));
			}
			else{
				return output;
			}
		}

		else if(param.equals("abstract")){
			int totalcount = current.getInt("count");
			String counttype = current.getString("counttype");
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
			int newlen = newterms.length;
			for(int j = 0; j < newlen; j++){
				//needs to be fixed
				if(searching(search, index + 1, newterms[j], trop).size() != 0){
					indexlist.add(j);
				}
			}
			indexlist = countchecker(indexlist, totalcount, counttype);
			if(connector.equals("and") || connector.equals("or")){
				int i = nextFinder(search, index, param);
				return logics(connector, indexlist, searching(search, i, term, trop));
			}
			else if(connector.equals("before") || connector.equals("after")){
				int i = nextFinder(search, index, param);
				JSONObject next = search.getJSONObject(index + 1);
				return distance(next, indexlist, searching(search, i, term, trop));
			}
			else{
				return indexlist;
			}
		}

		else if(param.equals("distance")){
			if(connector.equals("and") || connector.equals("or")){
				int i = nextFinder(search, index, "input");
				return logics(connector, searching(search, index + 1, term, trop), searching(search, i, term, trop));
			}
			else{
				return searching(search, index + 1, term, trop);
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
					if(trop[i].equals(val)){
						indexlist.add(i);
					}
				}	
			}
			indexlist = countchecker(indexlist, totalcount, counttype);
			if(connector.equals("and") || connector.equals("or")){
				return logics(connector, indexlist, searching(search, index + 1, term, trop));
			}
			if(connector.equals("distance")){
				JSONObject next = search.getJSONObject(index + 1);
				return distance(next, indexlist, searching(search, index + 1, term, trop));
			}
			else{
				return indexlist;
			}
		}
		return indexlist;
	}

	public static ArrayList<Integer> logics(String operation, ArrayList<Integer> one, ArrayList<Integer> two){
		ArrayList<Integer> result = new ArrayList<Integer>();
		Boolean notempty = true;
		int onesize = one.size();
		int twosize = two.size();
		if(operation.equals("and")){
			if(onesize == 0 || twosize == 0){
				notempty = false;
			}
		}
		else if(operation.equals("or")){
			if(onesize == 0 && twosize == 0){
				notempty = false;
			}
		}
		if(notempty){
			result.addAll(one);
			for(Integer s : two){
				if(!result.contains(s)){
					result.add(s);
				}
			}
		}
		return result;
	}

	public static ArrayList<Integer> distance(JSONObject input, ArrayList<Integer> one, ArrayList<Integer> two) throws JSONException{
	//	String operation, ArrayList<Integer> one, ArrayList<Integer> two, int distance, String type){
		String operation = input.getString("type");
		int distance = input.getInt("distance");
		String type = input.getString("distancetype");
		ArrayList<Integer> result = new ArrayList<Integer>();
		int onesize = one.size();
		int twosize = two.size();
			for(int i = 0; i < onesize; i++){
				int thisone = one.get(i);
				for(int j = 0; j < twosize; j++){
					int thistwo = two.get(j);
					if(operation.equals("before") && type.equals("greater")){
						if((thistwo - thisone > distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("before") && type.equals("equal")){
						if((thistwo - thisone == distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("before") && type.equals("less")){
						if((thistwo - thisone < distance) && !(thistwo - thisone < 0)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("after") && type.equals("greater")){
						if((thisone - thistwo > distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("after") && type.equals("equal")){
						if((thisone - thistwo == distance)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
					else if(operation.equals("after") && type.equals("less")){
						if((thisone - thistwo < distance) && !(thistwo - thisone < 0)){
							if(!result.contains(thisone)){
								result.add(thisone);
							}
						}
					}
				}
			}
			/*if(!(result.size() == one.size() * two.size())){
				result.clear();
			}*/
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

	public static int nextFinder(JSONArray search, int currentindex, String value) throws JSONException{
		JSONObject current = search.getJSONObject(currentindex);
		int thislevel = current.getInt("level");
		int len = search.length();
		int i = 0;
		for(i = currentindex + 1; i < len; i++){
			JSONObject nextobject = search.getJSONObject(i);
			int ilevel = nextobject.getInt("level");
			if(thislevel == ilevel && (nextobject.getString("param").equals(value) || value.equals("any"))){
				return i;
			}
		}
		return i;
	}

	public static ArrayList<Integer> countchecker(ArrayList<Integer> input, int totalcount, String counttype){
		ArrayList<Integer> output = input;
		int listlen = output.size();
			if(counttype.equals("equal")){
				if(!(listlen == totalcount)){
					output.clear();
				}
			}
			else if(counttype.equals("greater")){
				if(!(listlen > totalcount)){
					output.clear();
				}
			}
			else if(counttype.equals("less")){
				if(!(listlen < totalcount)){
					output.clear();
				}
			}
		return output;
	}
}