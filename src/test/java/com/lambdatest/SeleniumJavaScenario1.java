package com.lambdatest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class SeleniumJavaScenario1 {

    public static void main(String[] args) {

        WebDriver driver = null;

        try {
            // LambdaTest credentials
            String username = "raziya.mohammad"; // Your LambdaTest username
            String authkey = "QUP9UdQjp37svl5FDisEXIIwP2fQxQwYEkQtmpumhO6BITodvr"; // Your LambdaTest authkey
            String hub = "@hub.lambdatest.com/wd/hub"; // LambdaTest hub URL

            // DesiredCapabilities for Safari on MacOS Catalina
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platform", "MacOS Catalina");  // Platform version
            caps.setCapability("browserName", "Safari");       // Browser name
            caps.setCapability("version", "latest");           // Browser version
            caps.setCapability("visual", true);                // Enable visual testing
            caps.setCapability("video", true);                 // Enable video recording
            caps.setCapability("network", true);               // Enable network logs
            caps.setCapability("terminal", true);              // Enable terminal logs
            caps.setCapability("build", "Selenium Java 101");  // Build name
            caps.setCapability("name", "LambdaTest Test - Safari");  // Test name
            caps.setCapability("plugin", "git-testng");        // Test plugin

            // Initialize RemoteWebDriver with LambdaTest hub URL and desired capabilities
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

            // Open the URL in the browser
            String url = "https://www.lambdatest.com/selenium-playground";
            driver.get(url);
            System.out.println("Opened URL: " + url);

            // Your additional test actions go here
            // Example: You could add element interactions, validations, etc.

            // For example: Print page title
            System.out.println("Page title: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up - quit the driver
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
