package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MissingNumberTest {

  MissingNumber mn = new MissingNumber();
  int[] array1 = {3, 0, 1};
  int[] array2 = {9, 7, 0, 1, 4, 2, 5, 3, 6};

  @Test
  public void testFindUsingSum() {
    assertEquals(2, mn.findUsingSum(array1));
    assertEquals(8, mn.findUsingSum(array2));
  }

  @Test
  public void testFindUsingSet() {
    assertEquals(2, mn.findUsingSet(array1));
    assertEquals(8, mn.findUsingSet(array2));
  }
}