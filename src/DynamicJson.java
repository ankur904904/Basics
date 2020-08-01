import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payLoad;

import static io.restassured.RestAssured.*;
public class DynamicJson {

@Test(dataProvider="BooksData")
public void AddBook(String isbn, int id)
{
	RestAssured.baseURI="http://216.10.245.166";
	RestAssured.useRelaxedHTTPSValidation();
	String response=given().log().all().header("Content-Type","application/json")
	.body(payLoad.AddBook(isbn,id)).
	when()
	.post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	String ID= js.getString("ID");
	 System.out.println(ID);
}

@DataProvider(name="BooksData")
public Object getData()
{
          return new Object[][]{{"ankur",1},{"kritika",2},{"amaira",3}};

}
}