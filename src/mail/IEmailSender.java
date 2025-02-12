package mail;

public interface IEmailSender {
    void sendEmail(String senderEmail, String senderPassword, String recipientEmail, String subject, String messageBody);
}