package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.actions.SeleniumActions;

abstract class TemplatePage {

    protected final WebDriver driver;
    protected final WebDriverWait driverWait;

    protected TemplatePage(final WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, SeleniumActions.DEFAULT_ELEMENT_TIMEOUT);
    }

    protected void setUpPage(final String link) {
        SeleniumActions.setUpPage(driver, link);
    }

    protected void sendKey(final By locator, final String value) {
        SeleniumActions.sendKey(driver, locator, value);
    }


    protected void clear(final By locator) {
        SeleniumActions.clear(driver, locator);
    }

    protected void submit(final By locator) {
        SeleniumActions.submit(driver, locator);
    }

    protected void click(final By locator) {
        SeleniumActions.click(driver, locator);
    }

    protected Boolean isSelected(final By locator) {
        return SeleniumActions.isSelected(driver, locator);
    }

    protected String getText(final By locator) {
        SeleniumActions.waitOnElementWithUnEmptyText(driver, locator);
        return SeleniumActions.getText(driver, locator);
    }

    protected String getTitle() {
        return SeleniumActions.getTitle(driver);
    }

    protected List<String> getLinks(final By locator) {
        String href;
        List<String> linkList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(locator);

        for (WebElement element : elementList) {
            href = element.getAttribute("href");
            if (!linkList.contains(href))
                linkList.add(href);
        }

        return linkList;
    }

    protected void waitUntilElementDisappear(final By locator) {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilElementAppear(final By locator) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForRedirectedPage(final String page) {
        driverWait.until(ExpectedConditions.urlToBe(page));
    }
}