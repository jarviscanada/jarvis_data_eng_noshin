package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPalindromeTest {

  @Test
  public void testIsPalindromeRegular() {
    ValidPalindrome vp = new ValidPalindrome();
    assertTrue(vp.isPalindromeRegular("A man, a plan, a canal: Panama"));
    assertFalse(vp.isPalindromeRegular("race a car"));
  }

  @Test
  public void isPalindromeRecursion() {
    ValidPalindrome vp = new ValidPalindrome();
    assertTrue(vp.isPalindromeRecursion("A man, a plan, a canal: Panama"));
    assertFalse(vp.isPalindromeRecursion("race a car"));
  }
}