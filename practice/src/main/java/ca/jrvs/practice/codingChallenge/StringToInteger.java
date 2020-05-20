package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/String-to-Integer-atoi-fa289e28d11e479fa5f7ff703be0fe9a
 */
public class StringToInteger {

  /**
   * Big-O: O(n) where n is the length of the string
   * Justification: All built-in methods used have
   * O(n) and no loops are nested
   *
   * @param str string to be processed
   * @return transformed integer
   */
  public int atoiWithJavaBuiltInParse(String str) {
    str = str.trim();
    if (str.length() == 0) {
      return 0;
    }
    int i = 0;
    if (str.charAt(i) == 43 || str.charAt(i) == 45 || Character.isDigit(str.charAt(i))) {
      for (i = 1; i < str.length(); i++) {
        if (!(str.charAt(i) >= 48 && (str.charAt(i) <= 57))) {
          break;
        }
      }
      str = str.substring(0, i);
      try {
        if (str.length() == 1 && !Character.isDigit(str.charAt(0))) {
          return 0;
        } else {
          return Integer.parseInt(str);
        }
      } catch (NumberFormatException e) {
        if (str.charAt(0) == '-') {
          return Integer.MIN_VALUE;
        } else {
          return Integer.MAX_VALUE;
        }
      }
    }
    return 0;
  }

  /**
   * Big-O: O(n) where n is the length of the string
   * Justification: No loops are nested
   *
   * @param str string to be processed
   * @return transformed integer
   */
  public int atoiWithoutJavaBuiltInParse(String str) {
    if (str.length() == 0) {
      return 0;
    }
    int start;
    int result = 0;
    boolean negative = false;
    char[] strArray = str.toCharArray();
    int i = 0;
    while (i < strArray.length && strArray[i] == ' ') {
      i++;
    }
    try {
      if (i < strArray.length && (strArray[i] == 43 || strArray[i] == 45 || Character
          .isDigit(strArray[i]))) {
        start = i;
        for (i = i + 1; i < strArray.length; i++) {
          if (!Character.isDigit(strArray[i])) {
            break;
          }
        }
        if (strArray[start] == '-') {
          negative = true;
          start++;
        } else if (strArray[start] == '+') {
          start++;
        }
        for (int j = start; j < i; j++) {
          if (j != start) {
            result = Math.multiplyExact(result, 10);
          }
          result = Math.addExact(result, ((int) strArray[j] - (int) '0'));
        }
      }
      if (negative) {
        result = Math.multiplyExact(result, -1);
        ;
      }
    } catch (Exception e) {
      if (negative) {
        return Integer.MIN_VALUE;
      } else {
        return Integer.MAX_VALUE;
      }
    }
    return result;
  }
}