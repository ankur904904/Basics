import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payLoad;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     RestAssured.baseURI="https://rahulshettyacademy.com";
     RestAssured.useRelaxedHTTPSValidation();
     String response= given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
     .body(payLoad.AddPlace())
     .when().post("/maps/api/place/add/json").
     then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
     System.out.println(response);
     
     JsonPath js = new JsonPath(response); //for parsing json.
     String placeid= js.getString("place_id");
     System.out.println(placeid);
     
     
     //Update Place
       given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
       .body("{\r\n" + 
       		"\"place_id\":\""+placeid+"\",\r\n" + 
       		"\"address\":\"70 winter walk, USA\",\r\n" + 
       		"\"key\":\"qaclick123\"\r\n" + 
       		"}\r\n" + 
       		"").when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200);
       
       //GetPlace
      String addressnew= given().log().all().queryParam("key", "qaclick123").
       queryParam("place_id", placeid).when().get("/maps/api/place/get/json").then().
       assertThat().log().all().statusCode(200).extract().response().asString();
      JsonPath js1 = new JsonPath(addressnew);
     String actualAddress= js1.getString("address");
     System.out.println(actualAddress);
     
     Assert.assertEquals(actualAddress, "70 winter walk, USA1");
     
     //Junit , TestNG
      
     
	}

}
