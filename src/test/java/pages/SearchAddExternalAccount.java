package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class SearchAddExternalAccount {
	
	WebDriver driver;

    private By searchInstitutions = By.xpath("//input[@placeholder='Search Institutions']");
    private String other = "//p[text()='Other']/following-sibling::*//a[contains(text(), 'ZZZZ')]";
    private String  bestMatch = "//p[text()='Best Match']/following-sibling::*//a[contains(text(), 'ZZZ')]";
    private By closeBtn=By.xpath("//button[@class='close']");
    private By addExternalAccountWindow=By.xpath("//h4[@class='modal-title']//span[normalize-space()='Add External Account']");
    private By offLineAccount=By.xpath("//span[text()='Offline Account']");
    private By disclosureContinueBtn=By.xpath("//a[@role='button' and text()='Continue']");
     
   public SearchAddExternalAccount(WebDriver driver) {
   	 this.driver = driver;
	}

   public boolean verifyaddExternalAccountWindow() {
	   
	   WebElement modal = WaitUtils.waitForVisibility(driver, addExternalAccountWindow, 40);
		return(modal.isDisplayed());
//	   return (driver.findElement(addExternalAccountWindow).isDisplayed()) ; 

   }
   public void search(String searchString) {
	   
	 
	   WebElement searchInput = WaitUtils.waitUntilClickable(driver,searchInstitutions, 40);
	   searchInput.sendKeys(searchString);

	   
   }
   
   public boolean searchResultPositive(String Card) {
	  
//	   other.replace("ZZZZ", Card);
	   return (driver.findElement(By.xpath(other.replace("ZZZZ", Card))).isDisplayed() ) ;
	   
   }
   
  public boolean searchResultNegative(String Card) {
	 
	  List<WebElement> elements = driver.findElements(By.xpath(bestMatch.replace("ZZZZ", Card)));
	  return (!elements.isEmpty() && elements.get(0).isDisplayed()) ;
   }
  
  public void closeAddExternal() {
	  
	  driver.findElement(closeBtn).click();
	  	  
  }
   	
  public boolean addExternalNotviible() {
	  
	  return (!driver.findElement(By.xpath(bestMatch)).isDisplayed()) ;
	  
  }
  
  
  public boolean waitUntilAddAccountWindowCloses() {
	    return new WebDriverWait(driver, Duration.ofSeconds(15))
	        .until(ExpectedConditions.invisibilityOfElementLocated(addExternalAccountWindow));
	}
}
