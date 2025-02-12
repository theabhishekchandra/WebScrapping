package scraper.models;


public class ScrapeResult {
    private final String companyName;

    public ScrapeResult(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Company: " + companyName;
    }
}

