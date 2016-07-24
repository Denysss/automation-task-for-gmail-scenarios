package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InboxGmailPage extends TemplatePage {

    public static final String URL = "https://mail.google.com/mail/u/0/#inbox";

    public final NewEmailGmailDialog newEmailDialog = new NewEmailGmailDialog(driver);
    public final AccountDialog accountDialog = new AccountDialog(driver);
    public final EmailsPage emails = new EmailsPage(driver);
    public final EmailPage email = new EmailPage(driver);

    // TODO Implement language localization
    private static final By btnCompose = new By.ByXPath("//div[@role='button' and text()='COMPOSE' or text()='НАПИСАТИ' or text()='НАПИСАТЬ']");
    private static final By iconAccount = new By.ByXPath("//a[starts-with(@href, 'https://accounts.google.com/SignOutOptions')]");
    private static final By edtSearch = new By.ByXPath("//input[@type='text' and @aria-label='Search']");
    private static final By btnSearch = new By.ByXPath("//button[@aria-label='Search Gmail']");

    public InboxGmailPage(final WebDriver driver) {
        super(driver);
    }

    public void clickOnCompose() {
        clickWithWaiting(btnCompose);
    }

    public void clickOnIconAccount() {
        clickWithWaiting(iconAccount);
    }

    public void setSearchField(String value) {
        sendKeyWithClearingAndWaiting(edtSearch, value);
    }

    public void clickOnSearch() {
        clickWithWaiting(btnSearch);
    }
}
