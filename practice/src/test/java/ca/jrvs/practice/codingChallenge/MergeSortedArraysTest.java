package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MergeSortedArraysTest {

  @Test
  public void merge() {
    MergeSortedArrays msa = new MergeSortedArrays();
    assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6},
        msa.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3));
  }
}