package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import modules.ReusableModule;
import utilities.CoreTapWrappers;

public class TC001_login extends CoreTapWrappers{
	@BeforeClass
	public void setData() {
		testCaseName="Test001";
		browserName= "chrome";
		dataSheetName = "TD_Login";
		testDescription = "Login to Screen";
		category="Smoke";
		authors="Sathish";
	}

	@Test(invocationCount = 2)
	public void login() throws IOException{
		getData(this.testCaseName, "TD_Login");
		ReusableModule rs = new ReusableModule(getDriver(),excelHashMapValues);
		rs.login();
		
		
		

	}
}
