package edu.utexas.ece.testCases;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import edu.utexas.ece.testTasks.TestTasks;
import edu.utexas.ece.utils.SeleniumUtils;
import edu.utexas.ece.utils.XMLParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by hanyc on 4/27/2016.
 */
public class TestFacebook {
    static private String BASE_URL = "http://www.facebook.com";

    private TestTasks testTasks;
    private SeleniumUtils utils;
    private WebDriver webDriver;

    @BeforeClass
    public void init() {
        System.out.println("init...");
        webDriver = new FirefoxDriver();
        utils = new SeleniumUtils(new WebDriverBackedSelenium(webDriver, BASE_URL));
        testTasks = new TestTasks(utils);
    }


    @Test
    @Parameters({"facebookParams", "facebookXPath"})
    public void testFacebookProfileChange(String paramsFileName, final String xPathFileName) {
        final Map<String, String> paramsMap = XMLParser.getInstance().getMap(paramsFileName);
        final Map<String, String> xPathMap = XMLParser.getInstance().getMap(xPathFileName);
        System.out.println("the paramsMap is " + paramsMap);
        System.out.println("the xPathMap is " + xPathMap);

        testTasks.openSite();
        testTasks.deleteAllCookies();
        testTasks.typeSearchTxtField(xPathMap.get("loginUserName"), paramsMap.get("userNameA"));
        testTasks.typeSearchTxtField(xPathMap.get("loginUserPassword"), paramsMap.get("passwordA"));
        testTasks.clickBtn(xPathMap.get("loginClkBtn"));
        testTasks.clickBtn(xPathMap.get("profileBtn"));
        testTasks.clickBtn("//a[@role='button' and text()='Update Info']");
        testTasks.clickBtn("//a[@data-testid='nav_contact_basic']");
        testTasks.clickBtn("//a[@data-testid='add_mobile']");
        testTasks.typeSearchTxtField("//input[@title='Phone number 1']", paramsMap.get("userNameA"));
        testTasks.clickBtn("//button[text()='Save Changes']");
        testTasks.verifyResult(paramsMap.get("userNameA"));
    }

    @Test
    @Parameters({"facebookParams", "facebookXPath"})
    public void testFacebookLogin(String paramsFileName, final String xPathFileName) {
        final Map<String, String> paramsMap = XMLParser.getInstance().getMap(paramsFileName);
        final Map<String, String> xPathMap = XMLParser.getInstance().getMap(xPathFileName);
        System.out.println("the paramsMap is " + paramsMap);
        System.out.println("the xPathMap is " + xPathMap);

        testTasks.openSite();
        testTasks.deleteAllCookies();
        testTasks.typeSearchTxtField(xPathMap.get("loginUserName"), paramsMap.get("userNameA"));
        testTasks.typeSearchTxtField(xPathMap.get("loginUserPassword"), paramsMap.get("passwordA"));
        testTasks.clickBtn(xPathMap.get("loginClkBtn"));
        testTasks.verifyResult(paramsMap.get("verifyStringAfterLogin"));
        utils.pause(1000);
    }

    @Test
    @Parameters({"facebookParams", "facebookXPath"})
    public void testFacebookVisibilitySetting(String paramsFileName, final String xPathFileName) {
        final Map<String, String> paramsMap = XMLParser.getInstance().getMap(paramsFileName);
        final Map<String, String> xPathMap = XMLParser.getInstance().getMap(xPathFileName);
        System.out.println("the paramsMap is " + paramsMap);
        System.out.println("the xPathMap is " + xPathMap);

        /* Check Visibility in account B */
        testTasks.openSite();
        testTasks.deleteAllCookies();
        testTasks.typeSearchTxtField(xPathMap.get("loginUserName"), paramsMap.get("userNameB"));
        testTasks.typeSearchTxtField(xPathMap.get("loginUserPassword"), paramsMap.get("passwordB"));
        testTasks.clickBtn(xPathMap.get("loginClkBtn"));
        testTasks.clickBtn(xPathMap.get("profileBtn"));
        testTasks.clickBtn("//a[@role='button' and text()='Update Info']");
        testTasks.clickBtn("//a[@data-testid='nav_places']");
        testTasks.verifyResult("Austin");

        /* Check Visibility in account A */
        testTasks.openSite();
        testTasks.deleteAllCookies();
        testTasks.typeSearchTxtField(xPathMap.get("loginUserName"), paramsMap.get("userNameA"));
        testTasks.typeSearchTxtField(xPathMap.get("loginUserPassword"), paramsMap.get("passwordA"));
        testTasks.clickBtn(xPathMap.get("loginClkBtn"));
        testTasks.typeSearchTxtField("//div[@role='combobox' and @aria-label='Search Facebook']", "Shushu Wang");
        testTasks.clickBtn("//button[@type='submit']");
        testTasks.verifyResult("Shushu Wang");
        testTasks.clickBtn("//div[text()='Shushu Wang']");
        testTasks.clickBtn("//a[@data-tab-key='about']");
        testTasks.clickBtn("//a[@data-testid='nav_contact_basic']");
        testTasks.verifyResult("1992");
        testTasks.verifyResult("Male");
        testTasks.verifyNoResult("Birth Date");
    }



    @AfterClass
    public void destory() {
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
