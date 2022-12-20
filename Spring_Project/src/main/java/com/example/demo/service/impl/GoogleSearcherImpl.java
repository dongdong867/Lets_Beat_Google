package com.example.demo.service.impl;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.model.Website;
import com.example.demo.service.GoogleSearchService;

public class GoogleSearcherImpl implements GoogleSearchService {

    @Override
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
