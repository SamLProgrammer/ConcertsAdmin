package persistence;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Concert;

public class JSONManager {
	
	//=========================================== ENCODERS && DECODERS ==============================================
	
	public ArrayList<Concert> decodeFullData(String string) {
		ArrayList<Concert> concertsList = new ArrayList<>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(string);
		Type type = new TypeToken<ArrayList<Concert>>(){}.getType();
		concertsList =  gson.fromJson(jsonElement, type);
		return concertsList;   
	}
	
	public String fullDataToJson(ArrayList<Concert> concerts) {
		String jsonDataString = "";
		if(concerts != null) {
			Gson json = new GsonBuilder().setPrettyPrinting().create();
			jsonDataString = json.toJson(concerts);
		}
		return jsonDataString;
	}
	
	public String encodeConcert(Concert concert) {
		Gson gson = new Gson();
		return gson.toJson(concert);
	}
}