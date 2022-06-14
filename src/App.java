import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
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

		String searchin = "וַיֹּ֥אמֶר אֱלֹהִ֖ים יְהִ֣י א֑וֹר וַֽיְהִי־אֽוֹר׃";
		System.out.println(searching(json.getJSONArray("search"), 0, searchin));

		/* FileOutputStream out = new FileOutputStream("output.txt");
		out.write(searchText.getBytes("UTF-8"));
		out.close(); */

		

		// String data = Bible.maker.torah();

		// FileOutputStream out = new FileOutputStream("output.txt");
		// out.write(data.getBytes("UTF-8"));
		// out.close();
	}

	public static Boolean searching(JSONArray search, int index, String term) throws JSONException {
		JSONObject current = search.getJSONObject(index);
		Boolean contains = false;
		String param = current.getString("param");
		String connector = current.getString("connector");

		if(param.equals("condition") && connector.equals("and")){
			String type = current.getString("type");
			if(type.equals("contains")){
				int len = search.length();
				int count = 0;
				int i;
				for(i = index; i < len; i++){
					count += current.getInt("opens");
					if(count == 0 && search.getJSONObject(i).getString("param").equals("condition")){
						break;
					}
				}
				return (searching(search, index + 1, term) && searching(search, i, term));
			}
			else if(type.equals("does not contain")){
				int len = search.length();
				int count = 0;
				int i;
				for(i = index; i < len; i++){
					count += current.getInt("opens");
					if(count == 0 && search.getJSONObject(i).getString("param").equals("condition")){
						break;
					}
				}
				return (!searching(search, index + 1, term) && searching(search, i, term));
			}
		}
		else if(param.equals("condition") && connector.equals("or")){
			String type = current.getString("type");
			if(type.equals("contains")){
				int len = search.length();
				int count = 0;
				int i;
				for(i = index; i < len; i++){
					count += current.getInt("opens");
					if(count == 0 && search.getJSONObject(i).getString("param").equals("condition")){
						break;
					}
				}
				return (searching(search, index + 1, term) || searching(search, i, term));
			}
			else if(type.equals("does not contain")){
				int len = search.length();
				int count = 0;
				int i;
				for(i = index; i < len; i++){
					count += current.getInt("opens");
					if(count == 0 && search.getJSONObject(i).getString("param").equals("condition")){
						break;
					}
				}
				return (!searching(search, index + 1, term) || searching(search, i, term));
			}
		}
		else if(param.equals("condition") && connector.equals("none")){
			String type = current.getString("type");
			if(type.equals("contains")){
				System.out.println(search + " " + (index + 1) + " " + term);
				return searching(search, index + 1, term);
			}
			else if(type.equals("does not contain")){
				return !searching(search, index + 1, term);
			}
		}




		/* if(param.equals("abstract") && connector.equals("none")){
			if(type.equals("word")) {

			}
		} */




		if(param.equals("input") && connector.equals("and")){
			if(term.contains(current.getString("value"))){
				contains = true;
			}
			return (contains && searching(search, index + 1, term));
		}
		else if(param.equals("input") && connector.equals("or")){
			if(term.contains(current.getString("value"))){
				contains = true;
			}
			return (contains || searching(search, index + 1, term));
		}
		else if(param.equals("input") && connector.equals("none")){
			System.out.println("here");
			System.out.println(current.getString("value"));
			if(term.contains(current.getString("value"))){
				contains = true;
				System.out.println("has");
			}
			return contains;
		}
		return contains;
	}
}