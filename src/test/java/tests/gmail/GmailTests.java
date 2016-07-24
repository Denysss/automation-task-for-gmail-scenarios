package tests.gmail;

import entities.Account;
import entities.Email;
import org.junit.Test;
import pages.InboxGmailPage;
import pages.MainGooglePage;
import pages.MyAccountGooglePage;
import tests.TemplateTest;
import utils.FileUtil;
import utils.RandomData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *  Автоматизировать выполнение следующего теста:
 *  1. Залогиниться в gmail под тестовым аккаунтом_1;
 *  2. Отправить письмо с определенным выражением в теме и приложенным текстовым файлом (несколько известных строк);
 *  3. Зайти в gmail под тестовым аккаунтом_2, которым был адресатом отправленного письма;
 *  4. Найти письмо по определенному выражению из темы;
 *  5. Проверить строки из файла в приложении к письму.
 */

public class GmailTests extends TemplateTest {

    // TODO Move test data to DataProvider
    // Test Data
    private final Account ACC1 = new Account("Account1", "account1@example.com", "Password");
    private final Account ACC2 = new Account("Account2", "account2@example.com", "Password");
    private final Email EMAIL = new Email(ACC1.getEmail(), RandomData.getString(16), "body", "");
    private final File ATTACH_FILE = FileUtil.generateFile("file.txt", RandomData.getString(32));
    private final String expectedContent = FileUtil.getFileContent(ATTACH_FILE);

    // TODO Move PageObjects to PageFactory
    private MainGooglePage mainGooglePage;
    private MyAccountGooglePage myAccountGooglePage;
    private InboxGmailPage inboxGmailPage;

    @Test
    public void SimpleTest() {
        logInToGoogle(ACC1);
        moveToGmail();
        sendEmailWithAttachment(EMAIL, ATTACH_FILE);
        logOut();

        logInToGoogle(ACC2);
        moveToGmail();
        findEmailBySubject(EMAIL.getSubject());
        openEmailBySubject(EMAIL.getSubject());
        openAttachmentByName(EMAIL.getAttachmentName());
        assertThat(getEmailContent(), is(equalTo(expectedContent)));
        closeEmail();
        logOut();
    }

    // TODO Mode all privet methods to StepDefinition
    private void logInToGoogle(Account acc) {
        mainGooglePage = new MainGooglePage(driver);
        mainGooglePage.open();
        mainGooglePage.setEmail(acc.getEmail());
        mainGooglePage.clickOnNext();
        mainGooglePage.setPassword(acc.getPassword());
        mainGooglePage.clickSignIn();
        myAccountGooglePage = mainGooglePage.waitForMyAccountGooglePage();
    }

    private void moveToGmail() {
        myAccountGooglePage.clickOnGoogleApplicationMenu();
        myAccountGooglePage.clickOnGmail();
        inboxGmailPage = myAccountGooglePage.waitForInboxGmailPage();
    }

    private void sendEmailWithAttachment(Email email, File file) {
        inboxGmailPage.clickOnCompose();
        inboxGmailPage.newEmailDialog.setTo(email.getTo());
        inboxGmailPage.newEmailDialog.setSubject(email.getSubject());
        inboxGmailPage.newEmailDialog.setBody(email.getBody());
        inboxGmailPage.newEmailDialog.clickOnAttachFiles();
        email.setAttachmentName(file.getName());
        inboxGmailPage.newEmailDialog.attachFile(file);
        inboxGmailPage.newEmailDialog.clickOnSend();

    }

    private void logOut() {
        inboxGmailPage.clickOnIconAccount();
        mainGooglePage = inboxGmailPage.accountDialog.clickOnLogOut();
        // TODO Delete only special cookies
        driver.manage().deleteAllCookies();
    }

    private void findEmailBySubject(String subject) {
        inboxGmailPage.setSearchField(subject);
        inboxGmailPage.clickOnSearch();
    }

    private void openEmailBySubject(String subject) {
        inboxGmailPage.emails.clickOnEmailBySubject(subject);
    }

    private void openAttachmentByName(String attachmentName) {
        inboxGmailPage.email.clickOnAttachmentToDisplay(attachmentName);
    }

    private void closeEmail() {
        inboxGmailPage.email.clickOnCrossOfAttachmentContent();
    }

    private String getEmailContent() {
        return inboxGmailPage.email.getContent();
    }
}
