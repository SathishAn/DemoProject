package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import utilities.CoreTapWrappers;

public class LoginPage extends CoreTapWrappers  {
	
	public LoginPage(WebDriver driver){
		this.driver = driver;		
		PageFactory.initElements(this.driver, this);
		if(!verifyTitle("Sign in to GitHub · GitHub")){
			reportStep("This is not Login Page", "FAIL");
		}
		
	}
	@FindBy(how=How.ID, using="login_field") WebElement userName;
	
	@FindBy(how=How.ID, using="password") WebElement password;
	
	@FindBy(how=How.ID, using="Sign_in") WebElement signIn;
	
	public LoginPage enterUserName(String userName){
		enterInputText(this.userName, userName, "UserName");
		return this;
	}
	
	public LoginPage enterPassword(String password){
		enterInputText(this.password, password, "Password");		
		return this;
	}
	
	public LoginPage clickLogin(){
		webElementClick(this.signIn, "Sign-in");	
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
