package com.practice;

import java.util.Hashtable;

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

public class TC_FCA_3_Validate_Dashboard_Attributes_values extends TestBase{
	
Hashtable<String, String> data = null;
	  
	
	@Test
	public void tC_FCA_3_Validate_Dashboard_Attributes_values() throws Exception {
		
		
		if (!TestUtils.isTestcasesExecutable(this.getClass().getSimpleName(), xls))
			throw new SkipException("Test Cases Run mode is set by No!!!!!!!!!");
		
		Keywords keywords = new Keywords();
		keywords.executeKeywords(this.getClass().getSimpleName(), data);
		
		
	}

}
