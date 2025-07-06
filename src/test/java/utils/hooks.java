package utils;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;


public class hooks {

	    private WebDriver driver;

	    @Before
	    public void setUp() {
	        DriverFactory.getDriver(); // triggers only once
	    }

		
	    @AfterStep
	    public void takeScreenshot(Scenario scenario) {
	        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        scenario.attach(screenshot, "image/png", "Step Screenshot");
	    }
}
