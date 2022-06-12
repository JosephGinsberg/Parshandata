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

		/*String rawJson = "{\"books\": [\"exodus\"],\"keriUkesiv\": true,\"taamTachton\": true,\"display\": \"first\",\"splitBy\": \"pasuk\",\"searchAlt\": [{\"contains\": true,\"connector\": \"or\",\"terms\": [{\"param\": \"\",\"type\": \"letter\",\"value\": \"ו\",\"count\": 3,\"connector\": \"before\"},{\"param\": \"\",\"type\": \"letter\",\"value\": \"ו\",\"count\": 3,\"connector\": \"none\"}]},{\"contains\": false,\"connector\": \"none\",\"terms\": [{\"param\": \"\",\"type\": \"nekudah\",\"value\": \"segol\",\"count\": 1,\"connector\": \"or\"},{\"param\": \"\",\"type\": \"nekudah\",\"value\": \"kamatz\",\"count\": 2,\"connector\": \"none\"}]}]}";*/
		File fileJSON = new File("api_spec.json");
		String JSON = Level_1.tools.FileToString(fileJSON);
		//System.out.println(JSON);
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
		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(searchText.getBytes("UTF-8"));
		out.close();


		JSONArray arr = json.getJSONArray("searchAlt");
		for (int i = 0; i < arr.length(); i++)
		{
			JSONArray terms = arr.getJSONObject(i).getJSONArray("terms");
			for (int j = 0; j < terms.length(); j++)
			{
				String type = terms.getJSONObject(j).getString("type");
				System.out.println(type);

			}
		}

		// String data = Bible.maker.torah();

		// FileOutputStream out = new FileOutputStream("output.txt");
		// out.write(data.getBytes("UTF-8"));
		// out.close();
	}
}