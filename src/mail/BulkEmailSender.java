package mail;

import java.io.IOException;
import java.util.List;

public class BulkEmailSender {

    public static void main(String[] args) {
        // Specify the file path
        String filePath = "Emails.xlsx";
        String senderEmail = "ac927920@gmail.com";
        String senderPassword = "kgcs ldrx kxui axco";
        String subject = "Bulk Email Subject";

        IEmailSender emailSender = new EmailSender();
//        IEmailBodyGenerator emailBodyGenerator = new EmailBodyGenerator();
//        IEmailReader emailReader = new EmailReader();

//        try {
            // Read email addresses from the file
//            List<String> emailAddresses = emailReader.readEmailsFromExcel(filePath);

            // Generate email body using AI
//            String messageBody = emailBodyGenerator.generateEmailBody("Hello, please read this email.");

            // Send emails
//            for (String recipient : emailAddresses) {
                emailSender.sendEmail(senderEmail, senderPassword, "ac928920@gmail.com", subject, "messageBody");
//            }
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}



