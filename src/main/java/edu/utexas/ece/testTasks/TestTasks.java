package edu.utexas.ece.testTasks;

import com.thoughtworks.selenium.Selenium;
import edu.utexas.ece.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by hanyc on 4/27/2016.
 */
public class TestTasks {
    static private int WAIT_THRESHOLD = 30;

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

    public void clickSearchBtn(final String locator) {
        assertTrue(utils.waitForElement(locator, WAIT_THRESHOLD));
        selenium.click(locator);
    }

    //    Verifies that the specified text pattern appears somewhere on the rendered page shown to the user.
    public void verifyResult(final String pattern) {
        assertTrue(selenium.isTextPresent(pattern));
    }
}
