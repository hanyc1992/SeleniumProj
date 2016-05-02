package edu.utexas.ece;

import edu.utexas.ece.testCases.TestFacebook;
import edu.utexas.ece.testCases.TestGoogle;
import org.testng.TestNG;
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
        suiteParamsMap.put("googleParams", "/googleParams.xml");
        suiteParamsMap.put("googleXPath", "/googleXPath.xml");
        suiteParamsMap.put("facebookParams", "/facebookParams.xml");
        suiteParamsMap.put("facebookXPath", "/facebookXPath.xml");

        suite.setParameters(suiteParamsMap);

//        //Test Google Search
//        XmlTest testGoogle = new XmlTest(suite);
//        testGoogle.setName("Test Google");
//        List<XmlClass> googleClasses = new ArrayList<XmlClass>();
//        googleClasses.add(new XmlClass(TestGoogle.class));
//        testGoogle.setXmlClasses(googleClasses);

//        Test Facebook
        XmlTest testFacebook = new XmlTest(suite);
        testFacebook.setName("Test Facebook");
        List<XmlClass> facebookClasses = new ArrayList<XmlClass>();
        facebookClasses.add(new XmlClass(TestFacebook.class));
        testFacebook.setXmlClasses(facebookClasses);

        //running tests
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();
    }

}
