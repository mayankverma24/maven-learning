package maventutorial.maven_learning;

import java.io.IOException;

import org.testng.annotations.Test;

import resources.base;

public class AdvancedSearchPage extends base {

	@Test
	public void baseNavigationPage() throws IOException {
		driver = initializeDriver();
		pageObjects.AdvancedSearchPageObject ASPPageObject = new pageObjects.AdvancedSearchPageObject(driver);
		ASPPageObject.getLogin().click();
		driver.quit();
	}

}