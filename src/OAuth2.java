import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
public class OAuth2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
 
		
		
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\10283719\\Desktop\\chromedriver.exe"); WebDriver driver = new
		 * ChromeDriver(); driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss"
		 * ); driver.findElement(By.cssSelector("input[@type='email']")).sendKeys(
		 * "kritikagupta.gupta33@gmail.com");
		 * driver.findElement(By.cssSelector("input[@type='email']")).sendKeys(Keys.
		 * ENTER); Thread.sleep(3000);
		 * driver.findElement(By.cssSelector("input[@type='password']")).sendKeys(
		 * "catchme@20#");
		 * driver.findElement(By.cssSelector("input[@type='password']")).sendKeys(Keys.
		 * ENTER); Thread.sleep(4000); String url = driver.getCurrentUrl();
		 */
		 
		   RestAssured.useRelaxedHTTPSValidation();
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F2QEk8kjtf5aw23uTDKzIJMZ2ZphospgzZl-i9xs238SjaIRu4j3lmCkDEwnVKpKiM1y5Spf8ndxhxNRJQKVItt0&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String partial =url.split("code=")[1];
		String actualCode=partial.split("&scope")[0];
		 System.out.println(actualCode);
		
		String accessTokenResponse= given().
		 urlEncodingEnabled(false).
	     queryParams("code",actualCode).
		 queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		JsonPath js =new JsonPath(accessTokenResponse);
		String accesstoken= js.getString("access_token");
		
		
		
		/*
		 * String response = given().queryParam("access_token", accesstoken)
		 * .when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
		 * .asString(); System.out.println(response);
		 */
		
		//Wrap as Class -  Using Pojo Method
		
		/*
		 * GetCourse gc = given().queryParam("access_token",
		 * accesstoken).expect().defaultParser(Parser.JSON)
		 * .when().get("https://rahulshettyacademy.com/getCourse.php")
		 * .as(GetCourse.class);
		 */
		
		
		  GetCourse gc = given().queryParam("access_token",
		  accesstoken).expect().defaultParser(Parser.JSON)
		  .when().get("https://rahulshettyacademy.com/getCourse.php")
		  .as(GetCourse.class);
		  
		 /* String apisecondtitle= gc.getCourses().getApi().get(1).getCourseTitle();
		  System.out.println(apisecondtitle);
		  
		  List<Api> lapicourses= gc.getCourses().getApi();
		  for(int i=0;i<lapicourses.size(); i++)
		  {
			  if (lapicourses.get(i).getCourseTitle()=="SoapUI Webservices testing")
			  {
				  String getSoapPrice= lapicourses.get(i).getPrice();
			  }
		  }
		  }*/
		  
		  //System.out.println(gc.getLinkedIn());
		  
		  List<WebAutomation> webAutomation =  gc.getCourses().getWebAutomation();
		  for(int i=0;i<webAutomation.size(); i++)
		  {
			  webAutomation.get(i).getCourseTitle();
		  }
	}
}


