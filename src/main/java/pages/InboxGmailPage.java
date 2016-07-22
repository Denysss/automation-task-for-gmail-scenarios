package pages;


import org.openqa.selenium.WebDriver;

public class InboxGmailPage extends TemplatePage {

    public static final String URL = "https://mail.google.com/mail/u/0/#inbox";

    public InboxGmailPage(final WebDriver driver) {
        super(driver);
    }
}
