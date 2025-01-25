package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
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
                    System.out.println(h2.text()); // Extracts and prints the text inside the h2 tag
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
