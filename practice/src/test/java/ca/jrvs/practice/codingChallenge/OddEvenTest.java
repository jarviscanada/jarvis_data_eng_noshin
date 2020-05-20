package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OddEvenTest {

  @Test
  public void testIsOddEvenUsingModulo() {
    OddEven oddEven = new OddEven();
    assertEquals("even", oddEven.isOddEvenUsingModulo(-12));
    assertEquals("odd", oddEven.isOddEvenUsingModulo(-5));
    assertEquals("even", oddEven.isOddEvenUsingModulo(0));
    assertEquals("odd", oddEven.isOddEvenUsingModulo(5));
    assertEquals("even", oddEven.isOddEvenUsingModulo(12));
  }

  @Test
  public void testIsOddEvenUsingBitOperator() {
    OddEven oddEven = new OddEven();
    assertEquals("even", oddEven.isOddEvenUsingModulo(-12));
    assertEquals("odd", oddEven.isOddEvenUsingModulo(-5));
    assertEquals("even", oddEven.isOddEvenUsingModulo(0));
    assertEquals("odd", oddEven.isOddEvenUsingModulo(5));
    assertEquals("even", oddEven.isOddEvenUsingModulo(12));
  }
}