package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveDuplicatesTest {

  @Test
  public void removeDuplicates() {
    RemoveDuplicates rd = new RemoveDuplicates();
    assertEquals(2, rd.removeDuplicates(new int[]{1, 1, 2}));
    assertEquals(5, rd.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
  }
}