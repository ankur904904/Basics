import files.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJason {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payLoad.CoursePrice());
		//Print no of courses.
	     int count= js.getInt("courses.size()");
	     System.out.println(count);
	     
	     //Print Print Purchase Amount
	     int price = js.getInt("dashboard.purchaseAmount");
	     System.out.println(price);
	     
	     //Print Title of the first course
	     String firtsCourse = js.getString("courses[0].title");
	     System.out.println(firtsCourse);
	     
	     //Print All course titles and their respective Prices
	     for(int i = 0;i < count; i++ )
	     {
	    	  String courseName = js.getString("courses["+i+"].title");
	    	  System.out.println(courseName);
	    	  
	    	  int pricede = js.getInt("courses["+i+"].price");
	    	  System.out.println(pricede);
	     }
	     
	     //Print no of copies sold by RPA Course
	     // pricerRPA = js.getInt("courses[2].copies");
	     //System.out.println("RPA copies " + pricerRPA);
	     
	     for(int i = 0;i < count; i++ )
	     {
	    	  String courseName = js.getString("courses["+i+"].title");
	    	  if(courseName.equals("RPA"))
	    	  {
	    		  int copies= js.getInt("courses["+i+"].copies");
	    		  System.out.println("RPA copies " + copies);
	    		  break;
	    	  }  
	     }
	     
	     //Verify if Sum of all Course prices matches with Purchase Amount
	    
	  
	    
	     
	}

}
