package pages;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.WaitUtils;

public class AddAccountsPage {
	
	WebDriver driver;
	 public AddAccountsPage(WebDriver driver) {
    	 this.driver = driver;
//    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	private By offLineAccount=By.xpath("//span[text()='Offline Account']");
	private By acctTypeSelect=By.xpath("//label[text()='Account Type']/following::select[@id='type']"); 
	private By acctTitle=By.xpath("//input[@id='acctTitle']");
	private By date=By.xpath("//input[@name='date']");
	private By cashBalance=By.xpath("//input[@name='balance_0']");
	private By baseCurrency=By.xpath("//select[@id='currency']");
	private By manuallyBtn= By.xpath("//a[contains(text(),'Manually')]");
	private By continueBtn=By.xpath("//a[text()='Continue'][ancestor::div[@id='amModalFooter']]");
	private By continueBtnConfirm=By.xpath("//a[text()='Continue'][ancestor::div[@class='panel-body']]");
	
	private By addExternalAccountWindow=By.xpath("//h4[@class='modal-title']//span[text()='Add External Account']");
	private By enterTransactionsConfirmation=By.xpath("//h4[contains(text(), 'Add Offline Account - Enter Transactions')]");
	private By reviewAccount=By.xpath("//h4[contains(text(), 'Add Offline Account - Review')]"); 
	private By accountAddedSuccess=By.xpath("//h1[contains(text(), 'Your account has been added!')]");
	private By oK=By.xpath("//a[@role='button' and text()='Ok']");
	
	
//	brokerage and credit card and savings havve the manual button
	
	
	public void clickOfflineAcctLink() {
		driver.findElement(offLineAccount).click();
	}
	
	public void clickContinueBtn() {
		try {
			WaitUtils.waitAndClick(driver, continueBtn);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean verifyAddAcctPage() {
		// check if the Add External Account window is displayed	
			WebElement modal = WaitUtils.waitForVisibility(driver, addExternalAccountWindow, 40);
			return(modal.isDisplayed());
//			  return (driver.findElement((addExternalAccountWindow)).isDisplayed()) ;
		
	}
	
	public boolean addOfflineAccount(String accountType) throws InterruptedException {
		
		boolean success=false;
		selectOptionByText(driver,acctTypeSelect,accountType);
		driver.findElement(acctTitle).sendKeys("custom"+""+ String.valueOf((int)(Math.random() * 1000)));
		WebElement dateInput = WaitUtils.waitUntilClickable(driver,date, 15);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '2025-07-03'; arguments[0].dispatchEvent(new Event('input'));", dateInput);
		selectOptionByText(driver,baseCurrency,"USD");
		driver.findElement(cashBalance).sendKeys(String.valueOf((int)(Math.random() * 10000)));
		if("Brokerage".equalsIgnoreCase(accountType) || "Savings".equalsIgnoreCase(accountType)  || "Credit Card".equalsIgnoreCase(accountType)) {
			
			WebElement manually = WaitUtils.waitUntilClickable(driver, manuallyBtn, 15);
			manually.click();


		}
		
		WaitUtils.waitUntilClickable(driver, continueBtnConfirm, 15).click();
//		
	  if("Brokerage".equalsIgnoreCase(accountType) || "Savings".equalsIgnoreCase(accountType)  || "Credit Card".equalsIgnoreCase(accountType)) {
			
			if(!driver.findElement((enterTransactionsConfirmation)).isDisplayed()){
				success=false;
				return success;
			}
			WaitUtils.waitUntilClickable(driver, continueBtnConfirm, 15).click();
	
	  }
		
		
//		
		
		if(!driver.findElement((reviewAccount)).isDisplayed()){
			success=false;
			return success;
		}
		
		Thread.sleep(2000);
		
	
		WaitUtils.waitUntilClickable(driver, continueBtnConfirm, 15).click();
		
		if(!driver.findElement((accountAddedSuccess)).isDisplayed()){
			success=false;
			return success;
		}
		
		success=true;
		driver.findElement(oK).click();
		return success;
	}
	
	
	
	
	 public static void selectOptionByText(WebDriver driver, By dropdownLocator, String visibleText) {
	        WebElement dropdown = driver.findElement(dropdownLocator);
	        Select select = new Select(dropdown);
	        select.selectByVisibleText(visibleText);
	 }
	
	
//	savings and brokerage need the manual button
}
