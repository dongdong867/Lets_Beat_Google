package com.example.demo.Model;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearcher {

    // public void getSearchResult(String query) {
    // try {
    // String google = "https://www.google.com/search?q=";
    // String charset = "UTF-8";
    // String userAgent = "User-agent";

    // Elements links = Jsoup
    // .connect(google + URLEncoder.encode(query, charset))
    // .userAgent(userAgent)
    // .timeout(5000).get()
    // .select("a[href]");

    // for (Element link : links) {
    // String title = link.text();
    // String url = link.absUrl("href");
    // if (url.indexOf("q=") == -1) {
    // continue;
    // }
    // url = URLDecoder.decode(url.substring(url.indexOf("=") + 1,
    // url.indexOf("&")), "UTF-8");

    // if (!url.startsWith("http") || url.startsWith("https://support.google.com")
    // || url.startsWith("https://accounts.google.com")) {
    // continue; // Ads/news/etc and other crap.
    // } else {
    // System.out.println(title + " | " + url);
    // }
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public ArrayList<Website> getSearchResult(String query) {
        try {
            String searchURL = "https://www.google.com/search?q=";

            Elements links = Jsoup
                    .connect(searchURL + URLEncoder.encode(query, "UTF-8"))
                    .userAgent("User-agent")
                    .timeout(5000).get()
                    .select("a[href]");

            ArrayList<Website> websiteList = new ArrayList<>();
            for (Element link : links) {
                String uri = link.absUrl("href");
                if (uri.indexOf("q=") == -1) {
                    continue;
                }
                uri = URLDecoder.decode(uri.substring(uri.indexOf("=") + 1, uri.indexOf("&")), "UTF-8");
                if (!uri.startsWith("http") || uri.startsWith("https://support.google.com")
                        || uri.startsWith("https://accounts.google.com")) {
                    continue;
                } else {
                    websiteList.add(Website.builder().URL(uri).title(link.text()).build());
                }
            }
            return websiteList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
