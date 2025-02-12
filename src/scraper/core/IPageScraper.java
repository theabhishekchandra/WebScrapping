package scraper.core;

import scraper.models.ScrapeResult;

import java.util.List;

public interface IPageScraper {
    List<ScrapeResult> scrapePage(String url);
}