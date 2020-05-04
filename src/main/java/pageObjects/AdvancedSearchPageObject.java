package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdvancedSearchPageObject {
	public WebDriver driver;

	public AdvancedSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	By login = By.xpath("/html/body/div[2]/div[1]/div[4]/ul[1]/li[2]/a");

	public WebElement getLogin() {
		return driver.findElement(login);
	}
}
