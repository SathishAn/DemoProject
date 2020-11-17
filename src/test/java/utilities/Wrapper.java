package utilities;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public interface Wrapper {
	
	public WebDriver driver= null;
	
	/**
	 * This method will launch the given browser and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Sathish A
	 * @param browser, url 
	 */
	public void invokeApp(String application, String browser);
	
	/**
	 * This method will enter the value to the text field using locators
	 * @param element - The locator of the object
	 * @param data - The data to be sent to the webelement
	 * @author Sathish A
	 * @return 
	 */
	public void enterInputText(WebElement element, String data, String fieldName);
	
	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Sathish A
	 * @return 
	 */
	public boolean verifyTitle(String title);
	
	/**
	 * This method will verify the given text with exact match
	 * @param element - The locator of the object 
	 * @param text  - The text to be verified
	 * @author Sathish A
	 */
	public void verifyText(WebElement element, String text);
	
	/**
	 * This method will verify the given text with partial match
	 * @param element - The locator of the object
	 * @param text  - The text to be verified
	 * @author Sathish A
	 */
	public void verifyTextContains(WebElement element, String text);
	
	/**
	 * This method will click the element using a locator
	 * @param element -  The locator of the element to be clicked
	 * @author Sathish A
	 */
	public void webElementClick(WebElement element, String FieldName);
	
	/**
	 * This method will get the text of the element using locator
	 * @param element  The locator of the element 
	 * @author Sathish A
	 */
	public String getTextWebElement(WebElement element, String FieldName);
	
	/**
	 * This method will select the drop down visible text using locator
	 * @param element The locator of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Sathsh A
	 */
	public void selectVisibileText(WebElement element, String value, String fieldName);
	
	/**
	 * This method will select the drop down visible text using locator
	 * @param element The locator of the drop down element
	 * @param value The value to be selected (value) from the dropdown 
	 * @author Sathsh A
	 */
	public void selectByValue(WebElement element, String value);
	
	/**
	 * This method will select the drop down using index as locator
	 * @param element The (locator) of the drop down element
	 * @param value The value to be selected (Index) from the dropdown 
	 * @author Sathish A
	 */
	public void selectByIndex(WebElement element, int value);
	
	/**
	 * This method will move the control to the last window
	 * @author Sathish A
	 */
	public void switchToLastWindow();
	
	
	
	
	
	/**
	 * This method will switch to the parent Window
	 * @author Sathish A
	 */
	public void switchToParentWindow();
	
	/**
	 * This method will accept the alert opened
	 * @author Sathish A
	 */
	public void acceptAlert();	
	
	/**
	 * This method will dismiss the alert opened
	 * @author Sathish A
	 */
	public void dismissAlert();	
	
	/**
	 * This method will get the text from the opened alert
	 * @author Sathish A
	 */
	public String getAlertText();	
	
	/**
	 * This method will close the active browser
	 * @author Sathish A
	 */
	public void closeBrowser();
	
	
	/**
	 * This method will close all the browsers of the given execution session
	 * @author Sathish A
	 */
	public void closeAllBrowsers();
	
	/**
	 * This method will wait for the element
	 * @param element The (locator) of the drop down element
	 * @author Sathish A
	 */
	public void webElementWait(WebElement element, String value, String waitType);
	
	/**
	 * This method is used to move the mouse cursor to the webelement.
	 * @param element The (locator) of the element
	 * @author Sathish A
	 */
	public void mouseOver(WebElement element);
	
	/**
	 * This method is used to move the mouse cursor and double click on the webelement.
	 * @param element The (locator) of the element
	 * @author Sathish A
	 */
	
	public void actionDoubleClick(WebElement element, String fieldName);


	/**
	 * This method is used to select the radio button option
	 * @param element The (locator) of the element
	 * @author Sathish A
	 */
	
	public void selectRadioButton(List<WebElement> element, String value, String fieldName);
	
	/**
	 * This method is used to select the check box option
	 * @param element The (locator) of the element
	 * @author Sathish A
	 */
	
	public void selectCheckBox(List<WebElement> element, String value, String fieldName);
	
	/**
	 * This method is used to select the check box option
	 * @param element The (locator) of the element
	 * @author Sathish A
	 */
	
	public void wait(int seconds);
	
	
	
	public void sample();
	
	
	
}
