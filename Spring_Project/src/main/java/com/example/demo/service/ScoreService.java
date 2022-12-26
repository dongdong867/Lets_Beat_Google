package com.example.demo.service;

import com.example.demo.model.Website;

public interface ScoreService {

  /**
   * Calculate the score of a website with the count times score of all keywords
   * in keywords list.
   * 
   * @param website
   */
  void calculateScore(Website website);

  /**
   * Calculate the total score with the score of the page and its subpages.
   * 
   * @param website
   */
  void calculateTotalScore(Website website);
}
