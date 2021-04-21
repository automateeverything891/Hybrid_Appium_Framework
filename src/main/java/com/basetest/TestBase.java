package com.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.mail.SendMail;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.Xls_Reader;

import httpmethods.DELETE;
import httpmethods.GET;
import httpmethods.PATCH;
import httpmethods.POST;
import httpmethods.PUT;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class TestBase {

	public static AppiumDriver<MobileElement> driver;

	AppiumDriverLocalService appiumservice;

	public static String appiumServiceUrl;

	public boolean isSauceLabsRunnable = false;

	public Properties prop = null;

	public FileInputStream filein = null;

	public static Hashtable<String, String> maptable = new Hashtable<String, String>();

	public Xls_Reader xls = new Xls_Reader(
			System.getProperty("user.dir") + "\\src\\main\\java\\com\\testdata\\TestSuite1.xlsx");
	public Logger logger = Logger.getLogger(TestBase.class);

	public static ExtentReports report;

	public static ExtentTest extentTest;

	public static int aStatusRowNum;
	public static Hashtable<String, String> testcasesummary = new Hashtable<String, String>();

	public String token;

	public String token2;

	public static String token3;

	public static GET get;

	public static POST post;

	public static PUT put;

	public static DELETE delete;

	public static PATCH patch;

	static {
		// extent report configuration
		report = new ExtentReports(System.getProperty("user.dir") + "\\src\\main\\java\\com\\report\\extentreport.html",
				true);

		PropertyConfigurator.configure("log4j.properties");

		try {
			report.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName())
					.addSystemInfo("USER NAME", "TESTING TEAM").addSystemInfo("PROJECT NAME", "FALCON");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Lunching the app and configure the emulator using desire capabilities.
	@BeforeClass
	public void lunchingApp() throws IOException, InterruptedException {
		// to_Be_Start_Android_Device(); *****************SAUCE LABS
		// CODE******************
		Thread.sleep(20000);
		System.out.println(System.getProperty("os.name"));
		if (isSauceLabsRunnable != false) {

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
		} // ************************NORMAL DRIVER INITIALIZATION
			// ************************//
		else {
			// appiumStarter();
			Thread.sleep(2000);
			File appDir = new File(System.getProperty("user.dir") + "\\AppFiles\\");
			File app = new File(appDir, "app-debug.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			// capabilities.setCapability("deviceName", "Redmi 6 Pro");
			// capabilities.setCapability("udid", "cb9dcdc506432ec7");
			capabilities.setCapability("deviceName", "Redmi 6 pro");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("noReset", false);
			// capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);
			// capabilities.setCapability("noSign", true);
			// capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 6000);
			// capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			// driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);
			/*
			 * capabilities.setCapability("unicodeKeyboard", true);
			 * capabilities.setCapability("resetKeyboard", true);
			 * capabilities.setCapability("platformVersion", "10.0.0");
			 * capabilities.setCapability("appPackage", "com.totient.falcon");
			 * capabilities.setCapability("appActivity", "com.totient.falcon.MainActivity");
			 */
			// driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);

			// "http://127.0.0.1:4723/wd/hub"

			try {
				System.out.println("APPIUM SERVICE URL IS : - " + appiumServiceUrl);
				driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
				// driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				// capabilities);

			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}

		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		get = new GET();

		post = new POST();

		put = new PUT();

		delete = new DELETE();

		patch = new PATCH();

		// GETTING TOKEN API
		post.get_response_header_authantication_login_token(
				"http://falcon-dev.totient.in:8081/falcon/api/authenticate/token",
				"{\"actionType\":\"AT\",\"signature\":\"iD1DMD3aIh\",\"clientType\":\"ADMIN_WEB\",\"deviceId\":\"90GWN5Cm3I\",\"clientId\":null,\"ipAddress\":\"\",\"mobile\":\"0000111111\",\"code\":null,\"additionalInfo\":{},\"rememberMe\":false}");
		post.get_response_header_authantication("http://falcon-dev.totient.in:8081/falcon/api/authenticate",
				"{\"actionType\":\"SMO\",\"signature\":\"x0bgIMhCjK\",\"clientType\":\"ADMIN_WEB\",\"deviceId\":\"Iqo6xiGtlm\",\"clientId\":null,\"ipAddress\":\"\",\"mobile\":\"0000111111\",\"code\":null,\"additionalInfo\":{},\"rememberMe\":false}");
		post.get_response_header_authantication("http://falcon-dev.totient.in:8081/falcon/api/authenticate",
				"{\"actionType\":\"AMO\",\"signature\":\"x0bgIMhCjK\",\"clientType\":\"ADMIN_WEB\",\"deviceId\":\"Iqo6xiGtlm\",\"clientId\":\"sdgffssdfh\",\"ipAddress\":\"\",\"mobile\":\"0000111111\",\"code\":\"0000\",\"additionalInfo\":{},\"rememberMe\":false}");
		post.get_response_header_authantication("http://falcon-dev.totient.in:8081/falcon/api/authenticate",
				"{\"actionType\":\"RT\",\"clientType\":\"ADMIN_WEB\",\"clientId\":\"sdgffssdfh\",\"allClient\":false}");

	}

	@BeforeMethod
	public void beforeMethod(Method result) {

		extentTest = report.startTest(result.getName());

	}

	// Appium server will start on any free port automatically.
	public void appiumStarter() {

		TestBase tb = new TestBase();
		PropertyConfigurator.configure("log4j.properties");

		appiumservice = new AppiumServiceBuilder().usingAnyFreePort().withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.withArgument(GeneralServerFlag.RELAXED_SECURITY).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.build();
		if (tb.checkIfServerIsRunnning(appiumservice.getUrl().getPort())) {
			appiumservice.stop();
		} else {
			appiumservice.start();
			appiumServiceUrl = appiumservice.getUrl().toString();
			System.out.println("Appium is Started!!!");
		}

		// System.out.println("Appium Service Address : - " + appiumServiceUrl);

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		try {
			getStatus(result);
			report.endTest(extentTest);
			report.flush();
		} catch (Exception e) {
			System.out.println("Appium server connection exception");
		}
	}

	public void printout(String value) {

		logger.debug(value);
		extentTest.log(LogStatus.INFO, value);
	}

	private void getStatus(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			xls.setCellData1("Test Cases", "Status", aStatusRowNum - 1, "PASS");
			// xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test
			// Data", "Status", "PASS");
			// xls.setCellDataInparticularCell("TC_FCA_1_Validate_Launching_Splash_Screen",
			// "Test Cases", "Status", "PASS");
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(catureScreen()));
			testcasesummary.put(result.getTestClass().getName(), "PASS");

		} else if (result.getStatus() == ITestResult.FAILURE) {

			xls.setCellData1("Test Cases", "Status", aStatusRowNum - 1, "FAIL");
			// xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test
			// Data", "Status", "FAIL");
			extentTest.log(LogStatus.ERROR, result.getName() + "test is failed " + result.getThrowable());
			testcasesummary.put(result.getTestClass().getName(), "FAIL");
			extentTest.log(LogStatus.FAIL,
					result.getName() + "test is failed " + extentTest.addScreenCapture(catureScreen()));

		} else if (result.getStatus() == ITestResult.SKIP) {

			xls.setCellData1("Test Cases", "Status", aStatusRowNum - 1, "SKIP");
			// xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test
			// Data", "Status", "SKIP");
			extentTest.log(LogStatus.SKIP, result.getName() + "test is skip " + result.getThrowable());
			testcasesummary.put(result.getTestClass().getName(), "SKIP");

		} else if (result.getStatus() == ITestResult.STARTED) {
			extentTest.log(LogStatus.INFO, result.getName() + " Test is Started");
		}
	}

	public String catureScreen() {

		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			destFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\report\\"
					+ simpleformat.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}

	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	@BeforeSuite
	public void startAppium() {

		appiumStarter();
		
	}

	@AfterSuite
	public void endReport() {

		
		driver.closeApp();
		// driver.terminateApp("io.appium.android.apis");
		if (isSauceLabsRunnable == false) {
			appiumservice.stop();
		} else {
			if (isSauceLabsRunnable == false && driver != null)
				driver.quit();
		}
		//SendMail.custom_Mail();
	}

	@AfterClass
	public void closeApp() {

		try {
			log();

		} catch (Exception e) {

			System.out.println("Appium connection execptions");
		}

	}

	public void loadproperties() throws IOException {

		try {
			prop = new Properties();
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\flashscreen.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\dashboard.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\config\\config.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\ordercalendar.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\productpage.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\searchlisting.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\loginpage.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\editprofilepage.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\cartpage.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\mysubscription.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\moremenu.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\logoutlandingpage.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\subscriptionpage.properties");
			prop.load(filein);
			filein = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\pompages\\onlinepaymentpage.properties");
			prop.load(filein);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public MobileElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		if (locatorType.toLowerCase().equals("id"))
			return (MobileElement) driver.findElementById(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return (MobileElement) driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return (MobileElement) driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return (MobileElement) driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return (MobileElement) driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return (MobileElement) driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return (MobileElement) driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return (MobileElement) driver.findElement(By.xpath(locatorValue));
		else if (locatorType.toLowerCase().equals("androiduiautomator"))
			return (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	@SuppressWarnings("unchecked")
	public List<MobileElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public MobileElement getWebElement(String locator) {
		try {
			return getLocator(prop.getProperty(locator));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<MobileElement> getWebElements(String locators) {
		try {
			return getLocators(prop.getProperty(locators));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public  void log() {

		LogEntries entireLogBuffer = driver.manage().logs().get("server");
		Iterator<LogEntry> logIter = entireLogBuffer.iterator();
		while (logIter.hasNext()) {
			LogEntry entry = logIter.next();
			System.out.println(entry.getMessage());
			logger.info(entry.getMessage());
		}

	}

	// This method will start the given emulator
	private void to_Be_Start_Android_Device() {

		try {
			Runtime.getRuntime().exec("emulator @Nexus6");
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
