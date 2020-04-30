package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-35e7202acb824fca85c9a01b8e5c9991
 */
public class OddEven {

  //O(1) : constant time since the check only happens once

  /**
   * Big-O : O(1)
   * Justification : Arithmetic operation
   *
   * @param number input number
   * @return "odd" if odd or "even" if even
   */
  public String isOddEvenUsingModulo(int number) {
    if (number % 2 == 0) {
      return "even";
    } else {
      return "odd";
    }
  }

  /**
   * Big-O : O(1)
   * Justification : Always constant number of bits operated on
   *
   * @param number input number
   * @return "odd" if odd or "even" if even
   */
  public String isOddEvenUsingBitOperator(int number) {
    if ((number & 1) == 0) {
      return "even";
    } else {
      return "odd";
    }
  }
}
