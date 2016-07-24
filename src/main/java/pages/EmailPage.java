package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailPage extends TemplatePage {

    private static final String xPathAttachment = "//span[text()='" + REPLACE_STRING + "']";
    private static final By attachmentContent = new By.ByXPath("//div[@role='document']/pre");
    private static final By attachmentContentClose = new By.ByXPath("//div[@role='toolbar']//div[@role='button' and @aria-label='Close']");

    EmailPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAttachmentToDisplay(String attachmentName) {
        final By attachment = new By.ByXPath(xPathAttachment.replaceAll(REPLACE_STRING, attachmentName));
        clickWithWaiting(attachment);
    }

    public String getContent() {
        waitUntilElementAppear(attachmentContent);
        return getText(attachmentContent);
    }

    public void clickOnCrossOfAttachmentContent() {
        clickWithWaiting(attachmentContentClose);
    }
}
