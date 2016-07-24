package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.actions.SeleniumActions;

abstract class TemplatePage {

    public static final String REPLACE_STRING = "%";
    private static final Logger LOG = LoggerFactory.getLogger(TemplatePage.class);

    protected final WebDriver driver;
    protected final WebDriverWait driverWait;

    protected TemplatePage(final WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, SeleniumActions.DEFAULT_ELEMENT_TIMEOUT);
    }

    protected void setUpPage(final String link) {
        SeleniumActions.setUpPage(driver, link);
        LOG.info("Open page '{}'", link);
    }

    protected void sendKey(final By locator, final String value) {
        SeleniumActions.sendKey(driver, locator, value);
        LOG.info("Send value '{}' to obj='{}'", value, locator);
    }

    protected void sendKeyWithClearingAndWaiting(final By locator, final String value) {
        waitUntilElementAppear(locator);
        clear(locator);
        SeleniumActions.sendKey(driver, locator, value);
        LOG.info("Send value '{}' to obj='{}'", value, locator);
    }

    protected void clear(final By locator) {
        SeleniumActions.clear(driver, locator);
        LOG.info("Clear value from obj='{}'", locator);
    }

    protected void submit(final By locator) {
        SeleniumActions.submit(driver, locator);
        LOG.info("Submit obj='{}'", locator);
    }

    protected void click(final By locator) {
        SeleniumActions.click(driver, locator);
        LOG.info("Click on obj='{}'", locator);
    }

    protected void clickWithWaiting(final By locator) {
        waitUntilElementAppear(locator);
        SeleniumActions.click(driver, locator);
        LOG.info("Click on obj='{}'", locator);
    }

    protected Boolean isSelected(final By locator) {
        return SeleniumActions.isSelected(driver, locator);
    }

    protected String getText(final By locator) {
        SeleniumActions.waitOnElementWithUnEmptyText(driver, locator);
        final String text = SeleniumActions.getText(driver, locator);
        LOG.info("Get text '{}' from obj='{}'", text, locator);
        return text;
    }

    protected String getTitle() {
        final String title = SeleniumActions.getTitle(driver);
        LOG.info("Get title '{}' from the current page", title);
        return title;
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

        LOG.info("Get links from obj='{}'", locator);
        return linkList;
    }

    protected void waitUntilElementDisappear(final By locator) {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        LOG.info("Wait until obj='{}' disappear", locator);
    }

    protected void waitUntilElementAppear(final By locator) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        LOG.info("Wait until obj='{}' appear", locator);
    }

    protected void waitForRedirectedPage(final String page) {
        driverWait.until(ExpectedConditions.urlContains(page));
        LOG.info("Wait until the current page will be redirected to '{}'", page);
    }

    protected void sleep(int seconds) {
        try {
            if (seconds > 0)
                Thread.sleep(3000);
            else
                Thread.sleep(500);
        } catch (InterruptedException e) {
            LOG.error("InterruptedException={}", e);
            Thread.currentThread().interrupt();
        }
    }
}