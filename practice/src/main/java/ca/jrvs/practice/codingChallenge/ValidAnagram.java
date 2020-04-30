package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class ValidAnagram {

  /**
   * Big-O: O(nlogn)
   * Justification: O(nlogn) for sorting, O(n) for equals()
   *
   * @param s first string
   * @param t second string
   * @return true if anagram, false otherwise
   */
  public boolean isAnagramWithSorting(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    char[] sortedS = s.toCharArray();
    Arrays.sort(sortedS);
    char[] sortedT = t.toCharArray();
    Arrays.sort(sortedT);
    if (Arrays.equals(sortedS, sortedT)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Big-O: O(n)
   * Justification: O(n) no nested loop
   *
   * @param s first string
   * @param t second string
   * @return true if anagram, false otherwise
   */
  public boolean isAnagramWithoutSorting(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] check = new int[26];
    boolean allZeroes = true;
    for (int i = 0; i < s.length(); i++) {
      check[s.charAt(i) - 'a']++;
      check[t.charAt(i) - 'a']--;
    }
    for (int i = 0; i < 26; i++) {
      if (check[i] != 0) {
        return false;
      }
    }
    return true;
  }
}
