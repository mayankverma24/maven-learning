package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RediffHomePage {

	WebDriver driver;

	public RediffHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='srchword']")
	WebElement search;

	@FindBy(xpath = "//input[@id='srchword']")
	WebElement password;
	
	// By Search = By.xpath("//input[@id='srchword']");

	public WebElement Searchbox() {
		// return driver.findElement(Search);
		return search;
	}
	
	public WebElement Password() {
		return password;
	}
}
