package ca.jrvs.practice.sorting;

import java.util.Arrays;

public class QuickSort {


  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort();
    Integer[] array = {-1, 0, 1, 0, 2, 100, 8, 9, 13, -7, -7, -2, 12};
    System.out.println(Arrays.toString(array));
    quickSort.quickSort(array, 0, 12);
    System.out.println(Arrays.toString(array));
  }

  /**
   * sorts given array by partitioning the array into two around a pivot and calls itself
   * recursively on the partitioned arrays
   *
   * @param arr   input array
   * @param start start index of array
   * @param end   end index of array
   * @param <E>
   */
  public <E extends Comparable<E>> void quickSort(E arr[], int start, int end) {
    if (start < end) {
      int pivot = partition(arr, start, end);

      quickSort(arr, start, pivot - 1);
      quickSort(arr, pivot + 1, end);
    }
  }

  /**
   * partitions the array around the pivot in such a way that elements on the left of the pivot are
   * smaller than the pivot while those on the right are larger than pivot
   *
   * @param arr   input array
   * @param start start index of array
   * @param end   end index of array
   * @param <E>
   * @return
   */
  private <E extends Comparable<E>> int partition(E arr[], int start, int end) {
    int pivot = randomizedPivot(arr, start, end);
    int i = start;

    for (int j = start; j < end; j++) {
      if (arr[j].compareTo(arr[pivot]) < 0) {
        swap(arr, i, j);
        i++;
      }
    }
    swap(arr, i, end);

    return i;
  }

  /**
   * Generates randomized pivot to avoid worst case scenario : sorted (in descending order) input
   * array. Swaps the pivot value with the end value.
   *
   * @param arr   input array
   * @param start index of array partition
   * @param end   index of array partition
   * @param <E>
   * @return end index
   */
  private <E extends Comparable<E>> int randomizedPivot(E[] arr, int start, int end) {
    int pivot = (int) Math.random() * end + start;
    swap(arr, pivot, end);

    return end;
  }

  /**
   * Utility method to swap elements
   *
   * @param arr  input array
   * @param from index from where to swap
   * @param to   index to where to swap
   * @param <E>
   */
  private <E extends Comparable<E>> void swap(E arr[], int from, int to) {
    E temp = arr[from];
    arr[from] = arr[to];
    arr[to] = temp;
  }

}