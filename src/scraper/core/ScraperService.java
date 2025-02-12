package scraper.core;

import scraper.impl.PageScraper;
import scraper.models.ScrapeResult;

import java.util.ArrayList;
import java.util.List;

public class ScraperService {

    private final IPageScraper pageScraper;
    private final WebPageInputs inputs = new WebPageInputs();

    public ScraperService() {
        this.pageScraper = new PageScraper();
    }

    public void startScraping(String baseUrl) {
//        int currentPage = 1;
//        boolean hasNextPage = true;
//        List<ScrapeResult> allResults = new ArrayList<>();
        List<ScrapeResult> results = pageScraper.scrapePage(baseUrl);
        System.out.println("\nScraped Results:");
        for (ScrapeResult result : results) {
            System.out.println(result);
        }

        /*while (hasNextPage) {
            String url = baseUrl + "?page=" + currentPage;
            System.out.println("Scraping page: " + currentPage);

            List<ScrapeResult> results = pageScraper.scrapePage(inputs.baseUrl());
            if (results.isEmpty()) {
                hasNextPage = false;
                System.out.println("No more pages to scrape.");
            } else {
                allResults.addAll(results);
                currentPage++;
            }

            // Add a delay to avoid getting flagged
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }*/

        /*System.out.println("\nScraped Results:");
        for (ScrapeResult result : allResults) {
            System.out.println(result);
        }*/
    }
}
