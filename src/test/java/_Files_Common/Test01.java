package _Files_Common;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test01 {
	
	
	@Test
	public void Test() {
		
		given()
				.get("https://reqres.in/api/users?page=2")
		.then()
				.statusCode(200)
				.body("data.id[0]",equalTo(7))
				.body("data.first_name", hasItem("Michael"))
				
				
				.log().all();	//------>> log all = prints all the response all data from response
		
		
		
	}
	
	

}
