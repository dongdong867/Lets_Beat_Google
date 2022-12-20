package com.example.demo.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.model.Website;
import com.example.demo.service.WebsiteService;

public class WebsiteServiceImpl implements WebsiteService {

    @Override
    public String getContent(String url) {
        try {
            return Jsoup.connect(url).get().body().text();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
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
