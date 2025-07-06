package stepdefinitions;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AddAccountsPage;
import pages.LoginPage;
import pages.PortfolioAnalystLandingPage;
import pages.SearchAddExternalAccount;
import utils.DriverFactory;

public class AddAccountsSteps {
	WebDriver driver;
	@Before
	public void initDriver() {
	    driver = DriverFactory.getDriver();
	    addaccountspage = new AddAccountsPage(driver);
	    portfolioanalystlandingpage = new PortfolioAnalystLandingPage(driver);
	    searchaddexternalaccount = new SearchAddExternalAccount(driver);
	}
	
	AddAccountsPage addaccountspage =new AddAccountsPage(driver);
	 PortfolioAnalystLandingPage portfolioanalystlandingpage = new PortfolioAnalystLandingPage(driver);
	 SearchAddExternalAccount searchaddexternalaccount = new SearchAddExternalAccount(driver);
	
	@Given("I am on the Landing Page")
	public void iAmOnTheLandingPage() {
		Assert.assertTrue(portfolioanalystlandingpage.verifyLandingPage(), "Landing page is not diaplayed"); 
	}

	@Then("I click on 'Link Another Account' from the dashboard")
	public void iClickOnLinkAnotherAccount() {
		portfolioanalystlandingpage.clickLinkAccount();
	}

	@Then("I am on the Add External Account page")
	public void iAmOnTheAddExternalAccountPage() {
	    Assert.assertTrue(addaccountspage.verifyAddAcctPage(),"'Add External Account' window is not visible.");
	}
	

	@When("I click on the 'Offline Account' option")
	public void iClickOnOfflineAccountOption() {
		addaccountspage.clickOfflineAcctLink();
	}

	@And("I click 'Continue' on the Add External Account modal")
	public void iClickContinueOnTheAddExternalAccountModal() {
		addaccountspage.clickContinueBtn();
	}
	
	@Then("I am on the Add Account page")
	public void iAmOnTheAddAccountPage() {
	    Assert.assertTrue(addaccountspage.verifyAddAcctPage(),"'Add Account' page is not visible or did not load correctly");
	}

	@Then("I should be able to add an offline account of type {string}")
	public void iShouldBeAbleToAddOfflineAccountOfType(String accountType) throws InterruptedException {
	    Assert.assertTrue(addaccountspage.addOfflineAccount(accountType),"Offline account could not me added for -->" + accountType);
	}
}
