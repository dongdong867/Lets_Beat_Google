package app.web.lets_beat_google.service.impl;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import app.web.lets_beat_google.model.Website;
import app.web.lets_beat_google.service.WebsiteService;

@Service
public class WebsiteServiceImpl implements WebsiteService {

  @Override
  public String getContent(String url) {
    try {
      return Jsoup.connect(url).get().body().text();
    } catch (IOException e) {
      System.out.println("Error while retrieving content: " + e.getMessage());
      return "";
    }
  }

  @Override
  public ArrayList<Website> getSubsites(String url) {

    Elements links = getLinks(url);
    String domain;
    try {
      domain = new URI(url).getHost();
    } catch (Exception e) {
      System.out.println("Error while getting domain: " + e.getMessage());
      return new ArrayList<>();
    }
    List<Website> subsites = links.parallelStream()
        .filter(link -> link.absUrl("href").startsWith("https:// " + domain))
        .filter(link -> !link.absUrl("href").matches(".*\\.(jpg|png|jpeg)$"))
        .filter(link -> link.absUrl("href").indexOf("https://tw.news.yahoo.com") != -1)
        .map(link -> Website.builder().URL(link.absUrl("href")).title(link.text()).build())
        .collect(Collectors.toList());

    return (ArrayList<Website>) subsites;
  }

  @Override
  public Elements getLinks(String url) {
    try {
      return Jsoup.connect(url).userAgent("User-agent").timeout(5000).get().select("a[href]");
    } catch (Exception e) {
      System.out.println("Error while getting links from URL: " + e.getMessage());
      return new Elements();
    }
  }
}
