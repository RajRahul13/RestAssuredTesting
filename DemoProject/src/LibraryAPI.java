import org.json.JSONObject;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.logging.Logger;

public class LibraryAPI {
	static String ID;
	static String AuthorName;
	static Logger logger=Logger.getLogger("LibraryAPI");
	@Test(priority = 0)
	public static void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String Postresponse=given().log().all().header("Content-Type", "application/json").body(payload.AddBook()).
				when().post("/Library/Addbook.php").
				then().assertThat().statusCode(200).log().all().extract().response().asString();
		//Extracting AuthorName from jsonBody and ID from reponse.
		JsonPath js = ReUsableMethods.rawToJson(Postresponse);
		String ID1 = js.getString("ID");
		JSONObject Jo=ReUsableMethods.ExtractFromBody();
		String AuthorName1=Jo.getString("author");
		ID=ID1;
		AuthorName=AuthorName1;
		logger.info(AuthorName);
	}
	@Test(priority = 1)
	public static void getBookUsingAuthor() {
		RestAssured.baseURI="http://216.10.245.166";
		String PutResponse=given().log().all().queryParam("AuthorName", ""+AuthorName+"").
				when().get("/Library/GetBook.php").
				then().assertThat().statusCode(200).extract().response().asString();
		logger.info(PutResponse);
		System.out.println(AuthorName);
	}
	@Test(priority = 2)
	public static void getBookUsingID() {
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().queryParam("ID",""+ID+"").
				when().get("Library/GetBook.php").
				then().log().all().assertThat().statusCode(200);
	}
	@Test(priority = 3)
	public static void DeleteBookUsingID() {
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().
				body("{\r\n" + 
						"     \"ID\": \""+ID+"\"\r\n" + 
						"}").
				when().post("/Library/DeleteBook.php").
				then().log().all().assertThat().statusCode(200);
	}

}
