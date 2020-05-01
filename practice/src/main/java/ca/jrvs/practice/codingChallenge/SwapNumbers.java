package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Swap-two-numbers-72db5b683a014489b74350f7140b0e7d
 */
public class SwapNumbers {

  /**
   * Big-O: O(1) Justification: constant number of operations
   *
   * @param array
   * @return
   */
  public int[] swapWithBitManipulation(int[] array) {
    array[0] = array[0] ^ array[1];
    array[1] = array[0] ^ array[1];
    array[0] = array[0] ^ array[1];
    return array;
  }

  /**
   * Big-O: O(1) Justification: constant number of operations
   *
   * @param array
   * @return
   */
  public int[] swapWithArithmeticOperator(int[] array) {
    array[0] = array[0] + array[1];
    array[1] = array[0] - array[1];
    array[0] = array[0] - array[1];
    return array;
  }
}
