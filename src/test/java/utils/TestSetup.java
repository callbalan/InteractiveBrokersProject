package utils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;

	public class TestSetup {

	    @BeforeClass
	    public static void globalSetup() {
	        System.out.println("Launching browser once before all scenarios");
	        DriverFactory.getDriver();
	    }

	    @AfterClass
	    public static void globalTeardown() {
	        System.out.println("Closing browser after all scenarios");
	        DriverFactory.quitDriver();
	    }
	}

