package ca.jrvs.practice.search;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void binarySearchRecursion() {
    BinarySearch binarySearch = new BinarySearch();
    Integer[] array = {-13, -2, -2, 0, 4, 6, 8, 10, 13, 13, 20};

    Optional<Integer> index = binarySearch.binarySearchRecursion(array, -13);
    assertEquals(Optional.of(0), index);

    index = binarySearch.binarySearchRecursion(array, 20);
    assertEquals(Optional.of(10), index);

    index = binarySearch.binarySearchRecursion(array, 6);
    assertEquals(Optional.of(5), index);

    index = binarySearch.binarySearchRecursion(array, 3);
    assertEquals(Optional.empty(), index);
  }

  @Test
  public void binarySearchIteration() {
    BinarySearch binarySearch = new BinarySearch();
    Integer[] array = {-13, -2, -2, 0, 4, 6, 8, 10, 13, 13, 20};

    Optional<Integer> index = binarySearch.binarySearchIteration(array, -13);
    assertEquals(Optional.of(0), index);

    index = binarySearch.binarySearchIteration(array, 20);
    assertEquals(Optional.of(10), index);

    index = binarySearch.binarySearchIteration(array, 6);
    assertEquals(Optional.of(5), index);

    index = binarySearch.binarySearchIteration(array, 3);
    assertEquals(Optional.empty(), index);
  }
}