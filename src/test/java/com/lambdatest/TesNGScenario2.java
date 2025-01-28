package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TesNGScenario2 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setup(@Optional("chrome") String browser, String url, Method m, ITestContext ctx) throws MalformedURLException {
        String username = "raziya.mohammad";
        String authkey = "pQUP9UdQjp37svl5FDisEXIIwP2fQxQwYEkQtmpumhO6BITodvr";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "LambdaTest Selenium Playground");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

        // Launch URL
        driver.get(url);
    }

    @Test
    public void testCheckboxDemo() {
        SoftAssert softAssert = new SoftAssert();

        // Step 1: Click “Checkbox Demo”
        WebElement checkboxDemoLink = driver.findElement(By.linkText("Checkbox Demo"));
        checkboxDemoLink.click();

        // Step 2: Click the checkbox under the “Single Checkbox Demo” section
        WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));
        singleCheckbox.click();

        // Step 3: Validate whether this checkbox is “selected”
        boolean isSelected = singleCheckbox.isSelected();
        softAssert.assertTrue(isSelected, "Checkbox should be selected but it is not.");

        // Step 4: Click the checkbox again and validate whether the checkbox is “unselected”
        singleCheckbox.click();
        boolean isUnselected = !singleCheckbox.isSelected();
        softAssert.assertTrue(isUnselected, "Checkbox should be unselected but it is not.");

        // Assert all validations
        softAssert.assertAll();
        Status = "passed";
    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}
