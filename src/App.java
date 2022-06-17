import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import Level_1.bibleLists;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException{
		File fileJSON = new File("test.json");
		String JSON = Level_1.tools.FileToString(fileJSON);
		JSONObject json = new JSONObject(JSON);

		String result = mastersearch(setup(json), json);

		FileOutputStream out = new FileOutputStream("output.json");
		out.write(result.getBytes("UTF-8"));
		out.close();
	}

	public static String mastersearch(String[][][] material, JSONObject json) throws JSONException, FileNotFoundException{
		String jsonresult = "{\"answers\":[";
		String splitby = json.getString("splitBy");
		int len = material.length;
		int total = 0;
		for(int l = 0; l < len; l++){
			String[][] thismaterial = material[l];
			int thismateriallen = thismaterial.length;
			if(splitby.equals("pasuk")){
				for(int i = 0; i < thismateriallen; i++){
					if(searching(json.getJSONArray("search"), 0, thismaterial[i][0])){
						jsonresult += ",";
						jsonresult += new JSONStringer().object()
										.key("splitvalue").value(thismaterial[i][0])
										.key("fullverse").value("same")
										.key("bookname").value(thismaterial[i][1])
										.key("perek").value(thismaterial[i][2])
										.key("pasuk").value(thismaterial[i][3])
										.endObject().toString();
						total++;
					}
				}
			}
			else{
				for(int i = 0; i < thismateriallen; i++){
					String list[] = {};
					if(splitby.equals("word")){
						list = bibleLists.tropWords(thismaterial[i][0], true);
					}
					else if(splitby.equals("letter")){
						list = bibleLists.separateLetters(thismaterial[i][0]);
					}
					int listlen = list.length;
					for(int j = 0; j < listlen; j++){
						if(searching(json.getJSONArray("search"), 0, list[j])){
							jsonresult += ",";
							jsonresult += new JSONStringer().object()
											.key("splitvalue").value(list[j])
											.key("fullverse").value(thismaterial[i][0])
											.key("bookname").value(thismaterial[i][1])
											.key("perek").value(thismaterial[i][2])
											.key("pasuk").value(thismaterial[i][3])
											.endObject().toString();
							total++;
						}
					}
				}
			}
		}
		System.out.println("total: " + total);
		return jsonresult.replaceFirst(",", "") + "]}";
	}

	public static String[][][] setup(JSONObject json) throws JSONException, FileNotFoundException {
		String searchText = "";
		Boolean keri = json.getBoolean("keriUkesiv");
		Boolean taam = json.getBoolean("taamTachton");
		JSONArray books = json.getJSONArray("books");
		int bookslen = books.length();
		String[][][] material = new String[bookslen][][];
		for (int i = 0; i < bookslen; i++) {
			searchText = Bible.maker.book_numbers(books.getString(i))[0];
			if(keri){
				searchText = Bible.keriUkesiv.keri(searchText);
			}
			else{
				 searchText = Bible.keriUkesiv.kesiv(searchText);
			}
			if(taam){
				searchText = Bible.keriUkesiv.tachton(searchText, true);
			}
			else{
				 searchText = Bible.keriUkesiv.elyon(searchText, true);
			}
			material[i] = bibleLists.numseperator(bibleLists.numpasukim(Bible.maker.book_numbers(books.getString(i))[0]), books.getString(i));
		}
		return material;
	}

	public static Boolean searching(JSONArray search, int index, String term) throws JSONException {
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
			}
			//System.out.println(i);
			if(type.equals("contains") && connector.equals("and")){
				return (searching(search, index + 1, term) && searching(search, i, term));
			}
			else if(type.equals("does not contain") && connector.equals("and")){
				return (!searching(search, index + 1, term) && searching(search, i, term));
			}
			else if(type.equals("contains") && connector.equals("or")){
				return (searching(search, index + 1, term) || searching(search, i, term));
			}
			else if(type.equals("does not contain") && connector.equals("or")){
				return (!searching(search, index + 1, term) || searching(search, i, term));
			}
			else if(type.equals("contains") && connector.equals("none")){
				return searching(search, index + 1, term);
			}
			else if(type.equals("does not contain") && connector.equals("none")){
				//System.out.println("here");
				return !searching(search, index + 1, term);
			}
		}

		if(param.equals("abstract")){
			String type = current.getString("type");
			Boolean anyhas = false;
			String[] newterms = {term};
			if(type.equals("word")) {
				newterms = bibleLists.words(term);
			}
			else if(type.equals("tropword")) {
				newterms = bibleLists.tropWords(term, true);
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
					if(searching(search, index + 1, s)){
						anyhas = true;
					}
				}
				if(connector.equals("and")){
					return (anyhas && searching(search, i, term));
				}
				else if(connector.equals("or")){
					return (anyhas || searching(search, i, term));
				}
			}
			else if(connector.equals("none")){
				for(String s : newterms){
					if(searching(search, index + 1, s)){
						anyhas = true;
						break;
					}
				}
			}
			return anyhas;
		}

		Boolean contains = false;
		if(param.equals("input")){
			int totalcount = current.getInt("count");
			String val = current.getString("value");
			String counttype = current.getString("counttype");
			int thiscount = 0;
			int termlen = term.length();
			int vallen = val.length();
			for(int i = 0; i <= (termlen - vallen); i++){
				if(term.substring(i, i + vallen).equals(val)){
					thiscount++;
				}
			}
			if(counttype.equals("equal")){
				if(thiscount == totalcount){
					contains = true;
				}
			}
			else if(counttype.equals("greater")){
				if(thiscount > totalcount){
					contains = true;
				}
			}
			else if(counttype.equals("less")){
				if(thiscount < totalcount){
					contains = true;
				}
			}
			if(connector.equals("and")){
				return (contains && searching(search, index + 1, term));
			}
			else if(connector.equals("or")){
				return (contains || searching(search, index + 1, term));
			}
			else if(connector.equals("none")){
				return contains;
			}
		}
		return contains;
	}
}