package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.KeyPresser;

import java.io.File;

public class NewEmailGmailDialog extends TemplatePage {

    private static final By edtTo = new By.ByName("to");
    private static final By edtSubject = new By.ByName("subjectbox");
    private static final By edtBody = new By.ByXPath("//div[@role='textbox']");
    // TODO Implement language localization
    private static final By btnSend = new By.ByXPath("//div[@role='button' and text()='Send' or text()='Надіслати' or text()='Отправить']");
    private static final By btnAttachFiles = new By.ByXPath("//div[@role='button' and @command='Files']");
    private static final String xPathLinkToAttachedFile = "//a//div[text()='" + REPLACE_STRING + "']";

    NewEmailGmailDialog(WebDriver driver) {
        super(driver);
    }

    public void setTo(String email) {
        sendKeyWithClearingAndWaiting(edtTo, email);
    }

    public void setSubject(String subject) {
        sendKeyWithClearingAndWaiting(edtSubject, subject);
    }

    public void setBody(String body) {
        sendKeyWithClearingAndWaiting(edtBody, body);
    }

    public void clickOnAttachFiles() {
        clickWithWaiting(btnAttachFiles);
    }

    public void clickOnSend() {
        clickWithWaiting(btnSend);
        waitUntilElementDisappear(btnSend);
    }

    public void attachFile(File file) {
        // TODO It's not stable code, remove sleeps
        sleep(3000);
        KeyPresser.press(file.getAbsolutePath());
        sleep(1000);
        KeyPresser.pressEnter();
        sleep(3000);
        waitOnAttachedFile(file.getName());
    }

    private void waitOnAttachedFile(String fileName) {
        final By file = new By.ByXPath(xPathLinkToAttachedFile.replaceAll(REPLACE_STRING, fileName));
        waitUntilElementAppear(file);
    }
}