package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pageObjects.RediffHomePage;
import pageObjects.RediffLoginPage;

public class loginapp {

	@Test
	public void login() {
		//String username = System.getenv("USERNAME");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mayan\\eclipse-workspace\\selenium-learning\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		
		RediffLoginPage LoginPageObject = new RediffLoginPage(driver);
		RediffHomePage HomePageObject = new RediffHomePage(driver);

		LoginPageObject.Username().sendKeys("hello");
		LoginPageObject.Password().sendKeys("hrello");
		LoginPageObject.Submit().click();
		LoginPageObject.HomePage().click();
		HomePageObject.Searchbox().sendKeys("salman");
		//HomePageObject.Password().clear();
	}
}
