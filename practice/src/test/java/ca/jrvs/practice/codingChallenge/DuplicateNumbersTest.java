package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DuplicateNumbersTest {

  DuplicateNumbers dn = new DuplicateNumbers();
  int[] array1 = {1, 3, 4, 2, 2};
  int[] array2 = {3, 1, 3, 4, 2};

  @Test
  public void testFindSort() {
    assertEquals(2, dn.findSort(array1));
    assertEquals(3, dn.findSort(array2));
  }

  @Test
  public void testFindSet() {
    assertEquals(2, dn.findSet(array1));
    assertEquals(3, dn.findSet(array2));
  }
}