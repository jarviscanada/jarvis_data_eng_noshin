package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;

/**
 * Ticket URL: https://www.notion.so/Missing-Number-b6b3d56a9ee64ef4bd1cf1a0e62d7b62
 */
public class MissingNumber {

  /**
   * Big-O: O(n), where n is length of array
   * Justification: iterate over array only once
   *
   * @param nums input array
   * @return missing number
   */
  public int findUsingSum(int[] nums) {
    int expectedSum = (nums.length * (nums.length + 1)) / 2;
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return expectedSum - sum;
  }

  /**
   * Big-O: O(n), where n is length of array
   * Justification: iterate over array only once per for
   * loop
   *
   * @param nums input array
   * @return missing number
   */
  public int findUsingSet(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    for (int i = 0; i <= nums.length; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }
    return -1;
  }
}
