package edu.utexas.ece.testTasks;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;
import edu.utexas.ece.utils.PropUtils;
import edu.utexas.ece.utils.SeleniumUtils;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by hanyc on 4/26/2016.
 */
public class TestGoogleTasks {
    private final static String PROP_NAME = System.getProperty("user.dir") + "\\src\\main\\java\\edu\\utexas\\ece\\testProperties\\googlePages.properties";

    private SeleniumUtils utils;

    private Selenium selenium;

    private Properties elemMap;

    private SeleneseTestCase stc = new SeleneseTestCase();

    public TestGoogleTasks(SeleniumUtils utils) {
        this.utils = utils;
        selenium = this.utils.getSelenium();
        elemMap = PropUtils.getProperties(PROP_NAME);
    }

    public void openSite() {
        selenium.open("/");
    }

    public void typeSearchTxtField(HashMap<String, Object> paraMap) {
        System.out.println("the elemMap is " + elemMap + "..............");
        stc.verifyTrue(utils.waitForElement((String) elemMap
                .get(TestGoogleConstants.GOOGLE_SEARCH_TXT_FIELD), 30));
        selenium.type((String) elemMap
                        .get(TestGoogleConstants.GOOGLE_SEARCH_TXT_FIELD),
                (String) paraMap
                        .get(TestGoogleConstants.GOOGLE_SEARCH_TXT_FIELD));
    }

    public void clickSearchBtn() {
        stc.verifyTrue(utils.waitForElement((String) elemMap
                .get(TestGoogleConstants.GOOGLE_SEARCH_BTN), 30));
        selenium.click((String) elemMap
                .get(TestGoogleConstants.GOOGLE_SEARCH_BTN));
    }

    public void verifyResult(HashMap<String, Object> paraMap) {
        stc.verifyTrue(selenium.isTextPresent((String) paraMap
                .get(TestGoogleConstants.VERIFY_STRING)));
    }
}
