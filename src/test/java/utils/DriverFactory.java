package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("🚗 Launching new ChromeDriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            System.out.println("✅ Reusing existing WebDriver: " + driver.hashCode());
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            System.out.println("🛑 Quitting WebDriver: " + driver.hashCode());
            driver.quit();
            driver = null;
        }
    }

}
