package testDataSetup;

import java.io.IOException;

import org.junit.runner.Result;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import modules.ReusableModule;
import pages.LoginPage;
import utilities.CoreTapWrappers;


public class TD001_Login extends CoreTapWrappers{

	@BeforeClass
	public void setData() {
		testCaseName="Test001";
		dataSheetName = "TD_Login";
		browserName= "chrome";
		testDescription = "Login to Screen";
		category="Smoke";
		authors="Sathish";
	}

	@Test(dataProvider="fetchData")
	public void login(String scenario, String userName, String password) throws IOException{
		new LoginPage(getDriver())
		.enterUserName(userName)
		.enterPassword(password)
		.clickLogin();
		
		

	}

}
