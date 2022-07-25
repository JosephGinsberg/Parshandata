package com.parshandata.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

@RestController
@CrossOrigin
public class DefaultController {
	@PostMapping(path="/api/search", consumes = MediaType.ALL_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> search(@RequestBody String requestJson) throws FileNotFoundException, InterruptedException, JSONException{
		// add serializer
		String response = "";
		try {
			// change to GsonHttpMessageConverter
			// JSONObject req = new JSONObject(requestJson);
			// System.out.println(req.get("books"));

			response = BibleStatistics.App.searchAll(requestJson);
			// URL fileUrl = getClass().getResource("output.json");
			// response = tools.FileToString(fileUrl.getPath());
		} catch (Exception err) {
			String errorMessage = err.getMessage().replaceAll("\"", "''");
			response = "{\"ok\":false,\"msg\":\"" + errorMessage + "\",\"matches\":[],\"runtime\":0}";
		}

		// response = BibleStatistics.App.searchAll(requestJson);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}






	// working: spits out demo res
	@RequestMapping(path="/sample", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sample() throws FileNotFoundException, InterruptedException{
		URL url = getClass().getResource("output.json");
		String tempData = tools.FileToString(url.getPath());
		TimeUnit.SECONDS.sleep(1);
		return new ResponseEntity<String>(tempData, HttpStatus.OK);
	}

	// working; returns application/json
	@GetMapping(path = "/otherpage", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> otherPage() throws JSONException{
		ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
		entities.add(new JSONObject("{\"testing\": true}"));
		entities.add(new JSONObject("{\"randomVal\": []}"));
		return new ResponseEntity<String>(entities.toString(), HttpStatus.OK);
	}

	// working: try catch invalid JSON
	@PostMapping("/text")
	public void newEmployee(@RequestBody String rawData) throws JSONException {
		try {
			JSONObject req = new JSONObject(rawData);
			System.out.println(req.get("books"));
		} catch (Exception e) {
			System.out.println("Invalid JSON was passed in");
		}
	}

	// working: reads string and utilizes library to parse JSON
	@RequestMapping(path="/inbound", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<String> inbound(@RequestBody String body) throws JSONException{
		System.out.println(body);

		JSONObject json = new JSONObject();
		json.put("BoolVal", true);
		json.put("emptyArray", new String[0]);
		String res = json.toString();
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}