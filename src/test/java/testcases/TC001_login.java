package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import modules.ReusableModule;
import wrappers.CoreTapWrappers;



public class TC001_login extends CoreTapWrappers {
	@BeforeClass(alwaysRun=true)
	public void setData() {
		testCaseName="Test001";		
		dataSheetName = "TD_Login";
		testDescription = "Login to Screen";
		browserName= "Chrome";
		authors="Sathish";
	}
	

	@Test(invocationCount = 2, groups = {"smokeTest"})
	public void login() throws IOException{			
		System.out.println("Test1");
		getData(this.testCaseName, this.dataSheetName);
		ReusableModule rs = new ReusableModule(getDriver(),excelHashMapValues);
		rs.login();

	}
	
}
