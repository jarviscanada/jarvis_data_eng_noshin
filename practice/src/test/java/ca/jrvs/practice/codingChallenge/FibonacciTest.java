package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FibonacciTest {

  @Test
  public void testFibonacciWithRecursion() {
    Fibonacci fibonacci = new Fibonacci();

    assertEquals(0, fibonacci.fibonacciWithRecursion(0));
    assertEquals(1, fibonacci.fibonacciWithRecursion(1));
    assertEquals(1, fibonacci.fibonacciWithRecursion(2));
    assertEquals(5, fibonacci.fibonacciWithRecursion(5));
  }

  @Test
  public void testFibonacciWithDP() {
    Fibonacci fibonacci = new Fibonacci();

    assertEquals(0, fibonacci.fibonacciWithDP(0));
    assertEquals(1, fibonacci.fibonacciWithDP(1));
    assertEquals(1, fibonacci.fibonacciWithDP(2));
    assertEquals(5, fibonacci.fibonacciWithDP(5));
  }
}