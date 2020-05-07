package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveElementTest {

  @Test
  public void removeElement() {
    RemoveElement re = new RemoveElement();
    assertEquals(2, re.removeElement(new int[]{3, 2, 2, 3}, 3));
    assertEquals(5, re.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
  }
}