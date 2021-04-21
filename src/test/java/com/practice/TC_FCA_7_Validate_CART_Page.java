package com.practice;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.basetest.TestBase;
import com.utils.Keywords;
import com.utils.TestUtils;

public class TC_FCA_7_Validate_CART_Page extends TestBase{
	
	
Hashtable<String, String> data = null;
	  
	
	@Test
	public void tC_FCA_7_Validate_CART_Page() throws Exception {
		 
		
		if (!TestUtils.isTestcasesExecutable(this.getClass().getSimpleName(), xls))
			throw new SkipException("Test Cases Run mode is set by No!!!!!!!!!");
		 
		Keywords keywords = new Keywords();
		keywords.executeKeywords(this.getClass().getSimpleName(), data);
		
		
	}

}
