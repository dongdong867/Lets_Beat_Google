package app.web.lets_beat_google.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    StringBuilder searchURL = new StringBuilder("https://www.google.com/search?q=");
    try {
      searchURL.append(URLEncoder.encode(query + "咖啡", "UTF-8"));
      searchURL.append("&num=50");
      System.out.println(searchURL.toString());
      Elements links = websiteService.getLinks(searchURL.toString());

      websites.addAll(links.parallelStream()
          .filter(link -> link.absUrl("href").indexOf("url?q=") != -1)
          .filter(link -> link.absUrl("href").indexOf("&") != -1)
          .filter(link -> link.absUrl("href").indexOf("https://support.google.com") == -1)
          .filter(link -> link.absUrl("href").indexOf("https://accounts.google.com") == -1)
          .filter(link -> !link.absUrl("href").matches(".*\\.(jpg|png|jpeg)$"))
          .map(link -> {
            String url = "";
            try {
              url = URLDecoder.decode(link.absUrl("href").substring(link.absUrl("href").indexOf("q=") + 2), "UTF-8");
            } catch (UnsupportedEncodingException e) {
              System.out.println("error when decoding: " + e.getMessage());
            }
            return Website.builder().URL(url).title(link.select("h3").text()).build();
          })
          .collect(Collectors.toList()));
    } catch (IOException e) {
      System.out.println("Error occurs at getting search result: " + e.getMessage());
    }

    return websites;
  }
}
