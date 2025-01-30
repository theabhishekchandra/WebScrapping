package scraper.utils;

public class Constants {

    // Base URL for scraping
    public static final String BASE_URL = "https://wellfound.com/startups/location/india";

    // User-Agent header for HTTP requests
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36";

    // Common headers for HTTP requests
    public static final String HEADER_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8";
    public static final String HEADER_ACCEPT_LANGUAGE = "en-US,en;q=0.5";
    public static final String REFERRER_URL = "https://www.google.com/";

    // CSS Selectors
    public static final String COMPANY_NAME_SELECTOR = "h2.inline.text-md.font-semibold";
    public static final String NEXT_PAGE_SELECTOR = "li.styles_page-rc-style__GH3LC a[aria-label^=Go to page]";
}