package edu.utexas.ece.testCases;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import edu.utexas.ece.testTasks.TestGoogleConstants;
import edu.utexas.ece.testTasks.TestGoogleTasks;
import edu.utexas.ece.testTasks.TestLinkedInTasks;
import edu.utexas.ece.utils.SeleniumUtils;
import edu.utexas.ece.utils.XMLParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by hanyc on 4/26/2016.
 */
public class TestGoogle {
    private HashMap<String, Object> paramsMap;
    private TestGoogleTasks testTasks;
    private SeleniumUtils utils;
    private WebDriver webDriver;

    @BeforeClass
    public void init() {
        System.out.println("init...");
        webDriver = new FirefoxDriver();
        utils = new SeleniumUtils(new WebDriverBackedSelenium(webDriver, TestGoogleConstants.BASE_URL));
        testTasks = new TestGoogleTasks(utils);
    }

    @Test
    @Parameters({"GoogleParams"})
    public void testLinkedInLogin(String paramsFileName) {
        paramsMap = (HashMap<String, Object>) XMLParser.getInstance().parserXml(paramsFileName);
        System.out.println("the paraMap is " + paramsMap);

        testTasks.openSite();
        testTasks.typeSearchTxtField(paramsMap);
        testTasks.clickSearchBtn();
        testTasks.verifyResult(paramsMap);
        utils.pause(5000);
    }


    @AfterClass
    public void destory() {
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
