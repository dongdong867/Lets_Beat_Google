package app.web.lets_beat_google.controller;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.web.lets_beat_google.model.Website;
import app.web.lets_beat_google.service.impl.GoogleSearchServiceImpl;
import app.web.lets_beat_google.service.impl.ScoreServiceImpl;
import app.web.lets_beat_google.service.impl.WebsiteServiceImpl;

@CrossOrigin
@Controller
@RestController
@RequestMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8" })

public class SearchController {

  @Autowired
  private GoogleSearchServiceImpl googleSearchService;

  @Autowired
  private WebsiteServiceImpl websiteService;

  @Autowired
  private ScoreServiceImpl scoreService;

  @GetMapping(value = "/{query}", produces = { MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8" })
  public ResponseEntity<ArrayList<Website>> getSearchResult(@PathVariable("query") String query) {

    ArrayList<Website> websites = googleSearchService.getSearchResult(query);

    ForkJoinPool forkJoinPool = new ForkJoinPool();
    forkJoinPool.submit(() -> websites.parallelStream().forEach(website -> {
      website.setContent(websiteService.getContent(website.getURL()));
      scoreService.calculateScore(website);
      website.setSubpages(websiteService.getSubsites(website.getURL()));

      for (int i = 0; i < 5; i++) {
        try {
          int random = (int) Math.floor(Math.random() * website.getSubpages().size());
          Website subsite = website.getSubpages().get(random);
          subsite.setContent(websiteService.getContent(subsite.getURL()));
          scoreService.calculateScore(subsite);
          website.addSubpage(subsite);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      scoreService.calculateTotalScore(website);

    })).join();

    websites.sort((a, b) -> {
      return a.getScore() < b.getScore() ? 1 : -1;
    });

    System.out.println("process finish");
    return ResponseEntity.ok(websites);

  }

  @GetMapping(value = "/relative/{query}", produces = { MediaType.APPLICATION_JSON_VALUE,
      "application/json;charset=UTF-8" })
  public ArrayList<String> getRelativeKeywords(@PathVariable("query") String query) {

    ArrayList<String> relativeKeywords = googleSearchService.getRelativeSearch(query);
    return relativeKeywords;
  }
}
