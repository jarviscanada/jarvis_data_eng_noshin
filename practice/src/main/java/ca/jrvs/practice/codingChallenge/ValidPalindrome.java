package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {

  /**
   * Big-O: O(n), where n is the length of string
   * Justification: whole string is traversed once
   *
   * @param s input string
   * @return true if valid, false otherwise
   */
  public boolean isPalindromeRegular(String s) {
    int i = 0, j = s.length() - 1;
    s = s.toLowerCase();
    while (i <= j) {
      while (i < s.length() && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (j >= 0 && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }
      if (i <= j && s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  /**
   * Big-O: O(n), where n is the length of string
   * Justification: whole string is traversed once
   *
   * @param s input string
   * @return true if valid, false otherwise
   */
  public boolean isPalindromeRecursion(String s) {
    return isPalindrome(s.toLowerCase(), 0, s.length() - 1);
  }

  private boolean isPalindrome(String s, int i, int j) {
    if (i > j) {
      return true;
    } else {
      if (!Character.isLetterOrDigit(s.charAt(i))) {
        return isPalindrome(s, i + 1, j);
      } else if (!Character.isLetterOrDigit(s.charAt(j))) {
        return isPalindrome(s, i, j - 1);
      }
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      } else {
        return isPalindrome(s, i + 1, j - 1);
      }
    }
  }
}
