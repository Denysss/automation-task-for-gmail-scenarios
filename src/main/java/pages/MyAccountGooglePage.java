package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountGooglePage extends TemplatePage {

    public static final String URL = "https://myaccount.google.com/";

    private static final By hrefGoogleApl = new By.ByXPath("//a[@title='Google apps' and starts-with(@href,'https://www.google.com.ua/intl/')]");
    private static final By hrefGmail = new By.ByXPath("//a[@href='https://mail.google.com/mail/']");

    public MyAccountGooglePage(final WebDriver driver) {
        super(driver);
    }

    public void clickOnGoogleApplicationMenu() {
        clickWithWaiting(hrefGoogleApl);
    }

    public void clickOnGmail() {
        clickWithWaiting(hrefGmail);
    }

    public InboxGmailPage waitForInboxGmailPage() {
        waitForRedirectedPage(InboxGmailPage.URL);
        return new InboxGmailPage(driver);
    }

}