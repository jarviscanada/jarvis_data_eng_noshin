package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Merge-Sorted-Array-44fcde623d054bee9fc24c7c9c9ef48c
 */
public class MergeSortedArrays {

  /**
   * Big-O: O(n), where n is length of nums1 array
   * Justification: only iterate over nums1 array
   *
   * @param nums1 first input array
   * @param m     number of elements in nums1
   * @param nums2 second input array
   * @param n     number of elements in nums2
   */
  public int[] merge(int[] nums1, int m, int[] nums2, int n) {
    m--;
    n--;
    for (int i = nums1.length - 1; i >= 0; i--) {
      if (m >= 0 && n >= 0) {
        if (nums1[m] > nums2[n]) {
          nums1[i] = nums1[m];
          m--;
        } else {
          nums1[i] = nums2[n];
          n--;
        }
      } else if (m < 0) {
        nums1[i] = nums2[n];
        n--;
      } else {
        nums1[i] = nums1[m];
        m--;
      }
    }
    return nums1;
  }
}
