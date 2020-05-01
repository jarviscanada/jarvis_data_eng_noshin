package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Check-if-a-String-contains-only-digits-3a442f4e6d4d44bfa592c04c14f2f353
 */
public class OnlyDigitsInString {

  /**
   * Big-O: O(n), where n is length of string
   * Justification: iterate over string only once
   *
   * @param s string to be tested
   * @return true if only digits present, false otherwise
   */
  public boolean hasOnlyDigitsASCII(String s) {
    if (s.length() == 0) {
      return false;
    }
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) < '0' || s.charAt(i) > '9') {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n), where n is length of string
   * Justification: iterate over string only once in Integer.valueOf()
   *
   * @param s string to be tested
   * @return true if only digits present, false otherwise
   */
  public boolean hasOnlyDigitsAPI(String s) {
    try {
      Integer.valueOf(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Big-O: O(n+m), where n is length of string and m is length of regular expression
   * Justification: iterate over string and regex only once
   *
   * @param s string to be tested
   * @return true if only digits present, false otherwise
   */
  public boolean hasOnlyDigitsRegex(String s) {
    if (s.matches("\\d+")) {
      return true;
    } else {
      return false;
    }
  }
}
