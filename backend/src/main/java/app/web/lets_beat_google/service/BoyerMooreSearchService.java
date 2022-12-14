package app.web.lets_beat_google.service;

public interface BoyerMooreSearchService {

  /**
   * Count the times a String pattern appears in a String text.
   * 
   * @param text
   * @param pattern
   * @return the count of the pattern appears
   */
  int search(String text, String pattern);
}
