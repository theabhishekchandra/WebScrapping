package mail;

import java.io.IOException;

public interface IEmailBodyGenerator {
    String generateEmailBody(String prompt) throws IOException;
}
