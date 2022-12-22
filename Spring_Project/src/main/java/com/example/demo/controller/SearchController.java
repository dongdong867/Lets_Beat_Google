package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Website;
import com.example.demo.service.impl.GoogleSearchServiceImpl;
import com.example.demo.service.impl.ScoreServiceImpl;
import com.example.demo.service.impl.WebsiteServiceImpl;

@RestController
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

  @GetMapping("/{query}")
  public ArrayList<Website> getSearchResult(@PathVariable("query") String query) {

    GoogleSearchServiceImpl googleSearchServiceImpl = new GoogleSearchServiceImpl();
    ArrayList<Website> websites = googleSearchServiceImpl.getSearchResult(query);

    WebsiteServiceImpl websiteServiceImpl = new WebsiteServiceImpl();
    ScoreServiceImpl scoreServiceImpl = new ScoreServiceImpl();

    for (Website website : websites) {
      website.setContent(websiteServiceImpl.getContent(website.getURL()));
      scoreServiceImpl.calculateScore(website);
      ArrayList<Website> subpagesList = websiteServiceImpl.getLinks(website.getURL());
      for (Website subpage : subpagesList) {
        System.out.println(subpage.getURL());
        subpage.setContent(websiteServiceImpl.getContent(subpage.getURL()));
        scoreServiceImpl.calculateScore(subpage);
        website.addSubpage(subpage);
      }
      scoreServiceImpl.calculateTotalScore(website);
    }

    return websites;
  }
}
