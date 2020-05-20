package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Print-letter-with-number-e5ba176f9a994af182c64a1291332464
 */
public class LetterWithNumber {

  /**
   * Big-O: O(n^2), where n is length of array
   * Justification: for loop - O(n), Stringbuilder's append() - O(n). Complexity = O(n(n+n))
   *
   * @param s
   * @return
   */
  public String transform(String s) {
    char[] array = s.toCharArray();
    StringBuilder newString = new StringBuilder("");
    for (int i = 0; i < array.length; i++) {
      newString.append(array[i]);
      if (array[i] >= 'a' && array[i] <= 'z') {
        newString.append((int) array[i] - 'a' + 1);
      } else {
        newString.append((int) array[i] - 'A' + 27);
      }
    }
    return newString.toString();
  }
}
