package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.After;
import org.junit.Before;

public abstract class TemplateTest {

    protected static WebDriver driver;

    @Before
    public void beforeTest () {
        driver = new FirefoxDriver();
    }

    @After
    public void afterTest () {
        driver.quit();
    }

}