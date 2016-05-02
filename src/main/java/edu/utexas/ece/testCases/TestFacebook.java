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
        testTasks.clickBtn(xPathMap.get("updateInfoBtn"));
        testTasks.clickBtn(xPathMap.get("contactBasicBtn"));
        testTasks.clickBtn(xPathMap.get("addMobileBtn"));
        testTasks.typeSearchTxtField(xPathMap.get("phoneNumberInput"), paramsMap.get("userNameA"));
        testTasks.clickBtn(xPathMap.get("saveChangesBtn"));
        testTasks.verifyResult(paramsMap.get("userNameA"));
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
        testTasks.clickBtn(xPathMap.get("updateInfoBtn"));
        testTasks.clickBtn(xPathMap.get("placesBtn"));
        testTasks.verifyResult(paramsMap.get("verifyPlace"));

        /* Check Visibility in account A */
        testTasks.openSite();
        testTasks.deleteAllCookies();
        testTasks.typeSearchTxtField(xPathMap.get("loginUserName"), paramsMap.get("userNameA"));
        testTasks.typeSearchTxtField(xPathMap.get("loginUserPassword"), paramsMap.get("passwordA"));
        testTasks.clickBtn(xPathMap.get("loginClkBtn"));
        testTasks.typeSearchTxtField(xPathMap.get("searchBox"), paramsMap.get("userNameB"));
        testTasks.clickBtn(xPathMap.get("searchBtn"));
        testTasks.verifyResult(paramsMap.get("userNameB"));
        testTasks.clickBtn(xPathMap.get("ShushuWangOption"));
        testTasks.clickBtn(xPathMap.get("aboutBtn"));
        testTasks.clickBtn(xPathMap.get("contactBasicBtn"));
        testTasks.verifyNoResult(paramsMap.get("verifyPlace"));
    }



    @AfterClass
    public void destory() {
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
