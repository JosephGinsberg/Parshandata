import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

import Level_1.bibleLists;

import org.json.JSONException;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException{

		File fileJSON = new File("test.json");
		String JSON = Level_1.tools.FileToString(fileJSON);
		JSONObject json = new JSONObject(JSON);

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
			material = bibleLists.words(searchText);
		}

		/*String searchin = "וַיֹּ֥אמֶר אֱלֹהִ֖ים יְהִ֣י א֑וֹר וַֽיְהִי־אֽוֹר׃";
		System.out.println(searching(json.getJSONArray("search"), 0, searchin));*/

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
		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(answer.getBytes("UTF-8"));
		out.close();

	}

	public static Boolean searching(JSONArray search, int index, String term) throws JSONException {
		JSONObject current = search.getJSONObject(index);
		Boolean contains = false;
		String param = current.getString("param");
		String connector = current.getString("connector");

		if(param.equals("condition")){
			String type = current.getString("type");
			int i = 0;
			if(!connector.equals("none")){
				int len = search.length();
				int thislevel = current.getInt("level");
				for(i = index + 1; i < len; i++){
					int ilevel = search.getJSONObject(i).getInt("level");
					if(thislevel == ilevel && search.getJSONObject(i).getString("param").equals("condition")){
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
	



		if(param.equals("abstract") && connector.equals("none")){
			String type = current.getString("type");
			if(type.equals("word")) {
				String[] words = bibleLists.words(term);
				int wordslen = words.length;
				Boolean anyhas = false;
				for(int i = 0; i < wordslen; i++){
					if(searching(search, index + 1, words[i])){
						anyhas = true;
					}
					break;
				}
				return anyhas;
			}
		} 



		if(param.equals("input") && connector.equals("and")){
			if(term.contains(current.getString("value"))){
				contains = true;
				//System.out.println("here1");
			}
			return (contains && searching(search, index + 1, term));
		}
		else if(param.equals("input") && connector.equals("or")){
			if(term.contains(current.getString("value"))){
				contains = true;
				//System.out.println("here3");
			}
			return (contains || searching(search, index + 1, term));
		}
		else if(param.equals("input") && connector.equals("none")){
			//System.out.println("here2");
			//System.out.println(current.getString("value"));
			if(term.contains(current.getString("value"))){
				contains = true;
				//System.out.println("has");
			}
			return contains;
		}
		return contains;
	}
}