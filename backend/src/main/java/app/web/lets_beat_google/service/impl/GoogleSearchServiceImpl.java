package app.web.lets_beat_google.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.lets_beat_google.model.Website;
import app.web.lets_beat_google.service.GoogleSearchService;
import app.web.lets_beat_google.service.WebsiteService;

@Service
public class GoogleSearchServiceImpl implements GoogleSearchService {

  @Autowired
  private WebsiteService websiteService;

  @Override
  public ArrayList<Website> getSearchResult(String query) {

    ArrayList<Website> websites = new ArrayList<>();
    Elements links;
    try {
      StringBuilder url = new StringBuilder("Https://www.google.com/search?q=");
      url.append(URLEncoder.encode(query + " 咖啡", "UTF-8"));
      url.append("&num=25");
      links = websiteService.getLinks(url.toString());
    } catch (Exception e) {
      System.out.println("Error occur when fetching links: " + e.getMessage());
      return new ArrayList<>();
    }

    websites.addAll(links.parallelStream()
        .filter(link -> link.absUrl("href").indexOf("url?q=") != -1)
        .filter(link -> link.absUrl("href").indexOf("&") != -1)
        .filter(link -> link.absUrl("href").indexOf("https://support.google.com") == -1)
        .filter(link -> link.absUrl("href").indexOf("https://accounts.google.com") == -1)
        .filter(link -> !link.absUrl("href").matches(".*\\.(jpg|png|jpeg)$"))
        .map(link -> {
          String url = "";
          try {
            url = URLDecoder.decode(link.absUrl("href").substring(link.absUrl("href").indexOf("q=") + 2,
                link.absUrl("href").indexOf("&sa=")), "UTF-8");
          } catch (UnsupportedEncodingException e) {
            System.out.println("error when decoding: " + e.getMessage());
          }
          String title = link.select("h3").text();
          return Website.builder().URL(url).title(title).build();
        })
        .collect(Collectors.toList()));

    return websites;
  }

  public ArrayList<String> getRelativeSearch(String query) {

    ArrayList<String> relativeSearchs = new ArrayList<>();
    StringBuilder url = new StringBuilder("https://www.google.com/search?q=");
    try {
      url.append(URLEncoder.encode(query + "咖啡", "UTF-8"));
      Elements relatives = Jsoup.connect(url.toString()).userAgent("User-agent").timeout(5000).get()
          .select(".Q71vJc");
      System.out.println(relatives.toString());
      relativeSearchs.addAll(relatives.parallelStream()
          .map(relative -> {
            return relative.text();
          }).collect(Collectors.toList()));
    } catch (Exception e) {
      System.out.println("Error occur when getting relative link: " + e.getMessage());
    }

    System.out.println(relativeSearchs);

    return relativeSearchs;
  }
}
