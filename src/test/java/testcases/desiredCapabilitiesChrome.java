package testcases;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class desiredCapabilitiesChrome {

	public static void main(String[] args) {

		// it is used to define IE capability
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		capabilities.setPlatform(Platform.WIN10);
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver.exe");

		// it is used to initialize the IE driver
		WebDriver driver = new ChromeDriver(capabilities);

		driver.manage().window().maximize();

		driver.get("http://gmail.com");

		driver.quit();
	}

}