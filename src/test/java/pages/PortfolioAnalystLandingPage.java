package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class PortfolioAnalystLandingPage {

	WebDriver driver;


    private By linkAccount = By.xpath("//a[@role='button' and text()='Link Another Account']");
      
    public PortfolioAnalystLandingPage(WebDriver driver) {
    	 this.driver = driver;
    	 
	}

	public boolean verifyLandingPage() {
		 
		WebElement linkAccountLink = WaitUtils.waitForVisibility(driver, linkAccount, 15);
//		return(linkAccountLink.isDisplayed());
		String title = driver.getTitle();
		return title.contains("PortfolioAnalyst");
	
    }
	
	public void clickLinkAccount() {
		
		driver.findElement(linkAccount).click();
	}
	
	
    

	
}
