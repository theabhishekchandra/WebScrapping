package mail;

import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmailReader implements IEmailReader {

    @Override
    public List<String> readEmailsFromExcel(String filePath) throws IOException {
        List<String> emailAddresses = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Assuming the first row is the header and email addresses are in the first column
            for (Row row : sheet) {
                Cell emailCell = row.getCell(0); // Adjust the index if emails are in another column
                if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
                    emailAddresses.add(emailCell.getStringCellValue());
                }
            }
        }

        return emailAddresses;
    }
}
