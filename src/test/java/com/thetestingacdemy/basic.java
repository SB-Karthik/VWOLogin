package com.thetestingacdemy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class basic {
        public static void main(String[] args) {
            // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Uncomment and set path if needed
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.google.com");
            System.out.println("Title: " + driver.getTitle());
            driver.quit();
        }
    }

