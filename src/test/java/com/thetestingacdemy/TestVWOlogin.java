package com.thetestingacdemy;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Collections;


public class TestVWOlogin {

    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    public void setUp() {

        options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test(priority = 1, groups = {"negative", "sanity"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("TC#1 -> Verify the Invalid username and valid password, Login is not successfull..!")
    public void testInvalidLogin() throws InterruptedException {
        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.id("login-username")).sendKeys("93npu2yyb0@esiix.com");
        driver.findElement(By.id("login-password")).sendKeys("Wingify@123");
        driver.findElement(By.id("js-login-btn")).click();

        WebElement errorMessage = driver.findElement(By.className("notification-box-description"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(errorMessage));

        String errorString = driver.findElement(By.className("notification-box-description")).getText();
        Assert.assertEquals(errorString, "Your email, password, IP address or location did not match");
    }

    @Test(enabled = true, priority = 2, groups = {"positive", "sanity", "stage"})
    @Description("TC#2 -> Verify the valid username and valid password, Login is  successfull..!")
    public void testValidLogin() throws InterruptedException {
        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.id("login-username")).clear();
        driver.findElement(By.id("login-username")).sendKeys("nihim56864@mposhop.com");
        driver.findElement(By.id("login-password")).sendKeys("Wingify@123");
        driver.findElement(By.id("js-login-btn")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("h1.page-heading"))));

        Assert.assertEquals(driver.getTitle(),"Dashboard");
        Assert.assertEquals(driver.getCurrentUrl(),"https://app.vwo.com/#/dashboard");
        driver.get("https://app.vwo.com/logout");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("tearDown() method is being executed");
            driver.quit();
    }
}