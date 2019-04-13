package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webdriverClass {
	private  WebDriver driver;

	@BeforeClass
	public  void setupClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
}
