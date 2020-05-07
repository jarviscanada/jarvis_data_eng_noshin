package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TwoSumTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testTwoSumBruteForce() {
    TwoSum twoSum = new TwoSum();
    int[] array = {9, 2, 7, 11};
    int[] anotherArray = {2, 5, 5, 11};

    assertArrayEquals(new int[]{1, 2}, twoSum.twoSumBruteForce(array, 9));
    assertArrayEquals(new int[]{1, 2}, twoSum.twoSumBruteForce(anotherArray, 10));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("No solution found");
    twoSum.twoSumBruteForce(array, 3);
  }

  @Test
  public void testTwoSumSorted() {
    TwoSum twoSum = new TwoSum();
    int[] array = {9, 2, 7, 11};
    int[] anotherArray = {2, 5, 5, 11};

    assertArrayEquals(new int[]{1, 2}, twoSum.twoSumSorted(array, 9));
    assertArrayEquals(new int[]{2, 3}, twoSum.twoSumSorted(anotherArray, 10));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("No solution found");
    twoSum.twoSumBruteForce(array, 3);
  }

  @Test
  public void testTwoSumWithHashMap() {
    TwoSum twoSum = new TwoSum();
    int[] array = {9, 2, 7, 11};
    int[] anotherArray = {2, 5, 5, 11};

    assertArrayEquals(new int[]{1, 2}, twoSum.twoSumWithHashMap(array, 9));
    assertArrayEquals(new int[]{1, 2}, twoSum.twoSumWithHashMap(anotherArray, 10));

    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("No solution found");
    twoSum.twoSumBruteForce(array, 3);
  }
}