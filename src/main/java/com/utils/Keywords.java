package com.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.basetest.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class Keywords extends TestBase {

	public String testCasesName = "";

	public void executeKeywords(String testcases, Hashtable<String, String> data) throws Exception {

		this.testCasesName = testcases;
		loadproperties();

		System.out.println("Executable test cases is :" + testcases);

		String keyword = null;
		String objectkeys = null;
		String datakeys = null;
		String variables = null;
		String urls = null;

		for (int rNum = 2; rNum <= xls.getRowCount("Test Steps"); rNum++) {
			if (testcases.equalsIgnoreCase(xls.getCellData("Test Steps", "TCID", rNum))) {
				keyword = xls.getCellData("Test Steps", "Keyword", rNum);
				objectkeys = xls.getCellData("Test Steps", "Object", rNum);
				datakeys = xls.getCellData("Test Steps", "Data", rNum);
				variables = xls.getCellData("Test Steps", "Variables", rNum);
				urls = xls.getCellData("Test Steps", "URLS", rNum);
				
				System.out.println(keyword + "--" + objectkeys + "--" + datakeys);

				String result = null;

				if (keyword.equalsIgnoreCase("click")) {

					result = click(objectkeys);
				} else if (keyword.equalsIgnoreCase("enter")) {

					if (data != null)
						result = enter(objectkeys, data.get(datakeys));
					else
						result = enter(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("verifyElement")) {
					result = verifyElement(objectkeys);
				} else if (keyword.equalsIgnoreCase("verifyText")) {
					if (data != null)
						result = verifyText(objectkeys, data.get(datakeys));
					else
						result = verifyText(objectkeys, datakeys);

				} else if (keyword.equalsIgnoreCase("scroll_Down")) {
					result = scroll_Down(objectkeys);
				} else if (keyword.equalsIgnoreCase("goback")) {
					result = goback();
				} else if (keyword.equalsIgnoreCase("checkAndClick")) {
					result = checkAndClick(objectkeys);
				} else if (keyword.equalsIgnoreCase("searchAndSelect")) {
					result = searchAndSelect(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("selectDropdown")) {
					result = selectDropdown(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("select_date")) {
					result = select_date(datakeys);
				} else if (keyword.equalsIgnoreCase("scrollHorizontal")) {
					result = scrollHorizontal();
				} else if (keyword.equalsIgnoreCase("compareImage")) {
					result = compareImage(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("verifyelementNotDisplayed")) {
					result = verifyelementNotDisplayed(objectkeys);
				} else if (keyword.equalsIgnoreCase("waitElementPresent")) {
					result = waitElementPresent(objectkeys);
				} else if (keyword.equalsIgnoreCase("callTestCases")) {
					result = callTestCases(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("scrollHorizontalUntilElementPresent")) {
					result = scrollHorizontalUntilElementPresent(objectkeys);
				} else if (keyword.equalsIgnoreCase("scrollUsingTouchActions_ByElements")) {
					result = scrollUsingTouchActions_ByElements(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("verifyElementList")) {
					result = verifyElementList(objectkeys,datakeys);
				} else if (keyword.equalsIgnoreCase("selectFromListOfElement")) {
					result = selectFromListOfElement(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("setText")) {
					result = setText(objectkeys, datakeys);
				} else if (keyword.equalsIgnoreCase("sleep")) {
					result = sleep(datakeys);
				} else if (keyword.equalsIgnoreCase("getUniqueElement")) {
					result = getUniqueElement(objectkeys, datakeys, variables);
				} else if (keyword.equalsIgnoreCase("getText")) {
					result = getText(objectkeys, datakeys, variables);
				} else if (keyword.equalsIgnoreCase("verifyTextWithContinesElement")) {
					result = verifyTextWithContinesElement(objectkeys, variables);
				}else if (keyword.equalsIgnoreCase("verifyElementText")) {
					result = verifyElementText(objectkeys, datakeys);
				}
				else if (keyword.equalsIgnoreCase("verifyContineText")) {
					result = verifyContineText(objectkeys, datakeys);
				}
				
				else if (keyword.equalsIgnoreCase("verifyElementTextWithNextObj")) {
					result = verifyElementTextWithNextObj(objectkeys, datakeys, variables);
				}else if (keyword.equalsIgnoreCase("tab")) {
					result = tab(objectkeys);
				} 
				else if (keyword.equalsIgnoreCase("clickOnText")) {
					result = clickOnText(datakeys);
				} 
				else if (keyword.equalsIgnoreCase("checkinglistofvalue")) {
					result = checkinglistofvalue(objectkeys);
				}
				else if (keyword.equalsIgnoreCase("select")) {
					result = select(datakeys);
				}
				else if (keyword.equalsIgnoreCase("getWeekDayCount")) {
					result = getWeekDayCount(objectkeys,datakeys,variables);
				}
				else if (keyword.equalsIgnoreCase("getWorkDayCount")) {
					result = getWorkDayCount(objectkeys,datakeys,variables);
				}
				else if (keyword.equalsIgnoreCase("getAllDays")) {
					result = getAllDays(objectkeys,datakeys,variables);
				}
				else if (keyword.equalsIgnoreCase("verifySelected")) {
					result = verifySelected(objectkeys);
				} else if (keyword.equalsIgnoreCase("acceptAlert")) {
					result = acceptAlert(datakeys);
				} else if (keyword.equalsIgnoreCase("denyAlert")) {
					result = denyAlert(datakeys);
				}
				else if (keyword.equalsIgnoreCase("singleTab")) {
					result = singleTab(objectkeys);
				}
				else if (keyword.equalsIgnoreCase("relaunchApp")) {
					result = relaunchApp(datakeys);
				}
				else if(keyword.equalsIgnoreCase("clickOnIfNotUniqueElement")) {
					
					result = clickOnIfNotUniqueElement(objectkeys,datakeys);
				}
				else if(keyword.equalsIgnoreCase("response_field_validations")) {
					
					result = response_field_validations(objectkeys,datakeys, urls);
				}
				else if(keyword.equalsIgnoreCase("response_list_validations")) {
					
					result = response_list_validations(objectkeys,datakeys, urls);
				}
				else if(keyword.equalsIgnoreCase("getTextFromAPI")) {
					
					result = getTextFromAPI(datakeys, urls, variables);
				}
				else if(keyword.equalsIgnoreCase("verifyElementInListOfElements")) {
					
					result = verifyElementInListOfElements(objectkeys,datakeys, variables);
				}
				else if(keyword.equalsIgnoreCase("closeApps")) {
					
					result = closeApps();
				}
				else if(keyword.equalsIgnoreCase("verifyprice")) {
					result =verifyprice(objectkeys,variables);
				}
				
				else if(keyword.equalsIgnoreCase("waitForElementPresent")) {
					
					result =waitForElementPresent(objectkeys,datakeys);
				}
				
				else {

					System.out.println("Not Matching Keyword :" + keyword);

				}

				if (result != null) {
					if (result.equalsIgnoreCase("Pass"))
						xls.setCellData1("Test Steps", "Status", (rNum - 1), "Executed");
					
					else
						xls.setCellData1("Test Steps", "Status", (rNum - 1), "Not Executed");
				}

			}

		}

	}//search_suggestion_txt=xpath://android.view.View[@text='Bangalore Times']
	
	private String verifyprice(String objectkeys, String variables) {
		
		String expected = String.format("%,d",Integer.parseInt(maptable.get(variables).split(" ")[1]));
		
		String expected1="";
		if (expected.contains(","))
			expected1 =expected.replace(",", "").trim();
		else 
			expected1 = expected;
		int expectedprice = Integer.parseInt(expected1);
		int actualprice = Integer.parseInt(getWebElement(objectkeys).getText().split(" ")[2].replaceAll("[^0-9\\.]", "").trim());
		
		Assert.assertEquals(actualprice, expectedprice);
		return "Pass";  
		
	}

	private String closeApps() {
		
		driver.closeApp();
		
		return "Pass";
	}

	private String verifyElementInListOfElements(String objectkeys, String datakeys, String variables) {
		
		for(int i=0; i<getWebElements(objectkeys).size();i++) {
			
			if(maptable.containsKey(variables)) {
				if(getWebElements(objectkeys).get(i).getText().contains(maptable.get(variables))) {
					Assert.assertTrue(getWebElements(objectkeys).get(i).getText().contains(maptable.get(variables)), "PRODUCT NAME IS PRESENT!!");
				}
				else {
					Assert.assertTrue(getWebElements(objectkeys).get(i).getText().contains(datakeys), "PRODUCT NAME IS PRESENT!!");
				}
			}
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 100)).release().perform();
			
		}
		return "PASS";
	}

	private String getTextFromAPI(String datakeys, String urls,String variables) {

		printout("GET TEXT FROM API METHOD IS CALLED!!!!!!!!!!!!!!!!!!!!");
	
		printout("URLS"+urls);
		printout("DATAKEYS "+datakeys);
		printout("VARIABLES "+variables);
		
		if(urls.contains("productId")) {
			
			String var1=maptable.get(urls.split("=")[1]);
			printout(var1);
			printout(urls.split("=")[0]+var1);
			maptable.put(variables, get.response_field_validations(urls.split("=")[0]+var1, datakeys));
		}
		else {
			printout("Test"+get.response_field_validations(urls, datakeys));
			maptable.put(variables, get.response_field_validations(urls, datakeys));
		}
		
		
		return "PASS";
	}

	private String response_field_validations(String objectkeys, String datakeys, String  variables ) {
		
		printout(getWebElement(objectkeys).getText());
		//printout(variables);
		//printout(datakeys);
		printout(get.response_field_validations(variables, datakeys));
		
		Assert.assertTrue(getWebElement(objectkeys).getText().contains( get.response_field_validations(variables, datakeys)), "Strig Values ARE EQUALS!");
		//Assert.assertEq   uals(getWebElement(objectkeys).getText(), get.response_field_validations(variables, datakeys));
		return "Pass";
	}

	private String response_list_validations(String objectkeys, String datakeys, String  variables ) throws InterruptedException {
		
		
		if(objectkeys.contains("|")) {
		
			//List<MobileElement> list = getWebElements("//android.view.View[@index='0' and @clickable='true']");

			printout(getWebElement(objectkeys.split("\\|")[1]).getText().replaceAll("[^-?0-9]+", " "));
			
			int result = Integer.parseInt(getWebElement(objectkeys.split("\\|")[1]).getText().replaceAll("[^-?0-9]+", " ").trim());
			
			int count =0; 
			
			HashSet<String> hashset = new HashSet<String>();
			
				//printout("==============>>>>>>>>>>>>>>>>>>>>>>>" + list.get(i).getText());
				
				//printout("SEARCH LIST VALUES ARE : "+result+" ---- "+i);
				printout("======="+datakeys);

				//list = getWebElements("//android.view.View[@index='0' and @clickable='true']");
				
								
				while(count!=result){
					
					getWebElements(objectkeys.split("\\|")[0]);
						
		
						printout("J COUNT IS : "+count+"--"+hashset.size());
	
						//printout(driver.findElement(By.className("android.view.View")).getText());				
						
						printout("=====>>>>>>> "+driver.findElements(By.xpath("//android.view.View")).size());
						
						
						
						//hashset.add(driver.findElements(By.className("android.view.View")).get(0).getText());
						//printout("ADDED THE OUTER PRODUCT "+driver.findElements(By.className("android.view.View")).get(0).getText());
						
						//hashset.add(list.get(1).getText());
						//printout("ADDED THE INNER PRODUCT "+list.get(2).getText());
						
						List<MobileElement> list =driver.findElements(By.className("android.view.View"));

						
						count = addingValue(list, hashset, count, result);
				/*
				 * for(WebElement s :list){ hashset.add(s.getText());
				 * printout("P Count "+count+s.getText());
				 * 
				 * TouchAction action = new TouchAction(driver);
				 * action.longPress(PointOption.point(0, 610)).moveTo(PointOption.point(0,
				 * 100)).release().perform(); Thread.sleep(4000); list
				 * =driver.findElements(By.className("android.view.View")); count++; }
				 */
										printout("TOTAL COUNT IS "+count);
							
							 //list =driver.findElements(By.className("android.view.View"));
							
							TouchAction action = new TouchAction(driver);
							action.longPress(PointOption.point(0, 610)).moveTo(PointOption.point(0, 100)).release().perform();
							Thread.sleep(5000);
							
							list =driver.findElements(By.className("android.view.View"));
							
							if(count==result)
								break;
					}
				
				printout("API----"+get.response_field_validations(variables, "["+0+"].name"));
				
				//TouchAction action = new TouchAction(driver);
				//action.longPress(PointOption.point(0, 400)).moveTo(PointOption.point(0, 80)).release().perform();
				//list = getWebElements(objectkeys.split("\\|")[0]);
				//printout(get.response_list_validations(variables, datakeys).get(i));
				
			printout("TOTAL NO OF PRODUCTS IN LISTING COUNT IS : "+count);
			printout("SEARCH LIST VALUES ARE : "+result+" ---- "+count);
			printout("NAMMO : "+hashset);
			Assert.assertEquals(result, count);
			
		}
		return "Pass";
	}
	private int addingValue(List<MobileElement> list, HashSet<String> hashset, int count, int result) throws InterruptedException {
		
			for(int h=0; h<list.size();h++) {
				
				//printout("ADDED THE INNER PRODUCT "+driver.findElements(By.className("android.view.View")).get(h).getText());
				
				// list =driver.findElements(By.className("android.view.View"));
			
					hashset.add(list.get(1).getText());
					printout("ADDED THE INNER PRODUCT "+list.get(1).getText());
				
					hashset.add(list.get(2).getText());
					printout("ADDED THE INNER PRODUCT "+list.get(2).getText());


					hashset.add(list.get(3).getText());
					printout("ADDED THE INNER PRODUCT "+list.get(3).getText());
					count++;
				
					printout("FMOM"+driver.findElement(By.xpath("//*[@class='android.view.View']")).getText());
				TouchAction action = new TouchAction(driver);
				action.longPress(PointOption.point(0, 610)).moveTo(PointOption.point(0, 100)).release().perform();
                
				Thread.sleep(4000);
				list =driver.findElements(By.className("android.view.View"));
				
				Thread.sleep(4000);
				count++;
				
				if(count==result)
					break;
			}
			return count;
		

	}

	private String singleTab(String objectkeys) {
		printout("TAB ON SINGLE ELEMENT "+getWebElement(objectkeys).getText().toUpperCase()+"!!!!!!!!!!!!!!");
		
		getWebElement(objectkeys).sendKeys(Keys.TAB);
		return "Pass";
	}

	private String clickOnIfNotUniqueElement(String objectkeys, String datakeys) {
		
		printout("CLICK ON UNIQUE ELEMENT "+datakeys.toUpperCase()+"!!!!!!!!!!!!!!");
		List<MobileElement> listofelement = getWebElements(objectkeys);
		
		for(int i=0; i<listofelement.size(); i++) {
			if((i+1)==Integer.parseInt(datakeys)) {
				
				listofelement.get(i).click();
				break;
			}
			
		}
		
		return "Pass";
	}

	private String select(String datakeys) {
		
		printout("SELECTED THE VALUE "+datakeys.toUpperCase()+"!!!!!!!!!!!!!!");
		
		
		boolean onclicked = false;
		while(onclicked!=true) {
			
			if(driver.findElementsByXPath("//*[@text='"+datakeys+"']").size()==1) {
				driver.findElementByXPath("//*[@text='"+datakeys+"']").click();
				onclicked=true;
			}
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 100)).release().perform();
		}
		return "Pass";
	}

	private String clickOnText(String datakeys) {
		printout("CLICK ON "+datakeys.toUpperCase()+" !!!!!!!!!!!!!");////*[contains(text(),'ABC')]
		if(maptable.containsKey(datakeys)) {
			driver.findElementByXPath("//android.view.View[contains(text(),'"+maptable.get(datakeys)+"')]").click();
		}
		else {
			driver.findElementByXPath("//android.view.View[contains(text(),'"+datakeys+"')]").click();
		}
		
		return "Pass";
	}

	private String verifyContineText(String objectkeys, String datakeys) {
		printout("VERIFY CONTINE TEXT METHOD IS CALLED!!!!!!!!!!!!!");
		Assert.assertTrue(getWebElement(objectkeys).getText().contains(datakeys), "TEXT IS MATCHING "+getWebElement(objectkeys));
		return null;
	}

	private String verifyElementText(String objectkeys, String datakeys) {
		printout("VERIFY ELEMENT TEXT METHOD IS CALLED!!!!!!!!!!!!!");
		Assert.assertEquals(maptable.get(objectkeys).toUpperCase(), maptable.get(datakeys).toUpperCase());
		return "Pass";
	}

	public String verifyTextWithContinesElement(String objectkeys, String variables) {

		printout("VERIFY TEXT WITH CONTINES ELEMENT METHOD IS CALLED!!!!!!!!!!!!!");
		printout(getWebElement(objectkeys).getText());
		// printout("=======>>>>>"+maptable.get(variables));
		if (getWebElement(objectkeys).getText().contains(maptable.get(variables))) {

			Assert.assertTrue(getWebElement(objectkeys).getText().contains(maptable.get(variables)),
					"Element is present");
		}
		return "Pass";
	}

	private String getUniqueElement(String objectkeys, String datakeys, String variables) {

		printout("GET UNIQUE ELEMENT USING ARRAY OF INTEX ELEMENT METHOD IS CALLED");
		printout(getWebElements(objectkeys).get(Integer.parseInt(datakeys)).getText());
		maptable.put(variables, getWebElements(objectkeys).get(Integer.parseInt(datakeys)).getText());

		return "Pass";
	}

	private String sleep(String datakeys) {
		printout("SLEEP METHOD IS CALLED !!!!!!!!!!!!!!!!!!!");
		try {
			Thread.sleep(Long.parseLong(datakeys));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Pass";
	}

	private String getText(String objectkeys, String datakeys, String variables) {

		
		if (datakeys.equalsIgnoreCase("Number")) {
			printout(getWebElement(objectkeys).getText().replaceAll("[^-?0-9]+", " "));
			printout("GETTING THE TEXT "+getWebElement(objectkeys).getText().replaceAll("[^-?0-9]+", " ")+"!!!!!!!!!!!");
			maptable.put(variables, getWebElement(objectkeys).getText().replaceAll("[^-?0-9]+", " "));
		} else {
			printout(getWebElement(objectkeys).getText());
			printout("GETTING THE TEXT "+getWebElement(objectkeys).getText().toUpperCase()+"!!!!!!!!!!!");
			maptable.put(variables, getWebElement(objectkeys).getText());
		}

		return "Pass";
	}

	private String verifyElementTextWithNextObj(String objectkeys, String datakeys, String variables) {
		printout("VERIFY ELEMENT TEXT WITH ANOTHER PAGE ELEMENT METHOD IS CALLED !!!!!!!!!!!");

		if (datakeys.equalsIgnoreCase("countmatch")) {
			int proceedcartcount = Integer
					.parseInt(getWebElement(objectkeys).getText().replaceAll("[^-?0-9]+", " ").trim());
			int cartcount = Integer.parseInt(maptable.get(variables).split(" ")[0].trim());
			Assert.assertEquals(proceedcartcount, cartcount);
			printout(proceedcartcount + " - EQUALS - " + cartcount+"COUNT IS EQUAL");
		} else if (datakeys.equalsIgnoreCase("pricematch")) {
			int priceamountfromproccedcart = Integer
					.parseInt(getWebElement(objectkeys).getText().replaceAll("[^-?0-9]+", " ").split(" ")[1].trim());
			if(maptable.get(variables).contains(" ")) {
				
				int priceamountfromcartpage = Integer.parseInt(maptable.get(variables).split(" ")[1].trim());
				Assert.assertEquals(priceamountfromproccedcart, priceamountfromcartpage);
				printout(priceamountfromproccedcart + " - EQUALS - " + priceamountfromcartpage+"PRICE IS EQUALS");
			}
			else {
				int priceamountfromcartpage = Integer.parseInt(maptable.get(variables).trim());
				Assert.assertEquals(priceamountfromproccedcart, priceamountfromcartpage);
				printout(priceamountfromproccedcart + " - EQUALS - " + priceamountfromcartpage+"PRICE IS EQUALS");
			}
			
		}

		return "Pass";
	}

	private String checkinglistofvalue(String objectkeys) {

		List<MobileElement> list = getWebElements(objectkeys.split("\\|")[0]);

		printout(getWebElement(objectkeys.split("\\|")[1]).getText().replaceAll("[^-?0-9]+", " "));
		
		int result = Integer.parseInt(getWebElement(objectkeys.split("\\|")[1]).getText().replaceAll("[^-?0-9]+", " ").trim());
		
		int count =0;
		for (int i = 0; i <=result; i++) {
			//printout("==============>>>>>>>>>>>>>>>>>>>>>>>" + list.get(i).getText());
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 100)).release().perform();
			list = getWebElements(objectkeys.split("\\|")[0]);
			//printout("SEARCH LIST VALUES ARE : "+result+" ---- "+i);
			count=i;
		}
		printout("SEARCH LIST VALUES ARE : "+result+" ---- "+count);
		Assert.assertEquals(result, count);
		return "Pass";
	}

	private String setText(String objectkeys, String datakeys) throws InterruptedException {

		getWebElement(objectkeys).clear();
		Thread.sleep(1000);
		if(maptable.containsKey(datakeys)) {
			driver.getKeyboard().sendKeys(maptable.get(datakeys) + "\n");
			printout("ENTERING THE TEXT VALUE !!!!!!!!!!!!!!!!!!");
			Thread.sleep(1000);
		}
		else {
			printout("ENTERING THE TEXT VALUE !!!!!!!!!!!!!!!!!!");
			driver.getKeyboard().sendKeys(datakeys + "\n");
			Thread.sleep(1000);
		}
		
		Thread.sleep(3000); 
		return "Pass";
	}

	private String selectFromListOfElement(String objectkeys, String datakeys) throws InterruptedException {

		printout("SELECTING THE ELEMENT FROM THE LIST  !!!!!!!!!!!!!");
		Thread.sleep(4000);
		List<MobileElement> productnames = getWebElements(objectkeys);

		boolean isclicked=false;
		
		if (datakeys.contains("|")) {

			for (int i = 0; i < datakeys.split("\\|").length; i++) {

				for (int j = 0; j < productnames.size(); j++) {
					System.out.println("=================>>>>>>"
							+ driver.findElementByXPath("//android.view.View[@index='" + j + "' and @enabled='true']")
									.getText());
					Thread.sleep(1000);

					if (driver.findElementByXPath("//android.view.View[@index='" + j + "' and @enabled='true']")
							.getText().contains(datakeys.split("|")[i])) {

						driver.findElementByXPath("//android.widget.Button[@text='ADD']").click();
						isclicked =true;
						break;
					}
					TouchAction action = new TouchAction(driver);
					action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 100)).release().perform();
					productnames = getWebElements(objectkeys);

				}
			}
		} else {
			//android.view.View[contains(text()='Modern milk bread')]
			for (int i = 0; i <=productnames.size(); i++) {
				
				if(maptable.containsKey(datakeys)) {
					
				List<MobileElement> list =driver.findElements(By.className("android.view.View"));
				
				for(int h=0; h<list.size();h++) {
					
					if (list.get(h).getText()
							.contains(maptable.get(datakeys))) {

						list.get(h).click();
						isclicked =true;
						break;
					}
				}
		
				}
				else {
					if (driver.findElementByXPath("//android.view.View[@index='" + 0 + "' and @enabled='true']").getText()
							.contains(datakeys)) {

						driver.findElementByXPath("//android.view.View[@index='" + 0 + "' and @enabled='true']").click();
						isclicked =true;
						break;
					}
					
				}
				
				if(isclicked ==true) {
					TouchAction action = new TouchAction(driver);
					action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 100)).release().perform();
					productnames = getWebElements(objectkeys);
					break;
				}
				

			}

		}
		return "Pass";
	}

	private String verifyElementList(String objectkeys, String datakeys) throws InterruptedException {

		printout("VERIFY ELEMENT FROM THE LIST !!!!!!!!!!!!!!");
		Thread.sleep(3000);
		List<MobileElement> productnames = getWebElements(objectkeys);
		List<String> templist = new ArrayList<String>();
		System.out.println("+++==================>>>>>>" + productnames.size());
		String dataString;
		if(maptable.containsKey(datakeys))
			dataString=maptable.get(datakeys);
		else
			dataString=datakeys;
		
		for (int i = 0; i < productnames.size(); i++) {
			System.out.println("=================>>>>>>" + productnames.get(i).getText());
			Thread.sleep(1000);
			templist.add( productnames.get(i).getText());
			if(productnames.get(i).getText() .contains(dataString)) {
				//Assert.assertTrue(driver.findElementByXPath("//android.view.View[@index='" + i + "' and @enabled='true']").getText().contains(datakeys),datakeys.toUpperCase()+"ELEMENT IS PRESENT!!!!!");
			break;
			}
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 100)).release().perform();
			productnames = getWebElements(objectkeys);
		
		}
		for(int j=0; j<templist.size(); j++) {
			
			if(templist.get(j).contains(dataString)) {
				Assert.assertTrue(templist.get(j).contains(datakeys),dataString.toUpperCase()+"ELEMENT IS PRESENT!!!!!");
				break;
			}
		}
		return "Pass";
	}

	public String callTestCases(String objectkeys, String datakeys) {

		Hashtable<String, String> data = null;
		if (datakeys.equalsIgnoreCase("Y")) {

			Keywords keywords = new Keywords();
			try {
				keywords.executeKeywords(objectkeys, data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Pass";

	}

	private String waitElementPresent(String objectkeys) {

		printout("WAIT FOR ELEMENT PRESENT !!!!!!!!!!!!!!!!!!");

		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(getWebElement(objectkeys)));
		return "Pass";
	}
	
	private String waitForElementPresent(String objectkeys, String datakeys) {

		printout("WAIT FOR ELEMENT PRESENT !!!!!!!!!!!!!!!!!!");

		WebDriverWait wait = new WebDriverWait(driver,Long.parseLong(datakeys));
		wait.until(ExpectedConditions.visibilityOf(getWebElement(objectkeys)));
		return "Pass";
	}

	private String verifyelementNotDisplayed(String objectkeys) {

		printout("VERIFY ELEMENT IS NOT PRESENT!!!!!!!!!!!!");

		Assert.assertFalse(getWebElements(objectkeys).size() == 1);

		return "Pass";
	}

	public String scrollHorizontal() throws InterruptedException {

		printout("SCROLLING HORIZONTAL METHOD CALLING!!!!!!!!!!!!!!!!!!");

		Dimension dimension = driver.manage().window().getSize();
		int startY = (int) (dimension.height / 2);
		int startX = (int) (dimension.width * 0.90);
		int endX = (int) (dimension.width * 0.05);
		new TouchAction(driver).press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(4))).moveTo(PointOption.point(endX, startY))
				.release().perform();
		Thread.sleep(500);
		return "Pass";
	}

	public String scrollHorizontalUntilElementPresent(String objectkeys) throws InterruptedException {

		printout("SCROLLING HORIZONTAL UNTIL ELEMENT PRESENT METHOD CALLING!!!!!!!!!!!!!!!!!!");

		Dimension dimension = driver.manage().window().getSize();
		int startY = (int) (dimension.height / 2);
		int startX = (int) (dimension.width * 0.90);
		int endX = (int) (dimension.width * 0.05);

		while (getWebElements(objectkeys).size() != 1) {

			new TouchAction(driver).press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(4))).moveTo(PointOption.point(endX, startY))
					.release().perform();
			Thread.sleep(500);
		}

		return "Pass";
	}

	private String compareImage(String objectString, String dataKeys) {

		printout("COMPARING IMAGE METHOD IS CALLING  !!!!!!!!!!!!!!!!!!");

		WebElement ele = getWebElement(objectString);

		System.out.println("------------------------------------------------");
		File scrfile = getWebElement("image").getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrfile,
					new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\report\\image.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		try {
			img1 = ImageIO.read(scrfile);
			img2 = ImageIO.read(new File(System.getProperty("user.dir") + dataKeys));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int w1 = img1.getWidth();
		int w2 = img2.getWidth();
		int h1 = img1.getHeight();
		int h2 = img2.getHeight();
		if ((w1 != w2) || (h1 != h2)) {
			System.out.println("Both images should have same dimwnsions");
		} else {
			long diff = 0;
			for (int j = 0; j < h1; j++) {
				for (int i = 0; i < w1; i++) {
					// Getting the RGB values of a pixel
					int pixel1 = img1.getRGB(i, j);
					Color color1 = new Color(pixel1, true);
					int r1 = color1.getRed();
					int g1 = color1.getGreen();
					int b1 = color1.getBlue();
					int pixel2 = img2.getRGB(i, j);
					Color color2 = new Color(pixel2, true);
					int r2 = color2.getRed();
					int g2 = color2.getGreen();
					int b2 = color2.getBlue();
					// sum of differences of RGB values of the two images
					long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
					diff = diff + data;

				}

			}
		}
		return "Pass";
	}

	public String select_date(String datakeys) throws InterruptedException {

		printout("SELECT THE GIVEN DATES TO THE DATE PICKER OBJECT !!!!!!!!!!!!!!");

		String date = datakeys.split("-")[0];
		String monthyear = datakeys.split("-")[1];
		
		while(!monthyear.equalsIgnoreCase(getWebElement("calendarmonthyeartxt").getText())) {
			getWebElement("calendarnexticon").click();
		}
		for(int i=0; i<getWebElements("calenderdayslist").size();i++) {
			if(getWebElements("calenderdayslist").get(i).getText().equalsIgnoreCase(date)) {
				getWebElements("calenderdayslist").get(i).click();
				getWebElement("calendaokbtn").click();
			}
		}
			return "Pass";

	}

	private String selectDropdown(String objectkeys, String datakeys) {

		printout("SELECT THE VALUE " + datakeys.toUpperCase() + " TO THE DROP DOWN !!!!!!!!!!!!!!!!!!");
		Select dropdown = new Select(getWebElement(objectkeys));
		dropdown.selectByValue(datakeys);
		return "Pass";
	}

	private String searchAndSelect(String objectkeys, String datakeys) {

		printout("SEARCH AND SELECT THE " + datakeys.toUpperCase() + "OPTION !!!!!!!!!!!!!!!!!!!!!");
		String searchbox = objectkeys.split("\\|")[0];
		String suggestionlist = objectkeys.split("\\|")[1];

		getWebElement(searchbox).sendKeys(datakeys);
		for (int i = 0; i < getWebElements(suggestionlist).size(); i++) {
			if (getWebElements(suggestionlist).get(i).getText().equalsIgnoreCase(datakeys)) {

				getWebElements(suggestionlist).get(i).click();
				break;
			}
		}
		return "Pass";
	}

	private String verifyText(String objectkeys, String string) {
		printout("VERIFY THE TEXT " + string.toUpperCase() + " OPTION IS PRESENT ON THE SCREEN !!!!!!!!!!!!!!!!");
		Assert.assertEquals(getWebElement(objectkeys).getText(), string);
		return "Pass";
	}

	private String enter(String objectkeys, String string) throws InterruptedException {

		printout("ENTER THE VALUE " + string.toUpperCase() + " ON GIVEN FIELD !!!!!!!!!!!!!!!!");
		if(objectkeys.contains("|")) {
			Thread.sleep(2000);
			List<MobileElement> elements = getWebElements(objectkeys.split("\\|")[1]);
			for(int i=0; i<elements.size(); i++) {
				
				//elements.get(i).click();
				if(string.contains("|")) {
					elements.get(i).sendKeys(string.split("\\|")[i]);
				}else {
					elements.get(i).sendKeys(string);
				}
				
			}
			
		}
		else {
			getWebElement(objectkeys).sendKeys(string);
		}
		return "Pass";
	}

	private String click(String objectkeys) throws InterruptedException {

		printout("CLICKING ON " + getWebElement(objectkeys).getText()+ " OPTION !!!!!!!!!!");
		Thread.sleep(7000);
		getWebElement(objectkeys).click();
		return "Pass";
	}

	private String goback() {
		printout("NAVIGATING BACK TO THE DEVICE !!!!!!!!!!!!");
		driver.navigate().back();
		return "Pass";
	}

	private String checkAndClick(String objectkeys) {

		printout("CHECK AND CLICKING ON " + getWebElement(objectkeys).getText().toUpperCase() + " OPTION !!!!!!!!!!!!");
		if (getWebElements(objectkeys.split("\\|")[1]).size() == 0)
			getWebElement(objectkeys.split("\\|")[0]).click();
		return "Pass";
	}

	private String verifyElement(String objectkeys) {

		printout("VERIFING THE ELEMENT " + getWebElement(objectkeys).getText().toUpperCase() + " IS PRESENT!!!!!!!!!!!!");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(getWebElement(objectkeys)));
		return "Pass";
	}

	public String scroll_Down(String object) throws InterruptedException {

		printout("UNTIL FOUND THE GIVEN OBJECT KEEP SCROLL DOWN !!!!!!!!!!!!!!!");
		boolean bul = true;

		while (bul != false) {

			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(0, 500)).moveTo(PointOption.point(0, 200)).release().perform();
			Thread.sleep(500);
			bul = getWebElements(object).size() != 0 ? false : true;
		}

		//getWebElement(object).click();
		Thread.sleep(1000);
		return "Pass";

	}

	public String tab(String objectkeys) throws Exception {

		  printout("TAB ON "+getWebElement(objectkeys).getText().toUpperCase()+"!!!!!!!!!!!!!!!");
		  Thread.sleep(5000);
		  TouchAction action = new TouchAction(driver);
		  action.tap(TapOptions.tapOptions().withElement(ElementOption.element(getWebElement(objectkeys)))).waitAction(waitOptions(Duration.ofMillis(250))).perform();
		
		return "Pass";

	}

