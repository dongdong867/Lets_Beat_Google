package app.web.lets_beat_google.service;

import java.util.ArrayList;

import app.web.lets_beat_google.model.Website;

public interface GoogleSearchService {

  /**
   * Get the search result of Google with the input query.
   * 
   * @param query
   * @return the arraylist contains the search result
   */
  ArrayList<Website> getSearchResult(String query);
}
