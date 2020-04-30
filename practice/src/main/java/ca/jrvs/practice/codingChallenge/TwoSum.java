package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Ticket URL: https://www.notion.so/Two-Sum-8b8e24b291cb4f67aacc434940beb89f
 */
public class TwoSum {

  /**
   * Big-O: O(n^2), where n=length of array
   * Justification: nested for loop
   *
   * @param array  input array
   * @param target target sum
   * @return indices of two numbers in array with sum=target
   */
  public int[] twoSumBruteForce(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (target - array[i] == array[j]) {
          return new int[]{i, j};
        }
      }
    }
    throw new IllegalArgumentException("No solution found");
  }

  /**
   * Big-O: O(n*logn), where n=length of array
   * Justification: Arrays.sort is O(nlogn), iterating over for loop is O(n) and Arrays.binarySearch
   * is called n times which is O(logn). So, O(nlogn)+O(nlogn)=O(nlogn)
   *
   * @param array  input array
   * @param target target sum
   * @return indices of two numbers in array with sum=target
   */
  public int[] twoSumSorted(int[] array, int target) {
    Arrays.sort(array);
    for (int i = 0; i < array.length; i++) {
      int otherIndex = Arrays.binarySearch(array, i + 1, array.length, target - array[i]);
      if (otherIndex >= 0 && i != otherIndex) {
        return new int[]{i + 1, otherIndex + 1};
      }
    }
    throw new IllegalArgumentException("No solution found");
  }

  /**
   * Big-O: O(n), where n=length of array
   * Justification: Iterated over array twice with O(n) complexity. So, O(n)+O(n)=O(n)
   *
   * @param array  input array
   * @param target target sum
   * @return indices of two numbers in array with sum=target
   */
  public int[] twoSumWithHashMap(int[] array, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      map.put(target - array[i], i);
    }
    for (int i = 0; i < array.length; i++) {
      Integer otherIndex = map.get(array[i]);
      if (otherIndex != null && otherIndex != i) {
        return new int[]{i, map.get(array[i])};
      }
    }
    throw new IllegalArgumentException("No solution found");
  }
}
