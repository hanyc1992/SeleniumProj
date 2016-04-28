package edu.utexas.ece;

import edu.utexas.ece.testCases.TestGoogle;
import edu.utexas.ece.testCases.TestLinkedIn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanyc on 4/26/2016.
 */
public class SeleniumMain {

    public static void main(String args[]) {
        XmlSuite suite = new XmlSuite();
        suite.setName("Selenium Project");

        Map<String, String> suiteParamsMap = new HashMap<String, String>();
        suiteParamsMap.put("LinkedInParams", "/linkedInConfigs.xml");
        suiteParamsMap.put("GoogleParams", "/googleConfigs.xml");

        suite.setParameters(suiteParamsMap);

//        //Test LinkedIn
//        XmlTest testLinkedIn = new XmlTest(suite);
//        testLinkedIn.setName("Test LinkedIn");
//        List<XmlClass> linkedInClasses = new ArrayList<XmlClass>();
//        linkedInClasses.add(new XmlClass(TestLinkedIn.class));
//        testLinkedIn.setXmlClasses(linkedInClasses);

        //Test Google Search
        XmlTest testGoogle = new XmlTest(suite);
        testGoogle.setName("Test Google");
        List<XmlClass> googleClasses = new ArrayList<XmlClass>();
        googleClasses.add(new XmlClass(TestGoogle.class));
        testGoogle.setXmlClasses(googleClasses);

        //running tests
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();
    }

}
