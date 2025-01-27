package scraper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    public static void main(String[] args) {
        // List to store scraped data
        List<JobData> jobDataList = new ArrayList<>();

        try {
            // URL to scrape (starting page)
            String baseUrl = "https://wellfound.com/startups/location/india";

            // Start scraping from the first page
            int currentPage = 1;
            boolean hasNextPage = true;

            while (hasNextPage) {
                // Construct the URL for the current page
                String url = baseUrl + "?page=" + currentPage;
                System.out.println("Scraping page: " + currentPage);

                // Fetch the HTML document from the URL
                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .referrer("https://www.google.com/")
                        .get();

                // Print the title of the page
                System.out.println("Title: " + doc.title());

                // Scrape <h2> tags with the specific class (inline text-md font-semibold)
                Elements h2Tags = doc.select("h2.inline.text-md.font-semibold");
                System.out.println("\nCompany Names (h2 with specific class):");
                for (Element h2 : h2Tags) {
                    String companyName = h2.text();
                    System.out.println(companyName); // Extracts and prints the text inside the h2 tag

                    // Add the data to the list
                    jobDataList.add(new JobData(companyName, currentPage));
                }

                // Check for the next page
                Elements nextPageLink = doc.select("li.styles_page-rc-style__GH3LC a[aria-label^=Go to page]");
                if (!nextPageLink.isEmpty()) {
                    currentPage++;  // Move to the next page
                } else {
                    hasNextPage = false;  // No more pages
                    System.out.println("\nNo more pages to scrape.");
                }
            }

            // Write the data to an Excel file
            writeDataToExcel(jobDataList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataToExcel(List<JobData> jobDataList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Job Data");

        // Add header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Company Name");
        headerRow.createCell(1).setCellValue("Page Number");

        // Add job data rows
        int rowCount = 1;
        for (JobData jobData : jobDataList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(jobData.getCompanyName());
            row.createCell(1).setCellValue(jobData.getPageNumber());
        }

        // Write the workbook to a file
        try (FileOutputStream outputStream = new FileOutputStream("ScrapedData.xlsx")) {
            workbook.write(outputStream);
            System.out.println("\nData successfully saved to Excel: ScrapedData.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Inner class to store job data
    static class JobData {
        private final String companyName;
        private final int pageNumber;

        public JobData(String companyName, int pageNumber) {
            this.companyName = companyName;
            this.pageNumber = pageNumber;
        }

        public String getCompanyName() {
            return companyName;
        }

        public int getPageNumber() {
            return pageNumber;
        }
    }
}
