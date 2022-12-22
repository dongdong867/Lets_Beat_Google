package com.example.demo.service.impl;

import com.example.demo.model.Keyword;
import com.example.demo.model.Website;
import com.example.demo.repository.KeywordDAO;
import com.example.demo.service.ScoreService;

public class ScoreServiceImpl implements ScoreService {

  private KeywordDAO keywordsDAO = new KeywordDAO();

  @Override
  public void calculateScore(Website website) {

    BoyerMooreSearchServiceImpl bmSearch = new BoyerMooreSearchServiceImpl();
    for (Keyword keyword : keywordsDAO.getKeywordDB()) {
      int score = bmSearch.search(website.getContent(), keyword.getText());
      website.setScore(website.getScore() + score);
    }
  }

  public void calculateTotalScore(Website website) {
    int score = 0;
    for (Website subpage : website.getSubpages()) {
      score += subpage.getScore();
    }
    website.setScore(website.getScore() + (int) (score * 0.5));
  }
}
