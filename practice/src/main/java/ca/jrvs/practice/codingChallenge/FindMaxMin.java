package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Ticket URL: https://www.notion.so/Find-Largest-Smallest-d140233153864ea789cbdcf9a6cadc53
 */
public class FindMaxMin {

  /**
   * Big-O: O(n), where n is length of array
   * Justification: Iterate over array only once
   *
   * @param array input array
   * @return array where index 0 will have max number, index 1 will have min number
   */
  public int[] findOneLoop(int[] array) {
    int[] maxMin = new int[2]; //index 0 will have max number, index 1 will have min number
    maxMin[0] = array[0];
    maxMin[1] = array[0];
    for (int i = 1; i < array.length; i++) {
      if (maxMin[0] < array[i]) {
        maxMin[0] = array[i];
      }
      if (maxMin[1] > array[i]) {
        maxMin[1] = array[i];
      }
    }
    return maxMin;
  }

  /**
   * Big-O: O(n), where n is length of array
   * Justification: Iterate over array only once
   *
   * @param array input array
   * @return array where index 0 will have max number, index 1 will have min number
   */
  public int[] findStreamAPI(int[] array) {
    int[] maxMin = new int[2]; //index 0 will have max number, index 1 will have min number
    maxMin[0] = Arrays.stream(array).max().getAsInt();
    maxMin[1] = Arrays.stream(array).min().getAsInt();
    return maxMin;
  }

  /**
   * Big-O: O(n), where n is length of array
   * Justification: Iterate over array only once
   *
   * @param list input list
   * @return array where index 0 will have max number, index 1 will have min number
   */
  public int[] findJavaAPI(List<Integer> list) {
    int[] maxMin = new int[2]; //index 0 will have max number, index 1 will have min number
    maxMin[0] = Collections.max(list);
    maxMin[1] = Collections.min(list);
    return maxMin;
  }
}
