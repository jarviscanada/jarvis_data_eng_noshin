package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Duplicates-from-Sorted-Array-7108a28276b04ce18dfaadca6d354e48
 */
public class RemoveDuplicates {

  /**
   * Big-O: O(n), where n is length of array
   * Justification: iterate over array only once
   *
   * @param nums input array
   * @return length of new array
   */
  public int removeDuplicates(int[] nums) {
    int nextAvailableSpace = 0;
    int iterator = 1;
    while (iterator < nums.length) {
      if (nums[iterator] == nums[nextAvailableSpace]) {
        iterator++;
      } else {
        nextAvailableSpace++;
        nums[nextAvailableSpace] = nums[iterator];
      }
    }
    return ++nextAvailableSpace;
  }
}
