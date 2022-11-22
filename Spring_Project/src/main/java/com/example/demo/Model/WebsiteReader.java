package com.example.demo.Model;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;

public class WebsiteReader {

    public void getLinks(String query) {
        try {
            String google = "https://www.google.com/search?q=";
            String charset = "UTF-8";
            String userAgent = "User-agent";

            Elements links = Jsoup
                    .connect(google + URLEncoder.encode(query, charset))
                    .userAgent(userAgent)
                    .timeout(5000).get()
                    .select("a[href]");

            for (Element link : links) {
                String title = link.text();
                String url = link.absUrl("href"); // URL FORMAT: "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>"
                if (url.indexOf("q=") == -1) {
                    continue;
                }
                url = URLDecoder.decode(url.substring(url.indexOf("=") + 1, url.indexOf("&")), "UTF-8");

                if (!url.startsWith("http") || url.startsWith("https://support.google.com")
                        || url.startsWith("https://accounts.google.com")) {
                    continue; // Ads/news/etc and other crap.
                } else {
                    System.out.println(title + " | " + url);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
