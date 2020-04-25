package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {

  /**
   * find the the target index in a sorted array
   *
   * @param arr    input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    int start = 0;
    int end = arr.length - 1;

    return binarySearch(arr, target, start, end);
  }

  /**
   * recursively calls itself on any half of its input array based on comparison with target value
   *
   * @param arr    input sorted array
   * @param target value to be searched
   * @param start  of range
   * @param end    of range
   * @param <E>
   * @return target index or optional
   */
  private <E extends Comparable<E>> Optional<Integer> binarySearch(E[] arr, E target, int start,
      int end) {
    if (start <= end) {
      int mid = (start + end) / 2;

      if (arr[mid].compareTo(target) == 0) {
        return Optional.of(mid);
      } else if (arr[mid].compareTo(target) < 0) {
        return binarySearch(arr, target, mid + 1, end);
      } else {
        return binarySearch(arr, target, start, mid - 1);
      }
    } else {
      return Optional.empty();
    }
  }


  /**
   * find the the target index in a sorted array
   *
   * @param arr    input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = (start + end) / 2;
      if (arr[mid].compareTo(target) == 0) {
        return Optional.of(mid);
      } else if (arr[mid].compareTo(target) < 0) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return Optional.empty();
  }
}