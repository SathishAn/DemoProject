package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericWrapper extends Reporter implements Wrapper {
	private  WebDriver driver;
	
	public GenericWrapper() {
        this.driver = getDriver();        
   }
	
	
	
	public String primaryWindowHandle;
	public static WebDriverWait wait;
	public ConfigFileReader config = new ConfigFileReader();
	
	

	/* ---------------------------------------------------------------------
    Method Name: invokeApp
    Description: This method will launch the given browser and maximize the browser and set the wait for 30 seconds and load the url.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/	
	public void invokeApp( String application, String browser) {
		// TODO Auto-generated method stub
		boolean bRemote = false;
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser.toLowerCase());
		dc.setPlatform(Platform.WINDOWS);
		
		String Url = config.getPropertyValue(application);
		
		
		try {
			setDriver(browser, Url);	
			
			reportStep("Success: '" + application + " Application'  is launched in "+ browser + " browser", "PASS");
			System.out.println("The browser " + browser + " launched successfully.");
		} catch (Exception e) {
			
			reportStep("Failed: 'T24 Application'  is not able launch in "+ browser+ " browser", "PASS", false);
			System.out.println("The Browser is unexpectedly closed"+ e.getMessage());
			
		} 

	}	
	
	
	
	
	/* ---------------------------------------------------------------------
    Method Name: enterInputText
    Description: This method will enter the value to the text field using locators
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void enterInputText(WebElement element, String data, String fieldName) {
		// TODO Auto-generated method stub
		try {
			element.clear();
			element.sendKeys(data);
			reportStep("Success: '"+data+"'  is entered  in "+ fieldName.toUpperCase() + " field", "PASS");		
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportStep("Failed:Object Issue. '"+data+"' is not able to enter in "+ fieldName.toUpperCase() + " field", "FAIL");			
			System.out.println("The element '" + fieldName + " is not present in the application");
		} catch (WebDriverException e) {
			reportStep("Failed:Object Issue. " + e.getMessage() , "FAIL");
			System.out.println("The Browser is unexpectedly closed");
		} 

	}
	/* ---------------------------------------------------------------------
    Method Name: verifyTitle
    Description: This method will verify the title of the browser
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public boolean verifyTitle(String title) {
		// TODO Auto-generated method stub
		boolean bReturn = false;
		try {
			if (driver.getTitle().equalsIgnoreCase(title)) {
				reportStep("Success:The title of the page matches with the value : " + title, "PASS");
				bReturn = true;
			} else
				reportStep("Failed:The title of the page:" + driver.getTitle() + " did not match with the value :" + title,
						"FAIL");

		} catch (Exception e) {
			reportStep("Failed:Unknown exception occured while verifying the title", "FAIL");
		}
		return bReturn;
	}
	/* ---------------------------------------------------------------------
    Method Name: verifyText
    Description: This method will verify the given text with exact match.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void verifyText(WebElement element, String text) {
		// TODO Auto-generated method stub
		
		if(element.getText().equalsIgnoreCase(text)) {
			reportStep("Success:The Element Text" + element.getText() + " Matches The text" + text,	"PASS");
		}else {
		    
		    System.out.println("It doesn't contains the expected text");
		reportStep("Failed:The Element Text" + element.getText() + " does not Matches with the text" + text,
				"Fail");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: verifyTextContains
    Description: This method will verify the given text with partial match.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void verifyTextContains(WebElement element, String text) {
		// TODO Auto-generated method stub
		if(element.getText().contains(text))
		{
		System.out.println("Text is Present");
		reportStep("Success:The Text in the element" + element.getText() + " contains the text" + text,
				"PASS");
		}
		else
		{
		System.out.println("Text is not Present");
		reportStep("Failed:The Text in the element" + element.getText() + " does not contains the text" + text,
				"Fail");
		}
	}
	

	/* ---------------------------------------------------------------------
    Method Name: webElementClick
    Description: This method will click the element using a locator
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void webElementClick(WebElement element, String fieldName) {
		// TODO Auto-generated method stub
		try {
			element.click();
			reportStep("Success:'" + fieldName.toUpperCase() + "' is clicked.", "PASS");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			reportStep("Failed:Object Issue. Not able to click '" + fieldName.toUpperCase() + "' field", "FAIL");
		} 

	}
	/* ---------------------------------------------------------------------
    Method Name: getTextWebElement
    Description: This method will get the text of the element using locator
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public String getTextWebElement(WebElement element, String fieldName) {
		// TODO Auto-generated method stub
		StringBuffer tempValue = new StringBuffer();
		try {
			tempValue.append(element.getText());
			reportStep( "Success:The '" + tempValue.toString() + "' is reterived from " + fieldName.toUpperCase() + " field", "PASS");
		} catch (NoSuchElementException e) {
			reportStep("Failed:The element '" + fieldName + "' is not present in the application", "FAIL");
		} 

		return tempValue.toString();
	}
	/* ---------------------------------------------------------------------
    Method Name: selectVisibileText
    Description: This method will select the drop down visible text using locator
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void selectVisibileText(WebElement element, String value, String fieldName) {
		// TODO Auto-generated method stub
		try {
			new Select(element).selectByVisibleText(value);
			reportStep("Success:The '"+ value +"' is selected from "+ fieldName.toUpperCase() + " field" , "PASS");
		} catch (Exception e) {
			reportStep("Failed:Object Issue." + value + " is not able to select from "+ fieldName.toUpperCase()+ " field" , "FAIL");
		}

	}
	/* ---------------------------------------------------------------------
    Method Name: selectByValue
    Description: This method will select the drop down visible value using locator
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/
	public void selectByValue(WebElement element, String value, String fieldName) {
		// TODO Auto-generated method stub
		try {
			new Select(element).selectByValue(value);
			reportStep("Success:The '"+ value +"' is selected from "+ fieldName.toUpperCase() + " field" , "PASS");
		} catch (Exception e) {
			reportStep("Failed:Object Issue." + value + " is not able to select from "+ fieldName.toUpperCase() + " field" , "FAIL");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: selectByIndex
    Description: This method will select the drop down using index as locator
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/
	public void selectByIndex(WebElement element, int value,String fieldName) {
		// TODO Auto-generated method stub
		try {
			new Select(element).selectByIndex(value);
			reportStep("Success:The '"+ value +"' is selected from "+ fieldName.toUpperCase() + " field" , "PASS");
		} catch (Exception e) {
			reportStep("Failed:Object Issue." + value + " is not able to select from "+ fieldName.toUpperCase() + " field" , "FAIL");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: switchToLastWindow
    Description: This method will move the control to the last window.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void switchToLastWindow() {
		// TODO Auto-generated method stub
		try {			
			
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
			}
			reportStep("Success: Switched to the new window " + driver.getTitle(), "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Failed:The window could not be switched to the last window.", "FAIL");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: switchToParentWindow
    Description: This method will switch to the parent Window
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void switchToParentWindow() {
		// TODO Auto-generated method stub
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
				reportStep("Success: Switched to the Parent Window", "PASS");
				break;
			}
		} catch (Exception e) {
			reportStep("Failed:The window could not be switched to the first window.", "FAIL");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: acceptAlert
    Description: This method will accept the alert opened
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void acceptAlert() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Alert");
			driver.switchTo().alert().accept();
			reportStep("Success: Alert displayed in the page.", "PASS");
		} catch (NoAlertPresentException e) {
			reportStep("Failed:Alert not present in the page.", "FAIL");
		} catch (Exception e) {
			reportStep("Failed:Alert could not be accepted.", "FAIL");
		}
	}
	
	/* ---------------------------------------------------------------------
    Method Name: dismissAlert
    Description: This method will dismiss the alert opened
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void dismissAlert() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Alert");
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			reportStep("Failed:Alert not present in the page.", "FAIL");
		} catch (Exception e) {
			reportStep("Failed:Alert could not be accepted.", "FAIL");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: dismissAlert
    Description: This method will dismiss the alert opened
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public String getAlertText() {
		// TODO Auto-generated method stub
		StringBuffer tempValue = new StringBuffer();

		try {
			System.out.println("Alert");
			tempValue.append(driver.switchTo().alert().getText());
		} catch (NoAlertPresentException e) {
			reportStep("Failed:Alert not present in the page.", "FAIL");
		} catch (Exception e) {
			reportStep("Failed:Alert could not be accepted.", "FAIL");
		}
		return tempValue.toString();
	}
	
	/* ---------------------------------------------------------------------
    Method Name: closeBrowser
    Description: This method will close the active browser.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void closeBrowser() {
		// TODO Auto-generated method stub
		getDriver().close();

	}
	/* ---------------------------------------------------------------------
    Method Name: closeAllBrowsers
    Description: This method will close all the browsers of the given execution session
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void closeAllBrowsers() {
		// TODO Auto-generated method stub
		getDriver().quit();

	}
	/* ---------------------------------------------------------------------
    Method Name: webElementWait
    Description: This method will wait for the element.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void webElementWait(WebElement element, String syncValue, String waitType) {
		wait = new WebDriverWait(driver, Integer.parseInt(syncValue));
		try {
			switch (waitType.toLowerCase()) {
			case "visible":
				wait.until(ExpectedConditions.visibilityOf(element));
				break;
			case "clickable":
				wait.until(ExpectedConditions.elementToBeClickable(element));
				break;

			case "selectable":
				wait.until(ExpectedConditions.elementToBeSelected(element));
				break;
				
			case "stale element":
				try {
					element.sendKeys(Keys.TAB);;
				}catch(StaleElementReferenceException e) {
					wait.until(ExpectedConditions.stalenessOf(element));
				}
					
			default:
				wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
				break;
			}

		} catch (Exception e) {

		}

	}
	/* ---------------------------------------------------------------------
    Method Name: mouseOver
    Description: This method is used to move the mouse cursor to the webelement.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void mouseOver(WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			reportStep("Success:Mouse over operation is performed.", "PASS");
		} catch (Exception e) {
			reportStep("Failed:Mouse over operation could not be performed.", "FAIL");
		}
	}
	/* ---------------------------------------------------------------------
    Method Name: takeSnap
    Description: This method is used to capture the screen.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public long takeSnap() {
		// TODO Auto-generated method stub
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE),
					new File(reportPath+"/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			reportStep("Failed:The browser has been closed.", "FAIL");
		} catch (IOException e) {
			reportStep("Warning:The snapshot could not be taken", "WARN");
		}
		return number;

	}
	
	/* ---------------------------------------------------------------------
    Method Name: actionDoubleClick
    Description: This method is used to move the mouse cursor and double click on the webelement.
    Author: Core Banking Automation Team
	------------------------------------------------------------------------*/

	public void actionDoubleClick(WebElement element, String fieldName) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).doubleClick().build().perform();
			reportStep("Success:Double click operation is performed'" + fieldName + "'", "PASS");
		} catch (Exception e) {
			reportStep("Failed:Not able to perform double click operation.", "FAIL");
		}
	}
	
	public void captureScreenshot(String sTempValue) {
		reportStep(sTempValue+" Screenshot", "INFO");
	}


	public void selectByValue(WebElement element, String value) {
		// TODO Auto-generated method stub
		
	}


	public void selectByIndex(WebElement element, int value) {
		// TODO Auto-generated method stub
		
	}


	public void selectRadioButton(List<WebElement> element, String value, String fieldName) {
		// TODO Auto-generated method stub
		int bFlag = 0;
		try {
			for (WebElement ele : element) {
				if(ele.getAttribute("value").equalsIgnoreCase(value)) {
					ele.click();
					reportStep("Success: '" +value+"'  is selected from  "+ fieldName.toUpperCase() + " field", "PASS");
					bFlag=1;
					break;
				}
			}
			
			if(bFlag != 1) {
				reportStep("Failed: '" +value+"'  is not available in the  "+ fieldName.toUpperCase()+ " field", "FAIL");
			}
			
		} catch (Exception e) {
			reportStep("Failed Object Issue: '" +value+"'  is not able to select from  "+ fieldName.toUpperCase()+ " field", "FAIL");
		}
		
	}


	public void selectCheckBox(List<WebElement> element, String value, String fieldName) {
		// TODO Auto-generated method stub
		
	}


	public void wait(int seconds) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void sample() {
		// TODO Auto-generated method stub
		
	}
	

	
}
