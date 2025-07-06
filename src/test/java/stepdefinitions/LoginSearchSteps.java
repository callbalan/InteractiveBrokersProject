package stepdefinitions;
//import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import pages.SearchAddExternalAccount;
import pages.LoginPage;
import pages.PortfolioAnalystLandingPage;
import utils.DriverFactory;

//import java.time.Duration;

public class LoginSearchSteps {
	
	   
	
	    WebDriver driver = DriverFactory.getDriver();
	    LoginPage loginPage = new LoginPage(driver);
	    PortfolioAnalystLandingPage portfolioanalystlandingpage = new PortfolioAnalystLandingPage(driver);
	    SearchAddExternalAccount searchaddexternalaccount=new SearchAddExternalAccount(driver);
	    
	    @Given("I navigate to {string}")
	    public void iNavigateTo(String url) {
	        driver.get(url);
	        loginPage = new LoginPage(driver);
	        portfolioanalystlandingpage = new PortfolioAnalystLandingPage(driver);
	    }

	    @And("I log in with username {string} and password {string}")
	    public void iLogInWithUsernameAndPassword(String username, String password) {
	        loginPage.enterUsername(username);
	        loginPage.enterPassword(password);
	        loginPage.clickLogin();
	    }
	    
	    @And("I click on 'Link Another Account'")
	    public void clickOnLinkAnotherAccount() {
	    	portfolioanalystlandingpage.clickLinkAccount();; // Make sure this method exists in your SearchPage class
	    }

	    @When("I enter {string} in the Search Institutions box")
	    public void iEnterInstitution(String institution) {
	    	searchaddexternalaccount.search(institution);
	    }	
	    	

	    @Then("I should see {string} listed under the Other section")
	    public void iShouldSeeInOther(String card) {
	        Assert.assertTrue(searchaddexternalaccount.searchResultPositive(card), "Expected to find card in 'Other'");
	    }

	    @Then("I should not see {string} under the Best Match section")
	    public void iShouldNotSeeInBestMatch(String card) {
	        Assert.assertFalse(searchaddexternalaccount.searchResultNegative(card), "Card NOT found in 'Best Match'");
	    }

	    @When("I click on the close button {string}")
	    public void iClickCloseButton(String label) {
	    	searchaddexternalaccount.closeAddExternal();
	    }

	    @Then("the Search Institutions dialog should close")
	    public void dialogShouldClose() {
	        Assert.assertTrue(searchaddexternalaccount.waitUntilAddAccountWindowCloses(), "Add External Account window did not close");
	    }

	    


}
