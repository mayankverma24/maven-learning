package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RediffHomePage {

	WebDriver driver;

	public RediffHomePage(WebDriver driver) {
		this.driver = driver;
	}

	By Search = By.xpath("//input[@id='srchword']");

	public WebElement Searchbox() {
		return driver.findElement(Search);
	}
}
