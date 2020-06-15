package selenium;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import resources.base;

public class WindowHandle extends base {

	@Test
	public void baseNavigationPage() throws IOException {
		driver = initializeDriver();
		pageObjects.AdvancedSearchPageObject ASPPageObject = new pageObjects.AdvancedSearchPageObject(driver);
		ASPPageObject.getLogin().click();

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			System.out.println(handle);
			driver.switchTo().window(handle);			
		}
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		

		driver.quit();
	}

}
