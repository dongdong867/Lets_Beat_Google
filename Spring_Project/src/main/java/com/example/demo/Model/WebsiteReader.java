package com.example.demo.Model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteReader {

    public String getContent(String url) {
        try {
            return Jsoup.connect(url).get().body().text();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Website> getLinks(String url) {
        try {
            ArrayList<Website> linkList = new ArrayList<>();
            Elements links = Jsoup.connect(url).get().select("a[href]");
            for (Element link : links) {
                linkList.add(Website.builder().URL(link.attr("href")).build());
            }
            return linkList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
