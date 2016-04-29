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
 * Created by hanyc on 4/26/2016.
 */
public class TestGoogle {
    static private String BASE_URL = "http://www.google.com";

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
    @Parameters({"googleParams", "googleXPath"})
    public void testGoogleSearch(String paramsFileName, final String xPathFileName) {
        final Map<String, String> paramsMap = XMLParser.getInstance().getMap(paramsFileName);
        final Map<String, String> xPathMap = XMLParser.getInstance().getMap(xPathFileName);
        System.out.println("the paramsMap is " + paramsMap);
        System.out.println("the xPathMap is " + xPathMap);

        testTasks.openSite();
        testTasks.typeSearchTxtField(xPathMap.get("googleSearchTxtField"), paramsMap.get("googleSearchTxtField"));
        testTasks.clickBtn(xPathMap.get("googleSearchBtn"));
        testTasks.verifyResult(paramsMap.get("verify-String"));
        utils.pause(1000);
    }


    @AfterClass
    public void destory() {
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
