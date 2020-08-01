import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
public class JIRA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 RestAssured.baseURI="http://localhost:8080/";
		 
		 SessionFilter session = new SessionFilter();
		 
		 String sessionId=given().log().all().headers("Content-Type","application/json")
		 .body("{ \"username\": \"ankur\", \"password\": \"ankur\" }").log().all().filter(session)
		 .when()
		 .post("rest/auth/1/session").then().log().all().extract().asString();
		 
		 String expectedComment = "Hey, I am Ankur";
		
		String addResponseComment= given().pathParam("id", "10000").log().all().headers("Content-Type","application/json")
		 .body("{\r\n" + 
		 		"    \"body\": \""+expectedComment+"\",\r\n" + 
		 		"    \"visibility\": {\r\n" + 
		 		"        \"type\": \"role\",\r\n" + 
		 		"        \"value\": \"Administrators\"\r\n" + 
		 		"    }\r\n" + 
		 		"}").filter(session).when()
		 .post("rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		 
		JsonPath js= new JsonPath(addResponseComment);
		String commentId=js.getString("id");
		
		 given().log().all().header("X-Atlassian-Token","nocheck").filter(session)
		 .pathParam("id", "10000").header("Content-Type","multipart/form-data").multiPart("file", new File("C:\\Users\\10283719\\eclipse-workspace\\Basics\\src\\Jira.txt"))
		 .when().post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
		 
		
		 //Get Details
		 String issueDetails= given().log().all().filter(session).pathParam("id", "10000")
				             .queryParam("fields", "comment")
				 .get("rest/api/2/issue/{id}").
		 then().log().all().extract().response().asString();
		
		 System.out.println(issueDetails);
		 
		 JsonPath js1= new JsonPath(issueDetails);
		 int count= js1.get("fields.comment.comments.size()");
		 for(int i=0;i<count;i++)
		 {
			String iddetails = (js1.get("fields.comment.comments["+i+"].id"));
			System.out.println(iddetails);
			if(iddetails.equalsIgnoreCase(commentId))
			{
				String bodyMessage = (js1.get("fields.comment.comments["+i+"].body"));
				System.out.println(bodyMessage);
				Assert.assertEquals(bodyMessage, expectedComment);
		    }
		 }
	}

}
