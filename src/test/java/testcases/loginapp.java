package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import objectRepository.RediffHomePage;
import objectRepository.RediffLoginPage;

public class loginapp {

	@Test
	public void login() {
		String username = System.getenv("USERNAME");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + username
				+ "\\eclipse-workspace\\selenium-learning\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		RediffLoginPage rd = new RediffLoginPage(driver);

		rd.Username().sendKeys("hello");
		rd.Password().sendKeys("hrello");
		rd.Submit().click();
		rd.HomePage().click();

		RediffHomePage rdh = new RediffHomePage(driver);
		rdh.Searchbox().sendKeys("salman");
	}
}
