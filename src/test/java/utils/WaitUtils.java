package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;



public class WaitUtils {
	public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
	public static void waitAndClick(WebDriver driver, By locator) throws TimeoutException {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        element.click();
	    } catch (ElementNotInteractableException e) {
	        System.err.println("Element not clickable: " + locator);
	        throw e;
	    }
	}
	public static WebElement waitUntilClickable(WebDriver driver, By locator, int timeoutSeconds) {
	    Wait<WebDriver> wait = new FluentWait<>(driver)
	        .withTimeout(Duration.ofSeconds(timeoutSeconds))
	        .pollingEvery(Duration.ofMillis(500))
	        .ignoring(NoSuchElementException.class)
	        .ignoring(ElementNotInteractableException.class)
	        .ignoring(StaleElementReferenceException.class);

	    return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

}
