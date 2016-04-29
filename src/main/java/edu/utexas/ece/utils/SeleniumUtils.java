package edu.utexas.ece.utils;

import com.sun.istack.internal.NotNull;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import java.util.List;

/**
 * Created by hanyc on 4/26/2016.
 */
public class SeleniumUtils extends SeleneseTestCase {

    private Selenium selenium;


    public SeleniumUtils(Selenium selenium) {
        super();
        this.selenium = selenium;
    }

    public Selenium getSelenium() {
        return selenium;
    }

    public void pause(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForElementEditable(String locator, int millisecs) {
        boolean elementEditable = false;
        int time = 0;
        while (time < millisecs) {
            if (selenium.isEditable(locator)) {
                elementEditable = true;
                break;
            }
            time += 100;
            pause(100);
        }
        return elementEditable;
    }

    public boolean waitForElement(String locator, int millisecs) {
        boolean elementExist = false;
        int time = 0;
        while (time < millisecs) {
            if (selenium.isElementPresent(locator)) {
                elementExist = true;
                break;
            }
            time += 100;
            pause(100);
        }
        return elementExist;
    }

    public boolean waitForText(String tmplName, int millisecs) {
        boolean textExist = false;
        int time = 0;
        while (time < millisecs) {
            if (selenium.isTextPresent(tmplName)) {
                textExist = true;
                break;
            }
            time += 100;
            pause(100);
        }
        return textExist;
    }
}
