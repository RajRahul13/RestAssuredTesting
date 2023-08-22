package files;

import io.restassured.path.json.JsonPath;
import org.json.*;

public class ReUsableMethods {

	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js =new JsonPath(response);
		return js;
	}
	
	public static JSONObject ExtractFromBody()
	{	
		String body = payload.AddBook();
		JSONObject jsonObj = new JSONObject(body);
		return jsonObj;
		
	}
}
