package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.Website;

public interface WebsiteService {

  /**
   * Get the content of the website with a given url.
   * 
   * @param url
   * @return the content of the website
   */
  String getContent(String url);

  /**
   * Get the links in the website with a given url.
   * 
   * @param url
   * @return arraylist contains websites appeared in the website
   */
  ArrayList<Website> getLinks(String url);
}