//Generic function for Scroll
	public String scrollUsingTouchActions_ByElements(String listElement, String data) throws Exception {
		TouchAction actions = new TouchAction(driver);

		List<MobileElement> listofwebelemnt = getWebElements(listElement);

		System.out.println(listofwebelemnt.size());
		// Assert.assertFalse(listofwebelemnt.size()!=0, "Category List is Empty");
		
		String value;
		if(maptable.containsKey(data))
			value=maptable.get(data);
		else
			value=data;
		
		printout(data);
		printout(value);
		printout("--Test : "+maptable.containsKey(data));
		printout("__ "+maptable.get(data));
		
		while(driver.findElementsByXPath("//*[contains(text(),'"+value+"')]").size()!= 1) {
			
			int midOfY = 0;
			int fromXLocation = 0;
			int toXLocation = 0;

			midOfY = listofwebelemnt.get(listofwebelemnt.size() - 1).getLocation().y
					+ (listofwebelemnt.get(listofwebelemnt.size() - 1).getSize().height / 2);
			fromXLocation = listofwebelemnt.get(listofwebelemnt.size() - 1).getLocation().x;
			toXLocation = listofwebelemnt.get(0).getLocation().x;
			
			actions.press(PointOption.point(fromXLocation, midOfY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
					.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();
			
			
			listofwebelemnt = getWebElements(listElement);
			if(driver.findElementsByXPath("//*[contains(@text,'"+value+"')]").size()==1) {
				break;
			}
			
		}
		driver.findElementByXPath("//*[contains(@text,'"+value+"')]").click();
		
		return "Pass";

	}
	
  public String getWeekDayCount(String startDate, String  endDate, String variables) throws ParseException {
		
	  int getText = Integer.parseInt(getWebElement(startDate).getText().replaceAll("[^-?0-9]+", " ").split(" ")[0]);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(simple.parse(endDate.split("\\|")[0]));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd");
		cal1.setTime(simple1.parse(endDate.split("\\|")[1]));
		int workDays=0;
		int weekDays=0;
		do {
		    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		        ++workDays;
		    }
		    else {
		    	++weekDays;
		    }
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.getTimeInMillis() <= cal1.getTimeInMillis());
		maptable.put(variables,String.valueOf(weekDays*getText));
		return "Pass";
	   
	}
	public String getWorkDayCount(String startDate, String  endDate, String variables) throws ParseException {
		
		int getText = Integer.parseInt(getWebElement(startDate).getText().split(".")[0]);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(simple.parse(endDate.split("\\|")[0]));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd");
		cal1.setTime(simple1.parse(endDate.split("\\|")[1]));
		int workDays=0;
		
		do {
		    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		        ++workDays;
		    }
		   
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.getTimeInMillis() <= cal1.getTimeInMillis());
		maptable.put(variables,String.valueOf(workDays*getText));
		return "Pass";
	   
	}
 public String getAllDays(String startDate, String  endDate, String variables) throws ParseException {
		
	    int getText = Integer.parseInt(getWebElement(startDate).getText().split(".")[0]);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(simple.parse(endDate.split("\\|")[0]));
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd");
		cal1.setTime(simple1.parse(endDate.split("\\|")[1]));
		int workDays=0;
		int weekDays=0;
		
		do {
		    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		        ++workDays;
		    }
		    else {
		    	++weekDays;
		    }
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.getTimeInMillis() <= cal1.getTimeInMillis());
		
		maptable.put(variables,String.valueOf((workDays+weekDays+1)*getText));
		return "Pass";
	   
	}
 private String relaunchApp(String dataKeys) throws InterruptedException {

		printout("RELAUNCHING APPLICATION-NO RESET");
		// to_Be_Start_Android_Device(); *****************SAUCE LABS CODE******************
				System.out.println(System.getProperty("os.name"));
				if (isSauceLabsRunnable!=false) {

					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability("testobject_api_key", "AB7E49803834450E97979214568B90CE");
					capabilities.setCapability("testobject_app_id", "2");
					capabilities.setCapability("testobject_session_creation_timeout", "900000");
					

					try {
						driver = new AndroidDriver<MobileElement>(new URL("https://us1.appium.testobject.com/wd/hub"),
								capabilities);
					} catch (MalformedURLException e) {
						System.out.println(e.getMessage());
					}
				} // ************************NORMAL DRIVER INITIALIZATION ************************//
				else {
					appiumStarter();
					Thread.sleep(2000);
					File appDir = new File(System.getProperty("user.dir") + "\\AppFiles\\");
					File app = new File(appDir, "app-debug.apk");

					DesiredCapabilities capabilities = new DesiredCapabilities();
					//capabilities.setCapability("deviceName", "Redmi 6 Pro");
					//capabilities.setCapability("udid", "cb9dcdc506432ec7");
					capabilities.setCapability("deviceName", "Redmi 5A");
					capabilities.setCapability("platformName", "Android");
					capabilities.setCapability("noReset", Boolean.valueOf(dataKeys));
					
					//capabilities.setCapability("automationName", "uiautomator2");
					capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
					
					try {
						 System.out.println("APPIUM SERVICE URL IS : - "+appiumServiceUrl);
						 driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
						 //driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

					} catch (MalformedURLException e) {
						System.out.println(e.getMessage());
					}

				}
				
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				return "Pass";
	}
	
	private String verifySelected(String objectkeys) {
		printout("VERIFY IF THE OBJECT IS SELECTED !!!!!!!!!!!!!!!!");
		Assert.assertTrue(getWebElement(objectkeys).isSelected());
		return "Pass";
	}
	
	private String acceptAlert(String string) {
		printout("VERIFY AND ACCEPT THE ALERT POPUP !!!!!!!!!!!!!!!!");
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.alertIsPresent());
	if( driver.switchTo().alert().getText().contains(string))
		{
		Assert.assertTrue(true);
		}
	else
	{
		Assert.fail();
	}
	driver.switchTo().alert().accept();
	return "Pass";
	}
	
	
	private String denyAlert(String string) {
		printout("VERIFY AND DENY THE ALERT POPUP !!!!!!!!!!!!!!!!");
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.alertIsPresent());
	if( driver.switchTo().alert().getText().contains(string))
	{
	Assert.assertTrue(true);
	}
	else
	{
	Assert.fail();
	}
	driver.switchTo().alert().dismiss();
	return "Pass";
	}
	

}
