package edu.utexas.ece.testTasks;

import com.thoughtworks.selenium.Selenium;
import edu.utexas.ece.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;


import java.util.List;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by hanyc on 4/27/2016.
 */
public class TestTasks {
    //    in millisecond
    static private int WAIT_THRESHOLD = 5000;

    private SeleniumUtils utils;
    private Selenium selenium;

    public TestTasks(final SeleniumUtils utils) {
        this.utils = utils;
        selenium = this.utils.getSelenium();
    }

    //must do after first open a Url to delete the cookie of that site
    //then it will reopen the base url
    public void deleteAllCookies() {
        WebDriver driver = ((WrapsDriver) selenium).getWrappedDriver();
        driver.manage().deleteAllCookies();
        selenium.open("/");
    }

    public void openSite() {
        selenium.open("/");
    }

    public void typeSearchTxtField(final String locator, final String text) {
        assertTrue(utils.waitForElement(locator, WAIT_THRESHOLD));
        selenium.type(locator, text);
    }

    public void clickBtn(final String locator) {
        Boolean a = utils.waitForElement(locator, WAIT_THRESHOLD);
        assertTrue(a);
        selenium.click(locator);
    }

    //    Verifies that the specified text pattern appears somewhere on the rendered page shown to the user.
    public void verifyResult(final String pattern) {
        final boolean result = utils.waitForText(pattern, WAIT_THRESHOLD);
        System.out.println("the result is " + result);
        assertTrue(result);
    }

    public void verifyNoResult(final String pattern) {
        final boolean result = utils.waitForElement(pattern, WAIT_THRESHOLD);
        assertFalse(result);
    }

    public void verifyDisappearance(final String pattern) {
        assertTrue(utils.waitForDisappearance(pattern, WAIT_THRESHOLD));
    }

    public void verifyOrder(final String left, final String right) {
        //left should come before right in the html
        utils.pause(WAIT_THRESHOLD);
//        count(a/b[.='tsr']/preceding::a)
        final String leftXPath = "//*[text() = '" + left + "']/preceding::*";
        final String righXPath = "//*[text() = '" + right + "']/preceding::*";
        int leftPosition = selenium.getXpathCount(leftXPath).intValue();
        int rightPosition = selenium.getXpathCount(righXPath).intValue();
        assertTrue(leftPosition < rightPosition);
    }
}
