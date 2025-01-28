package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGScenario1 {

    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setup(String browser, String url, Method m) throws MalformedURLException {
        String username = "raziya.mohammad";
        String authkey = "QUP9UdQjp37svl5FDisEXIIwP2fQxQwYEkQtmpumhO6BITodvr";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 11");
        caps.setCapability("browserName", browser);
        caps.setCapability("version", "latest");
        caps.setCapability("visual", true);
        caps.setCapability("video", true);
        caps.setCapability("network", true);
        caps.setCapability("build", "LambdaTest Selenium Playground");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

        // Open the URL
        driver.get(url);
    }

    @Test
    public void validatePageTitle() {
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));

        // Validate Page Title
        String actualTitle = driver.getTitle();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, "LambdaTest", "Page title validation failed!");

        // Continue execution despite assertion failure
        System.out.println("Actual Page Title: " + actualTitle);

        // Assert all
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
