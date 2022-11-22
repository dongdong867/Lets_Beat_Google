package com.example.demo.Model;

public class ScoreCalculator {

    KeywordList keywordList = new KeywordList();

    public void calculateScore(Website website) {

        BoyerMooreSearch bmSearch = new BoyerMooreSearch();
        for (Keyword keyword : keywordList.getKeywordList()) {
            int score = bmSearch.search(website.getContent(), keyword.getText());
            website.setScore(website.getScore() + score);
        }
    }
}
