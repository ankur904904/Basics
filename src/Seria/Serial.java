package Seria;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Serial {
       
	public static void main(String[] args) {
		
		Addplace p = new Addplace();
	    p.setAccuracy(50);
	    p.setAddress("ABC");
	    p.setLanguage("Hindi");
	    p.setName("Ankur");
	    p.setPhone_number("9464664");
	    p.setWebsite("www.google.com");
	    List<String> myList=  new ArrayList<String>();
	    myList.add("shoe park");
	    myList.add("shop");
	    p.setTypes(myList);
	    
	    Location loc = new Location();
	    loc.setLng(-78);
	    loc.setLng(-97.0);
	    p.setLocation(loc);
		
		System.out.println(p.getAccuracy());
		
		// TODO Auto-generated method stub
		
		  RestAssured.baseURI="https://rahulshettyacademy.com";
		  RestAssured.useRelaxedHTTPSValidation(); 
		  String response= given().log().all().queryParam("key", "qaclick123").
				  headers("Content-Type","application/json")
				     .body(p)
				     .when().post("/maps/api/place/add/json").
				     then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
				     System.out.println(response);
		
		 
	}

}
