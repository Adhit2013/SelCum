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

public class TestYahoo extends BrowserUtil{

    private boolean failure = false;

    @Given("^Yahoo is open in Chrome$")
    public void initYahoo()
    {
        driver.get("http://www.yahoo.com");
    }

    @When("^I type Java in the search field$")
    public void enterValue()
    {
        WebElement SearchTextBox = driver.findElement(By.id("UHSearchBox"));
        SearchTextBox.sendKeys("Java");
    }

    @And("^I click the search web button$")
    public void yahooSearch()
    {
        WebElement SearchButton = driver.findElement(By.id("UHSearchWeb"));
        SearchButton.click();
    }

    @Then("^I see the results for Java$")
    public void checkJava()
    {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toUpperCase().startsWith("JAVA");
            }
        });
        try {
            assertTrue(driver.getTitle().equals("Java - Yahoo Search Results"));
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