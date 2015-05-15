package com.selcum.runner;

import com.selcum.utils.BrowserUtil;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:report/cumcumber-html-report", "json:report/cucumber-report.json"},
        features = {"classpath:Features"},
        glue = "com.selcum.stepdef"
)

public class CucumberTestRunner {

    @BeforeClass
    public static void initialize(){
        BrowserUtil.openBrowser();
    }

    @AfterClass
    public static void reset(){
        BrowserUtil.closeBrowser();
    }

}