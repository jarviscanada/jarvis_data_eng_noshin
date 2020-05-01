package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Ticket URL: https://www.notion.so/Find-the-Duplicate-Number-76094058d7564518a08ce2990c835e52
 */
public class DuplicateNumbers {

  /**
   * Big-O: O(nlogn), where n is the length of array
   * Justification: sorting takes O(nlogn) and iterating over array takes O(n)
   *
   * @param nums input array
   * @return duplicate number
   */
  public int findSort(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        return nums[i];
      }
    }
    return -1;
  }

  /**
   * Big-O: O(n), where n is the length of array
   * Justification: array iterated only once
   *
   * @param nums input array
   * @return duplicate number
   */
  public int findSet(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return nums[i];
      } else {
        set.add(nums[i]);
      }
    }
    return -1;
  }

}
