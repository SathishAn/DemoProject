package modules;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.it.Ma;
import pages.LoginPage;


public class ReusableModule {
		private Map<String, String> testData = new HashMap<String, String>();
		private WebDriver driver;
	public ReusableModule(WebDriver driver ,Map<String, String> testData) {
		this.testData = testData;
		this.driver = driver;
	}
	
	public void login() {
		new LoginPage(this.driver)
		.enterUserName(this.testData.get("UserName"))
		.enterPassword(this.testData.get("Password"))
		.clickLogin();		
	}
}
