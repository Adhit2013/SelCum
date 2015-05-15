package com.selcum.stepdef;

import com.selcum.utils.BrowserUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import static junit.framework.Assert.assertTrue;

public class TestGoogle extends BrowserUtil{

    private boolean failure = false;

    @Given("^Google is open in Chrome$")
    public void initGoogle()
    {
        driver.get("http://www.google.com");
    }

    @When("^I type Selenium in the search field$")
    public void enterValue()
    {
        WebElement SearchTextBox = driver.findElement(By.name("q"));
        SearchTextBox.sendKeys("Selenium");
    }

    @And("^I click the search button$")
    public void googleSearch()
    {
        WebElement SearchButton = driver.findElement(By.name("btnG"));
        SearchButton.click();
    }

    @Then("^I see the results for Selenium$")
    public void checkSelenium()
    {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toUpperCase().startsWith("SELENIUM");
            }
        });
        try {
            assertTrue(driver.getTitle().equals("Selenium - Google Search"));
        }
        catch(AssertionError ae){
            failure = true;
            Assert.fail();
        }
    }

    @After
    public void captureFailure(Scenario scenario)
    {
        if(failure) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

}