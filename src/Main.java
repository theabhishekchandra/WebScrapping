import scraper.core.ScraperService;
import scraper.core.WebPageInputs;
import scraper.utils.Constants;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ScraperService scraperService = new ScraperService();
        WebPageInputs inputs = new WebPageInputs();
        scraperService.startScraping(inputs.baseUrl());
    }
}