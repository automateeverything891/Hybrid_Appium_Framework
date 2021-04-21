package com.practice;

import java.util.Hashtable;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.basetest.TestBase;
import com.utils.Keywords;
import com.utils.TestUtils;

import httpmethods.DELETE;
import httpmethods.GET;
import httpmethods.PATCH;
import httpmethods.POST;
import httpmethods.PUT;


public class TC_027_Validate_On_Screen_And_DashboardUsingAPI extends TestBase{  

	Hashtable<String, String> data = null;
	  
	
	@Test
	public void tC_027_Validate_On_Screen_And_DashboardUsingAPI() throws Exception {
		
		get = new GET();
		
		post = new POST();
		
		put = new PUT();
		
		delete = new DELETE();
		
		patch = new PATCH();

		//GETTING TOKEN API
		post.get_response_header_authantication_login_token("http://falcon-dev.totient.in:8081/falcon/api/authenticate/token", "{\"actionType\":\"AT\",\"signature\":\"iD1DMD3aIh\",\"clientType\":\"ADMIN_WEB\",\"deviceId\":\"90GWN5Cm3I\",\"clientId\":null,\"ipAddress\":\"\",\"mobile\":\"0000111111\",\"code\":null,\"additionalInfo\":{},\"rememberMe\":false}");
		post.get_response_header_authantication("http://falcon-dev.totient.in:8081/falcon/api/authenticate", "{\"actionType\":\"SMO\",\"signature\":\"x0bgIMhCjK\",\"clientType\":\"ADMIN_WEB\",\"deviceId\":\"Iqo6xiGtlm\",\"clientId\":null,\"ipAddress\":\"\",\"mobile\":\"0000111111\",\"code\":null,\"additionalInfo\":{},\"rememberMe\":false}");
		post.get_response_header_authantication("http://falcon-dev.totient.in:8081/falcon/api/authenticate", "{\"actionType\":\"AMO\",\"signature\":\"x0bgIMhCjK\",\"clientType\":\"ADMIN_WEB\",\"deviceId\":\"Iqo6xiGtlm\",\"clientId\":\"sdgffssdfh\",\"ipAddress\":\"\",\"mobile\":\"0000111111\",\"code\":\"0000\",\"additionalInfo\":{},\"rememberMe\":false}");
		post.get_response_header_authantication("http://falcon-dev.totient.in:8081/falcon/api/authenticate", "{\"actionType\":\"RT\",\"clientType\":\"ADMIN_WEB\",\"clientId\":\"sdgffssdfh\",\"allClient\":false}");
		
		if (!TestUtils.isTestcasesExecutable(this.getClass().getSimpleName(), xls))
			throw new SkipException("Test Cases Run mode is set by No!!!!!!!!!");
		
		      Keywords keywords = new Keywords();
		      keywords.executeKeywords(this.getClass().getSimpleName(), data);
		
		
	}

		
	
}
