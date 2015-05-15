package com.selcum.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserUtil {

    protected static WebDriver driver = null;

    public static void openBrowser(){
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();
            System.out.println("Driver initialized");
        }
    }

    public static void closeBrowser(){
        driver.quit();
    }
}
