package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainGooglePage extends TemplatePage {

    public static final String URL = "https://accounts.google.com/";

    private static final By edtEmail = new By.ById("Email");
    private static final By btnNext = new By.ById("next");
    private static final By edtPassword = new By.ById("Passwd");
    private static final By btnSignIn = new By.ById("signIn");

    private static final Logger LOG = LoggerFactory.getLogger(MainGooglePage.class);

    public MainGooglePage(final WebDriver driver) {
        super(driver);
    }

    public void clickOnNext() {
        clickWithWaiting(btnNext);
    }

    public void clickSignIn() {
        clickWithWaiting(btnSignIn);
    }

    public void open() {
        setUpPage(URL);
    }

    public void setEmail(final String email) {
        sendKeyWithClearingAndWaiting(edtEmail, email);
    }

    public void setPassword(final String password) {
        sendKeyWithClearingAndWaiting(edtPassword, password);
    }

    public MyAccountGooglePage waitForMyAccountGooglePage() {
        waitForRedirectedPage(MyAccountGooglePage.URL);
        return new MyAccountGooglePage(driver);
    }

}