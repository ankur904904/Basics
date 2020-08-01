import io.restassured.path.json.JsonPath;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import files.payLoad;
public class SumValidation {

	 @Test
	 public void SumValidationCourses()
	 {
		 JsonPath js = new JsonPath(payLoad.CoursePrice());
		 int count= js.getInt("courses.size()");
		 int ActualPrice = js.getInt("dashboard.purchaseAmount");
		 int sum = 0;
		 for(int i = 0;i < count; i++ )
	     {
			 int  Price = js.getInt("courses["+i+"].price") ;
			  int copies = js.getInt("courses["+i+"].copies");
			  int Amount =   Price * copies;
			  sum = sum + Amount;
	     }
		 System.out.println(sum);
		 
		 assertEquals(ActualPrice, 900);
	 }
	
}
