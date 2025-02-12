package scraper.core;

import java.util.Scanner;

public class WebPageInputs implements IWebPageInputs {
    @Override
    public String baseUrl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Base URL: ");
        return scanner.next();
    }

    @Override
    public String nextPageTag() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Next Page Tag: ");
        //        value = "li.styles_page-rc-style__GH3LC a[aria-label^=Go to page]";
        return scanner.next();
    }

    @Override
    public String companyNameTag() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Company Name Tag: ");
        return scanner.next();
    }
}
