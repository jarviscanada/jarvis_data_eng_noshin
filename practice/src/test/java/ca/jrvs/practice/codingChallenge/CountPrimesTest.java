package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountPrimesTest {

  @Test
  public void count() {
    CountPrimes cp = new CountPrimes();
    assertEquals(4,cp.count(10));
    assertEquals(5,cp.count(12));
  }
}