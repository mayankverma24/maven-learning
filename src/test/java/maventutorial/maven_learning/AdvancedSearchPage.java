package maventutorial.maven_learning;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import resources.base;

public class AdvancedSearchPage extends base {

	@Test
	public void baseNavigationPage() throws IOException {
		driver = initializeDriver();
		pageObjects.AdvancedSearchPageObject ASPPageObject = new pageObjects.AdvancedSearchPageObject(driver);
		ASPPageObject.getLogin().click();
		driver.quit();
		Set<String> win = driver.getWindowHandles();
	}

}