package SpecBuilder;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;
import Seria.Addplace;
import Seria.Location;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBu {
       
	public static void main(String[] args) {
		
		Addplace p = new Addplace();
	    p.setAccuracy(50);
	    p.setAddress("ABC1");
	    p.setLanguage("Hindi1");
	    p.setName("Ankur1");
	    p.setPhone_number("94646641");
	    p.setWebsite("www.google.com1");
	    List<String> myList=  new ArrayList<String>();
	    myList.add("shoe park1");
	    myList.add("sho1p");
	    p.setTypes(myList);
	    
	    Location loc = new Location();
	    loc.setLng(-78);
	    loc.setLng(-97.0);
	    p.setLocation(loc);
	    
	      RestAssured.useRelaxedHTTPSValidation();
		  RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
		  addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		 
		
		  ResponseSpecification res= new
		  ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
		  JSON).build();
		  
		  
		  RequestSpecification response= given().spec(req).body(p).log().all();
		  String newResponse=
		  response.when().post("/maps/api/place/add/json").
		  then().log().all().spec(res).extract().response().asString();
		  
		  System.out.println(newResponse);
		 
		
		 
	}

}
