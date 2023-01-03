package app.web.lets_beat_google.service;

import app.web.lets_beat_google.model.Website;

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
