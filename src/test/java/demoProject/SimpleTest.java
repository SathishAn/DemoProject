package demoProject;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.DataTestProvider;


public class SimpleTest {
	public static void main(String[] args) throws IOException {
		Map<String, String> datas;
		DataTestProvider dp = new DataTestProvider(); 
		dp.readTestData("Test001", "Credential"); 
		datas = dp.getTestData();
		String appURL = "https://github.com/login";
		String chromeDriverExe ="D:\\Workspace\\Demo\\drivers\\chromedriver.exe";	 
 		System.setProperty("webdriver.chrome.driver", chromeDriverExe); 
 		WebDriver driver = new ChromeDriver();
 		driver.manage().window().maximize();
 		driver.manage().deleteAllCookies();
 		driver.navigate().to(appURL);
 		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 		driver.findElement(By.id("login_field")).sendKeys(datas.get("UserName"));
 		driver.findElement(By.id("password")).sendKeys(datas.get("Password"));
 		
	}
	
}
