package com.example.demo.service.impl;

import java.io.IOException;
import java.net.URI;
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
    ArrayList<Website> linkList = new ArrayList<>();
    try {
      Elements links = Jsoup
          .connect(url)
          .userAgent("Usre-agent")
          .timeout(5000).get()
          .select("a[href]");
      String domain = new URI(url).getHost();
      for (Element link : links) {
        String uri = link.absUrl("href");
        Website subsite = Website.builder().URL(uri).title(link.text()).build();
        if (uri.startsWith("https://" + domain) && !uri.endsWith(".jpg") && !uri.endsWith(".png")
            && !linkList.contains(subsite)) {
          linkList.add(subsite);
        } else {
          continue;
        }
      }
      return linkList;
    } catch (Exception e) {
      e.printStackTrace();
      return linkList;
    }
  }
}
