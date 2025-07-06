package runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.DriverFactory;

@CucumberOptions(
		features="src/test/resources/features",
		glue={"stepdefinitions"},monochrome=true,tags="@smoke",
		plugin={
				"pretty",
				"html:target/cucumber.html",
		        "json:target/cucumber.json"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
	
	 @BeforeClass
	    public static void globalSetup() {
	        System.out.println("🚀 Starting browser before all scenarios");
	        DriverFactory.getDriver();
	    }

	    @AfterClass
	    public static void globalTeardown() {
	        System.out.println("🧹 Shutting down browser after all scenarios");
	        DriverFactory.quitDriver();
	    }

}
