package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapNumbersTest {

  SwapNumbers swapNumbers = new SwapNumbers();
  int[] array = {-2,3};

  @Test
  public void swapWithBitManipulation() {
    assertArrayEquals(new int[]{3,-2}, swapNumbers.swapWithBitManipulation(array));
  }

  @Test
  public void swapWithArithmeticOperator() {
    assertArrayEquals(new int[]{3,-2}, swapNumbers.swapWithArithmeticOperator(array));
  }
}