import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException{

		String rawJson = "{\"books\": [\"genesis\",\"exodus\"],\"keriUkesiv\": false,\"taamTachton\": false,\"display\": \"first\",\"splitBy\": \"pasuk\",\"searchAlt\": [{\"contains\": true,\"connector\": \"or\",\"terms\": [{\"param\": \"\",\"type\": \"letter\",\"value\": \"ו\",\"count\": 3,\"connector\": \"before\"},{\"param\": \"\",\"type\": \"letter\",\"value\": \"ו\",\"count\": 3,\"connector\": \"none\"}]},{\"contains\": false,\"connector\": \"none\",\"terms\": [{\"param\": \"\",\"type\": \"nekudah\",\"value\": \"segol\",\"count\": 1,\"connector\": \"or\"},{\"param\": \"\",\"type\": \"nekudah\",\"value\": \"kamatz\",\"count\": 2,\"connector\": \"none\"}]}]}";
		JSONObject json = new JSONObject(rawJson);

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