import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Level_1.bibleLists;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException{
		File fileJSON = new File("test.json");
		String JSON = Level_1.tools.FileToString(fileJSON);
		JSONObject json = new JSONObject(JSON);

		String answer =  mastersearch(setup(json), json);

		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(answer.getBytes("UTF-8"));
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
				newterms = bibleLists.tropWords(term, true);
			}
			else if(type.equals("letter")) {
				newterms = bibleLists.separateLetters(term);;
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
			if(term.contains(current.getString("value"))){
				contains = true;
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