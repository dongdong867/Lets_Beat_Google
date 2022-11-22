package com.example.demo.Model;

import java.util.HashMap;

public class BoyerMooreSearch {

    public int search(String text, String pattern) {

        int matched = 0, textLength = text.length(), patternLength = pattern.length();

        if (textLength == 0 || patternLength == 0 || textLength < patternLength) {
            return 0;
        }

        HashMap<Character, Integer> badChar = getBadChar(pattern);
        int[] goodSuffix = getGoodSuffix(pattern);
        for (int i = patternLength - 1, j; i < textLength;) {
            for (j = patternLength - 1; text.charAt(i) == pattern.charAt(j); i--, j--) {
                if (j == 0) {
                    matched++;
                    break;
                }
            }
            if (j == patternLength - 1) {
                i += badChar.get(text.charAt(i)) == null ? patternLength : badChar.get(text.charAt(i));
            } else {
                i += Math.max(badChar.get(text.charAt(i)) == null ? patternLength : badChar.get(text.charAt(i)),
                        goodSuffix[j + 1]);
            }
        }
        return matched;
    }

    private HashMap<Character, Integer> getBadChar(String pattern) {

        HashMap<Character, Integer> badChar = new HashMap<>();
        int patternLengthMinusOne = pattern.length() - 1;
        for (int i = 0; i < patternLengthMinusOne; i++) {
            badChar.put(pattern.charAt(i), patternLengthMinusOne - i);
        }
        return badChar;
    }

    private int hasSuffix(String pattern) {

        int patternLength = pattern.length();
        for (int i = 0; i < patternLength - 1; i++) {
            if (pattern.charAt(i) == pattern.charAt(patternLength - 1)) {
                return i;
            }
        }
        return -1;
    }

    private int[] getGoodSuffix(String pattern) {

        int[] goodSuffix = new int[pattern.length()];
        int suffix = hasSuffix(pattern), patternLength = pattern.length();
        if (suffix == -1) {
            for (int i = 0; i < patternLength; i++) {
                goodSuffix[i] = patternLength;
            }
        } else {
            goodSuffix[patternLength - 1] = patternLength - 1 - suffix;
            goodSuffix[0] = patternLength - 1;
            for (int i = patternLength - 2; i > 0; i--) {
                for (int x = 0, y = i; x < i; x++) {
                    if (pattern.charAt(x) == pattern.charAt(y)) {
                        y++;
                    }
                    if (y == patternLength) {
                        goodSuffix[i] = patternLength - 1 - x;
                        break;
                    }
                    goodSuffix[i] = goodSuffix[i + 1];
                }
            }
            for (int i = 0, j = patternLength; i < patternLength; i++, j--) {
                goodSuffix[i] += j;
            }
        }
        return goodSuffix;
    }
}
