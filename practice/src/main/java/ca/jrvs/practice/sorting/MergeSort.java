package ca.jrvs.practice.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

  /**
   * sorts provided array by recursively halving it and merging the halves by comparing them
   *
   * @param arrayinput array
   * @param start      start index of array
   * @param end        end index of array
   * @param <E>
   */
  public static <E extends Comparable<E>> void mergeSort(E[] array, int start, int end) {
    if (end - start == 0) {
      return;
    }
    int mid = (start + end) / 2;

    mergeSort(array, start, mid);
    mergeSort(array, mid + 1, end);

    merge(array, start, mid, end);
  }

  /**
   * compares and "merges" two parts of the array at the mid.
   *
   * @param array input array
   * @param start start index of the first half of array
   * @param mid   end index of the first half of array
   * @param end   end index of the last half of the array
   * @param <E>
   */
  private static <E extends Comparable<E>> void merge(E[] array, int start, int mid, int end) {
    List<E> left = new ArrayList<>();
    List<E> right = new ArrayList<>();

    left = Arrays.asList(Arrays.copyOfRange(array, start, mid + 1));
    right = Arrays.asList(Arrays.copyOfRange(array, mid + 1, end + 1));

    int leftCursor = 0, rightCursor = 0, arrayCursor = start;

    while (leftCursor < left.size() && rightCursor < right.size()) {
      if (left.get(leftCursor).compareTo(right.get(rightCursor)) <= 0) {
        array[arrayCursor++] = left.get(leftCursor++);
      } else {
        array[arrayCursor++] = right.get(rightCursor++);
      }
    }
    while (leftCursor < left.size()) {
      array[arrayCursor++] = left.get(leftCursor++);
    }
    while (rightCursor < right.size()) {
      array[arrayCursor++] = right.get(rightCursor++);
    }
  }

  public static void main(String[] args) {
    Integer[] array = {-1, 0, 1, 0, 2, 100, 8, 9, 13, -7, -7, -2, 12};
    System.out.println(Arrays.toString(array));
    mergeSort(array, 0, 12);
    System.out.println(Arrays.toString(array));
  }
}