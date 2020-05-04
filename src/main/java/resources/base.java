package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {
	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\mayan\\eclipse-workspace\\maven-learning\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// "C://drivers//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			// System.setProperty("webdriver.firefox.driver", "");
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			// System.setProperty("webdriver.edge.driver", "");
			driver = new EdgeDriver();
		}
		String pageURL = prop.getProperty("url");
		driver.get(pageURL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
}
