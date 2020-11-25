package wrappers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

import utilities.ExcelUtils;


public class CoreTapWrappers extends GenericWrapper {
	
	public WebDriver driver;

	public String browserName;
	public String dataSheetName;
	public String testcaseSheetName;
	public Scenario scenario;
	public String testDescription;
		
	public static Map<String, String> excelHashMapValues = new HashMap<String, String>();
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(){
		
		startResult();
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite(){
		endResult();
		
		
		
	}		
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(){
		test = startTestCase(testCaseName, testDescription);		
		test.assignAuthor(authors);
		invokeApp("URL", browserName);
	}	
	

	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(){
		endTestcase();
		//quitBrowser();
		
	}
	
	
	public void getData(String scenarioName , String sheetName) throws IOException{		  
		 ExcelUtils.getTableArray(scenarioName, sheetName);	
		 excelHashMapValues = ExcelUtils.getTestData();
	}	
	
	
	
	
	
	
}
