package entities;

public class Email {

    private String to;
    private String subject;
    private String body;
    private String attachmentName;

    public Email(String to, String subject, String body, String attachmentName) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.attachmentName = attachmentName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
