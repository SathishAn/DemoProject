package utilities;

import java.util.Collections;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

public class BaseTestSetup {
	
	 private static WebDriver driver ;
	 public static WebDriverWait wait = null;
	
	 
	 private enum BrowserType {
	        ie, chrome, firefox;
	    }
	 
	 
	 public static void setDriver(String browserType, String appURL) throws Exception {

	        BrowserType browser_Type = BrowserType.valueOf(browserType.toLowerCase());

	        switch (browser_Type) {
	            case chrome:
	                driver = initChromeDriver(appURL);
	                break;
	            case firefox:
	                //driver = initFirefoxDriver(appURL);
	                break;
	            case ie:
	                System.out.println("browser : " + browserType + " is ie, Launching IE11 as browser of choice..");
	                driver = initIEDriver(appURL);
	                System.out.println(driver.getWindowHandle().toString());
	                break;
	            
	        }
	    }
	 
	 private static WebDriver initChromeDriver(String appURL) throws Exception {
	        try {
	            System.out.println("Launching google chrome with new profile..");
	            try{
	            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	            }
	            catch(Exception e)
	            {
	            	System.out.println("Path not supported");
	            }
	            ChromeOptions chromeOptions = new ChromeOptions();
	            chromeOptions.setExperimentalOption("useAutomationExtension", false);
	            chromeOptions.addArguments("disable-extensions");
	            chromeOptions.addArguments("--start-maximized");
	            chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	            WebDriver driver = new ChromeDriver(chromeOptions);
	            wait = new WebDriverWait(driver, 50);
	            Thread.sleep(60);
	            driver.navigate().to(appURL);

	            return driver;
	        }
	        catch (Exception e) {	           
	            return null;
	        }
	    }
	    
	    /**
	     * This method is to initialize the firefox driver and launch the URL
	     * 
	     * @param Application
	     *            URL
	     * @throws Throwable
	     */
	    /*private static WebDriver initFirefoxDriver(String appURL) throws Exception {
	        System.out.println("Launching Firefox browser..");
	        WebDriver driver = new FirefoxDriver();
	        wait = new WebDriverWait(driver, 50);
	        driver.manage().window().maximize();
	        driver.get(appURL);
	        return driver;
	    }
	*/
	    /**
	     * This method is to initialize the ie driver and launch the URL
	     * 
	     * @param Application
	     *            URL
	     * @throws Throwable
	     */
	    private static WebDriver initIEDriver(String appURL) throws Exception {
	        System.out.println("Launching IE browser..");
	        System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");

	        DesiredCapabilities cap = new DesiredCapabilities();
	        DesiredCapabilities.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	        DesiredCapabilities.internetExplorer().setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.ACCEPT);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);

	        DesiredCapabilities.internetExplorer().setCapability("nativeEvents", false);
	        
	        WebDriver driver = new InternetExplorerDriver(cap);
	        wait = new WebDriverWait(driver, 70);
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        driver.get(appURL);
	        return driver;
	    }
	    
	    /**
	     * This method is to initialize the ie driver and launch the URL
	     * 
	     * @param Application
	     *            URL
	     * @throws Throwable
	     */
	    private static WebDriver initIE_Driver(String appURL) throws Exception {
	        System.out.println("Launching IE browser..");
	        System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer_New.exe");

	        DesiredCapabilities cap = new DesiredCapabilities();
	        DesiredCapabilities.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	        DesiredCapabilities.internetExplorer().setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.ACCEPT);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	        DesiredCapabilities.internetExplorer().setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);

	        DesiredCapabilities.internetExplorer().setCapability("nativeEvents", false);
	        
	        WebDriver driver = new InternetExplorerDriver(cap);
	        wait = new WebDriverWait(driver, 70);
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        driver.get(appURL);
	        return driver;
	    }
	    
	    public static WebDriver getDriver() {
	    	return driver;
	    }
	    
	   
	    
}
