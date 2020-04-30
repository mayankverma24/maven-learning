package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class stepDefinition {

	@Given("^User is on NetBanking landing page$")
	public void user_is_on_netbanking_landing_page() throws Throwable {
		System.out.println("Navigated to Login page");
	}

	@When("^User login into application with (.+) and password (.+)$")
    public void user_login_into_application_with_and_password(String username, String password) throws Throwable {
    	System.out.println(username);
    	System.out.println(password);
    }

	@Then("^Home page is displayed$")
	public void home_page_is_displayed() throws Throwable {
		System.out.println("Home Page is displayed");

	}

	@And("^Cards displayed are (.+)$")
	public void cards_displayed_are(String result) throws Throwable {
    	System.out.println(result);

	}

	
}