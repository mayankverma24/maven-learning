package testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.RediffHomePage;
import pageObjects.RediffLoginPage;

public class selenium {
	public WebDriver driver;

	@BeforeTest
	public void init() {
		String username = System.getenv("USERNAME");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + username
				+ "\\eclipse-workspace\\selenium-learning\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	}

	@Test
	public void login() {
//		String username = System.getenv("USERNAME");
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + username
//				+ "\\eclipse-workspace\\selenium-learning\\resources\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		RediffLoginPage rd = new RediffLoginPage(driver);

		rd.Username().sendKeys("hello");
		rd.Password().sendKeys("hrello");
		rd.Submit().click();
		rd.HomePage().click();

		RediffHomePage rdh = new RediffHomePage(driver);
		rdh.Searchbox().sendKeys("salman");

		// Soft Assertions using AssertJ library
		SoftAssertions Softly = new SoftAssertions();
		Softly.assertThat(rd.Username().getText()).isEqualTo("Usernmae");
		Softly.assertAll();
		
	}

	@Test
	public void CaptureScreenShot() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Take screen shot
		// Copy the screen shot to the desired location
		FileUtils.copyFile(srcFile, new File("C:\\Gmail.jpg"));
	}

	@AfterTest
	public void tearDown() {
		driver.close(); // close the browser
	}
}
