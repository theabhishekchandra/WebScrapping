package scraper.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.IPageScraper;
import scraper.core.WebPageInputs;
import scraper.models.ScrapeResult;
import scraper.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageScraper implements IPageScraper {
    private final WebPageInputs inputs = new WebPageInputs();

    @Override
    public List<ScrapeResult> scrapePage(String url) {
        List<ScrapeResult> results = new ArrayList<>();
        int currentPage = 1;
        boolean hasNextPage = true;
        String companyNameTag = inputs.companyNameTag();
        String nextPageTag = inputs.nextPageTag();

        try {
            String pageUrl = url + "?page=" + currentPage;
            Document doc = Jsoup.connect(pageUrl)
                    .userAgent(Constants.USER_AGENT)
                    .header("Accept", Constants.HEADER_ACCEPT)
                    .header("Accept-Language", Constants.HEADER_ACCEPT_LANGUAGE)
                    .referrer(Constants.REFERRER_URL)
                    .get();

            while (hasNextPage) {

                Elements companyElements = doc.select(companyNameTag);

                for (Element element : companyElements) {
                    String companyName = element.text();
                    results.add(new ScrapeResult(companyName));
                }
                // Check for the next page
                Elements nextPageLink = doc.select(nextPageTag);
                if (!nextPageLink.isEmpty()) {
                    currentPage++;  // Move to the next page
                    System.out.println("\nPage Number : " + currentPage);
                } else {
                    hasNextPage = false;  // No more pages
                    System.out.println("\nNo more pages to scrape.");
                }

            }
        } catch (IOException e) {
            System.err.println("Failed to scrape page: " + url);
            e.printStackTrace();
        }
        return results;
    }
}
