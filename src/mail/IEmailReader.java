package mail;

import java.io.IOException;
import java.util.List;

public interface IEmailReader {
    List<String> readEmailsFromExcel(String filePath) throws IOException;
}
