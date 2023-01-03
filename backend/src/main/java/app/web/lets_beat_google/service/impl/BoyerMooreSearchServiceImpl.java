package app.web.lets_beat_google.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import app.web.lets_beat_google.service.BoyerMooreSearchService;

@Service
public class BoyerMooreSearchServiceImpl implements BoyerMooreSearchService {

  @Override
  public int search(String text, String pattern) {

    text = text.toLowerCase();
    pattern = pattern.toLowerCase();

    int matched = 0, textLength = text.length(), patternLength = pattern.length();
    if (textLength == 0 || patternLength == 0 || textLength < patternLength)
      return 0;

    HashMap<Character, Integer> badChar = getBadChar(pattern);
    int[] goodSuffix = getGoodSuffix(pattern);

    for (int i = 0, j; i <= textLength - patternLength;) {
      for (j = patternLength - 1; j >= 0 && text.charAt(i + j) == pattern.charAt(j); j--) {
      }
      if (j < 0) {
        matched++;
        i += goodSuffix[0];
      } else {
        i += Math.max(goodSuffix[j + 1],
            badChar.get(text.charAt(i + j)) == null ? goodSuffix[0] : badChar.get(text.charAt(i + j)));
      }
    }

    return matched;
  }

  private HashMap<Character, Integer> getBadChar(String pattern) {

    int patternLengthMinusOne = pattern.length() - 1;

    HashMap<Character, Integer> badChar = new HashMap<>();
    badChar.put(pattern.charAt(patternLengthMinusOne), pattern.length());

    for (int i = 0; i < patternLengthMinusOne; i++)
      badChar.put(pattern.charAt(i), patternLengthMinusOne - i);

    return badChar;

  }

  private int[] getGoodSuffix(String pattern) {

    int patternLength = pattern.length();
    int[] goodSuffix = new int[patternLength + 1], temp = new int[patternLength + 1];

    int i = patternLength, j = patternLength + 1;
    temp[i] = j;
    while (i > 0) {
      while (j <= patternLength && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
        if (goodSuffix[j] == 0)
          goodSuffix[j] = j - i;
        j = temp[j];
      }
      i--;
      j--;
      temp[i] = j;
    }

    j = temp[0];
    for (i = 0; i <= patternLength; i++) {
      if (goodSuffix[i] == 0)
        goodSuffix[i] = j;
      if (i == j)
        j = temp[j];
    }
    return goodSuffix;
  }
}
