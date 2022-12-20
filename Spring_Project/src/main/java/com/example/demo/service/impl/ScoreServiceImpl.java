package com.example.demo.service.impl;

import com.example.demo.model.Keyword;
import com.example.demo.model.Website;
import com.example.demo.repository.KeywordRepository;
import com.example.demo.service.ScoreService;

public class ScoreServiceImpl implements ScoreService {

    KeywordRepository keywordList = new KeywordRepository();

    @Override
    public void calculateScore(Website website) {

        BoyerMooreSearchServiceImpl bmSearch = new BoyerMooreSearchServiceImpl();
        for (Keyword keyword : keywordList.getKeywordList()) {
            int score = bmSearch.search(website.getContent(), keyword.getText());
            website.setScore(website.getScore() + score);
        }
    }
}
