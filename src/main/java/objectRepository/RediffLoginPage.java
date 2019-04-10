package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RediffLoginPage {

	WebDriver driver;

	public RediffLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By Username = By.xpath("//input[@name='login']");
	By Password = By.xpath("//input[@id='password']");
	By Go = By.xpath("//input[@title='Sign in']");
	By Home = By.linkText("Home");

	public WebElement Username() {
		return driver.findElement(Username);
	}

	public WebElement Password() {
		return driver.findElement(Password);
	}

	public WebElement Submit() {
		return driver.findElement(Go);
	}

	public WebElement HomePage() {
		return driver.findElement(Home);
	}
}
