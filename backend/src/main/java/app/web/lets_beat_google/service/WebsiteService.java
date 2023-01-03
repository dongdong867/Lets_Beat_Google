package app.web.lets_beat_google.service;

import java.util.ArrayList;

import org.jsoup.select.Elements;

import app.web.lets_beat_google.model.Website;

public interface WebsiteService {

  /**
   * Get the content of the website with a given url.
   * 
   * @param url
   * @return the content of the website
   */
  String getContent(String url);

  /**
   * Get the subsites in the website with a given url.
   * 
   * @param url
   * @return arraylist contains websites appeared in the website
   */
  ArrayList<Website> getSubsites(String url);

  /**
   * Get all links inside a given website.
   * 
   * @param url
   * @return Object Elements contain links
   */
  Elements getLinks(String url);
}
