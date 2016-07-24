package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailsPage extends TemplatePage {

    private static final String xPathSubject = "//div[@role='main']//span/b[text()='" + REPLACE_STRING + "']";
    private static final String xPathEmailAddress = "//div[@role='main']//span/b[text()='" + REPLACE_STRING + "']";

    EmailsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnEmailBySubject(String subject) {
        final By emailSubject = new By.ByXPath(xPathSubject.replaceAll(REPLACE_STRING, subject));
        clickWithWaiting(emailSubject);
    }

    public void clickOnEmailByEmailAddress(String email) {
        final By emailAddress = new By.ByXPath(xPathEmailAddress.replaceAll(REPLACE_STRING, email));
        clickWithWaiting(emailAddress);
    }
}
