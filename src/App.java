import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import Level_1.bibleLists;
import Level_1.tools;

import java.util.ArrayList;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException{
		File fileJSON = new File("test.json");
		String JSON = Level_1.tools.FileToString(fileJSON);
		JSONObject json = new JSONObject(JSON);

		/*String answer =  mastersearch(setup(json), json);

		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(answer.getBytes("UTF-8"));
		out.close();*/

		/*String[] booklist = {"genesis"};
		String[][] books = Bible.maker.booknums(booklist); 
		String[] answers = {"ף", "ף", "ף", "ף", "ף", "ף", "ף", "ף"};
		String result = JSONbuilder(answers, books);*/

		String result =  mastersearch2(setup(json), json);

		FileOutputStream out = new FileOutputStream("output.json");
		out.write(result.getBytes("UTF-8"));
		out.close();
	}

	public static String mastersearch(String[] material, JSONObject json) throws JSONException{
		String answer = "";
		int len = material.length;
		int total = 0;
		for(int i = 0; i < len; i++){
			if(searching(json.getJSONArray("search"), 0, material[i])){
				answer += material[i] + "\n";
				total++;
			}
		}
		System.out.println("total: " + total);
		return answer;
	}

	public static String mastersearch2(String[] material, JSONObject json) throws JSONException, FileNotFoundException{
		ArrayList<String> answers = new ArrayList<String>();
		int len = material.length;
		int total = 0;
		for(int i = 0; i < len; i++){
			if(searching(json.getJSONArray("search"), 0, material[i])){
				answers.add(material[i]);
				total++;
			}
		}
		System.out.println("total: " + total);
		String[] allanswers = tools.Arraylisttoarray(answers);
		ArrayList<String> allbooks = new ArrayList<String>();
		JSONArray books = json.getJSONArray("books");
		for (int i = 0; i < books.length(); i++) {
			allbooks.add(books.getString(i));
		}
		String[] booknames = tools.Arraylisttoarray(allbooks);
		String[][] finalbooks = Bible.maker.booknums(booknames);
		return JSONbuilder(allanswers, finalbooks);
	}
		
	public static String[] setup(JSONObject json) throws JSONException, FileNotFoundException {
		String searchText = "";
		Boolean keri;
		Boolean taam;
		String splitby;
		String[] material = {};
		JSONArray books = json.getJSONArray("books");
		for (int i = 0; i < books.length(); i++) {
			searchText += Bible.maker.book(books.getString(i));
		}
		keri = json.getBoolean("taamTachton");
		if(keri){
			searchText = Bible.keriUkesiv.keri(searchText);
		}
		else{
	 		searchText = Bible.keriUkesiv.kesiv(searchText);
		}
		taam = json.getBoolean("taamTachton");
		if(taam){
			searchText = Bible.keriUkesiv.tachton(searchText, false);
		}
		else{
	 		searchText = Bible.keriUkesiv.elyon(searchText, false);
		}
		splitby = json.getString("splitBy");
		if(splitby.equals("pasuk")){
			material = bibleLists.pasukim(searchText);
		}
		else if(splitby.equals("word")){
			material = bibleLists.tropWords(searchText, true);
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

	public static String JSONbuilder(String[] answers, String[][] books) throws JSONException{
		String jsonresult = "{\"answers\":[";
		int answerslen = answers.length;
		for(int i = 0; i < answerslen; i++){
			if(i > 0){
				jsonresult += ",";
			}
			int count = 1;
			for(int j = 0; j < i; j++){
				if(answers[j].equals(answers[i])){
					count++;
				}
			}
			int thislen = answers[i].length();
			int bookslen = books.length;
			for(int j = 0; j < bookslen; j++){
				int findercount = 0;
				String currentbook = books[j][0];
				int currentbooklen = currentbook.length();
				int forward = 0;
  				int backward = 0;
				for(int k = 0; k < (currentbooklen - thislen); k++){
					if(currentbook.substring(k, k + thislen).equals(answers[i])){
						findercount++;
						//System.out.println(findercount + " " + count);
					}
					if(currentbook.substring(k, k + thislen).equals(answers[i]) && (findercount == count)){
						forward = 0;
						backward = 0;
						for(int l = k - 1; l < currentbooklen; l++) {
							if(currentbook.substring(l, l + 1).equals("׃")) {
								forward = l + 1;
								break;
							}
						}
						for(int l = k; l > 0; l--) {
							if(currentbook.substring(l - 1, l).equals("׃")) {
								backward = l - 6;
								break;
							}
						}
						String bookname = books[j][1];
						String pasuk = currentbook.substring(backward + 10, forward);
						String[] place = currentbook.substring(backward + 1, backward + 10).replace(" ", "").split("׃");
						jsonresult += new JSONStringer().object()
							.key("splitvalue").value(answers[i])
							.key("fullverse").value(pasuk)
							.key("bookname").value(bookname)
							.key("perek").value(place[1])
							.key("pasuk").value(place[0])
							.endObject().toString();
					}
				}
			}
		}
		jsonresult += "]}";
		return jsonresult;
	}
}