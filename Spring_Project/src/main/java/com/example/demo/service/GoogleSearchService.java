package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.Website;

public interface GoogleSearchService {

  /**
   * Get the search result of Google with the input query.
   * 
   * @param query
   * @return the arraylist contains the search result
   */
  ArrayList<Website> getSearchResult(String query);
}
