package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountGooglePage extends TemplatePage {

    public static final String URL = "https://myaccount.google.com/";


    private static final By hrefGoogleApl = new By.ByLinkText("https://www.google.com.ua/intl/uk/options/");
    private static final By hrefGmail = new By.ByLinkText("https://mail.google.com/mail/");

    public MyAccountGooglePage(final WebDriver driver) {
        super(driver);
    }

}