package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDialog extends TemplatePage {

    private static final By btnLogOut = new By.ByXPath("//a[starts-with(@href, 'https://accounts.google.com/Logout')]");

    AccountDialog(WebDriver driver) {
        super(driver);
    }

    public MainGooglePage clickOnLogOut() {
        clickWithWaiting(btnLogOut);
        waitForRedirectedPage(MainGooglePage.URL);
        return new MainGooglePage(driver);
    }
}
