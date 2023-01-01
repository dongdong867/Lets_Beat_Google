package com.example.demo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Keyword;
import com.example.demo.model.Website;
import com.example.demo.repository.KeywordDAO;
import com.example.demo.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

  @Autowired
  private BoyerMooreSearchServiceImpl boyerMooreSearchService;

  @Autowired
  private KeywordDAO keywordsDAO;

  @Override
  public void calculateScore(Website website) {
    for (Keyword keyword : keywordsDAO.getKeywordDB()) {
      int score = boyerMooreSearchService.search(website.getContent(), keyword.getText()) * keyword.getWeight();
      website.setScore(website.getScore() + score);
    }
  }

  @Override
  public void calculateTotalScore(Website website) {
    int score = 0;
    for (Website subpage : website.getSubpages()) {
      score += subpage.getScore();
    }
    website.setScore(website.getScore() + (int) (score * 0.5));
  }
}
