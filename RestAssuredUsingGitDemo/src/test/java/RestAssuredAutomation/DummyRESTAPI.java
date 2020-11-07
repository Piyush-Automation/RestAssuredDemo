package RestAssuredAutomation;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



import org.json.simple.JSONObject;


public class DummyRESTAPI {
	
	@Test(priority=1)
	public void test_getAllReq() {
		
		given()
		.headers("Content-Type","application/json")
		.when()
			.get("http://dummy.restapiexample.com/api/v1/employees")
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.body("data.id[1]",equalTo("2"))
		.body("data.employee_name", hasItems("Garrett Winters"))
		
		.log().all();
	
	}
	
	
	//@Test(priority=2)
	public void test_PostReq() {
		
		JSONObject req = new JSONObject();
		
		req.put("name","test");
		req.put("salary","123");
		req.put("age","23");
		
		given()
			.headers("Content-Type","application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(req.toJSONString())
		.when()
		.post("http://dummy.restapiexample.com/api/v1/create")
		.then()
		.statusCode(200)
		.body("message", equalTo("Successfully! Record has been added."))
		.log().body();
		
		
	}
	
	
	//@Test(priority=3)
	public void test_deleteReq() {
		
		given()
		.headers("Content-Type","application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
			.delete("http://dummy.restapiexample.com/api/v1/delete/2")
		.then()
		.statusCode(200)
		.body("status", equalTo("success"))
		.body("message", equalTo("Successfully! Record has been deleted"))
		.log().body();
	}
	
	//@Test(priority=4)
	public void test_PutReq() {

		JSONObject req = new JSONObject();
		
		req.put("name","test21");
		req.put("salary","12321");
		req.put("age","21");
		
		
		given()
		.headers("Content-Type","application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(req.toJSONString())
		.when()
			.put("http://dummy.restapiexample.com/api/v1/update/21")
		.then()
		.statusCode(200)
		.body("message", equalTo("Successfully! Record has been updated."))
		.log().body();
	}

}
