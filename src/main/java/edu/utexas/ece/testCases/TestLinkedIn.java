package edu.utexas.ece.testCases;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
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
public class TestLinkedIn {
    private static String BASEURL = "http://www.linkedin.com";
    private HashMap<String, Object> paramsMap;
    private TestLinkedInTasks tgTasks;
    private SeleniumUtils utils;
    private WebDriver webDriver;

    @BeforeClass
    public void init() {
        System.out.println("init...");
        webDriver = new FirefoxDriver();
        utils = new SeleniumUtils(new WebDriverBackedSelenium(webDriver, BASEURL));
    }

    @Test
    @Parameters({"LinkedInParams"})
    public void testLinkedInLogin(String paramsFileName) {
        paramsMap = (HashMap<String, Object>) XMLParser.getInstance().parserXml(paramsFileName);
        System.out.println("the paramsMap is" + paramsMap);
    }


    @AfterClass
    public static void destory() {
//        System.out.println("destory...");
//        driver.quit();
    }
}
