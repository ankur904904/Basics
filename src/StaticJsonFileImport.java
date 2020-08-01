import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payLoad;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class StaticJsonFileImport {

	@Test
	public void AddBook() throws IOException
	{
		RestAssured.baseURI="http://216.10.245.166";
		RestAssured.useRelaxedHTTPSValidation();
		String response=given().log().all().header("Content-Type","application/json")
		.body(GenerateStringFromResource("C:\\Users\\10283719\\Desktop\\AddBookDetails.json")).
		when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String ID= js.getString("ID");
		 System.out.println(ID);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
