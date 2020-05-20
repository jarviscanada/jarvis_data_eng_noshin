package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Remove-Element-5bcfe460ae3a4024847398d98c61bc7e
 */
public class RemoveElement {

  /**
   * Big-O: O(n), where n is length of array
   * Justification: iterate over array only once
   *
   * @param nums input array
   * @param val  value to be deleted
   * @return new length of array
   */
  public int removeElement(int[] nums, int val) {
    int nextAvailableSpace = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[nextAvailableSpace] = nums[i];
        nextAvailableSpace++;
      }
    }
    return nextAvailableSpace;
  }
}
