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
public class TestFacebookMultiplePosts {
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
    public void testFacebookMultiplePosts(String paramsFileName, final String xPathFileName) {
        final Map<String, String> paramsMap = XMLParser.getInstance().getMap(paramsFileName);
        final Map<String, String> xPathMap = XMLParser.getInstance().getMap(xPathFileName);
        System.out.println("the paramsMap is " + paramsMap);
        System.out.println("the xPathMap is " + xPathMap);

        testTasks.openSite();
        testTasks.deleteAllCookies();
        testTasks.typeSearchTxtField(xPathMap.get("loginUserName"), paramsMap.get("wangAyiUserName"));
        testTasks.typeSearchTxtField(xPathMap.get("loginUserPassword"), paramsMap.get("wangAyiPassword"));
        testTasks.clickSearchBtn(xPathMap.get("loginClkBtn"));
        testTasks.verifyResult(paramsMap.get("verifyStringAfterLogin"));
        testTasks.clickSearchBtn(xPathMap.get("profile"));
        testTasks.verifyResult(paramsMap.get("verifyStringAfterClickProfile"));

        testTasks.typeSearchTxtField(xPathMap.get("postTextArea"), paramsMap.get("firstPost"));
        testTasks.clickSearchBtn(xPathMap.get("postButton"));
//        testTasks.verifyResult(paramsMap.get("verifyFirstPostExist"));
        utils.pause(3000);
        testTasks.typeSearchTxtField(xPathMap.get("postTextArea2"), paramsMap.get("secondPost"));
        testTasks.clickSearchBtn(xPathMap.get("postButton"));
        utils.pause(3000);
        testTasks.verifyOrder(paramsMap.get("secondPost"), paramsMap.get("firstPost"));
        utils.pause(3000);
    }


    @AfterClass
    public void destory() {
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
