package httpmethods;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import com.basetest.TestBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.model.Test;

public class POST extends TestBase {
	
	
	public void response_field_validations(String url,String body, String path, String expected) {
		
		printout("EXECUTING THE POST RECORD METHOD WITH FIELD VALIDATION !");
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").header("Authorization", token3).when().body(body).post(url).jsonPath().get(path);
		
		Assert.assertEquals(actual, expected);
		
		printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+actual+" - "+expected+"TEST CASES IS PASS !!!");
	}
	
	public void response_body_validations(String url, String body, String expected) {
		
		printout("EXECUTING THE POST RECORD METHOD WITH BODY VALIDATION !");
		
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").header("Authorization", token3).when().body(body).post(url).asString();
	
		JsonParser jsonparser = new JsonParser();
		JsonElement json1 = jsonparser.parse(actual);
		JsonElement json2 = jsonparser.parse(expected);
	
		if(!expected.equalsIgnoreCase("")) {
			Assert.assertEquals(json1, json2);
		}
		//printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+actual+" - "+expected+"TEST CASES IS PASS !!!");
	}
	
	public void response_status_code_validations(String url,String body, String expected) {
		
		printout("EXECUTING THE POST RECORD METHOD WITH STATUS CODE VALIDATION !");
		
		int actual = given().header("Content-Type", "application/json").header("Accept", "application/json").header("Authorization", token3).when().body(body).post(url).getStatusCode();
		
		Assert.assertEquals(actual, Integer.parseInt(expected));
		
		printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+actual+" - "+expected+"TEST CASES IS PASS !!!");
	}
	
	public void response_header_validations(String url,String body, String expected) {
		
		printout("EXECUTING THE POST RECORD METHOD WITH HEADER VALIDATION !");
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").header("Authorization", token3).when().body(body).post(url).getHeader("Content-Type");
		
		Assert.assertEquals(actual, expected);
		
		printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+actual+" - "+expected+"TEST CASES IS PASS !!!");
	}

	public void response_time_validations(String url,String body, long expected) {
		
		printout("EXECUTING THE POST RECORD METHOD WITH RESPONSE TIME VALIDATION !");
		
		long actual = given().header("Content-Type", "application/json").header("Accept", "application/json").header("Authorization", token3).when().body(body).post(url).getTime();
		
		Assert.assertTrue(actual<=expected, "RESPONSE TIME LIMIT IS EXCEED!!!");
		
		printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+actual+" - "+expected+"TEST CASES IS PASS !!!");
	}
	
	public void get_response_header_authantication_login_token(String url,String body) {
		
		//printout("EXECUTING THE POST RECORD METHOD WITH HEADER VALIDATION !");
		
		token3 = given().header("Content-Type", "application/json").header("Accept", "application/json").when().body(body).post(url).getHeader("authorization");
		
		//printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+token3+"TEST CASES IS PASS !!!");
	}
		
	public void get_response_header_authantication(String url,String body) {
		
		//printout("EXECUTING THE POST RECORD METHOD WITH HEADER VALIDATION !");
		
		token3 = given().header("Content-Type", "application/json").header("Accept", "application/json").header("Authorization", token3).when().body(body).post(url).getHeader("authorization");
		
		//printout("ACTUAL CONDITION MATCHING WITH EXPECTED CONDITION. "+token3+"TEST CASES IS PASS !!!");
	}

}
