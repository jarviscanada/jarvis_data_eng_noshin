package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OnlyDigitsInStringTest {

  OnlyDigitsInString test = new OnlyDigitsInString();

  @Test
  public void testHasOnlyDigitsASCII() {
    String s = "1234";
    assertTrue(test.hasOnlyDigitsASCII(s));
    s = "125,000";
    assertFalse(test.hasOnlyDigitsASCII(s));
    s = "abc123";
    assertFalse(test.hasOnlyDigitsASCII(s));
    s = "";
    assertFalse(test.hasOnlyDigitsASCII(s));
    s = "abc";
    assertFalse(test.hasOnlyDigitsASCII(s));
  }

  @Test
  public void testHasOnlyDigitsAPI() {
    String s = "1234";
    assertTrue(test.hasOnlyDigitsAPI(s));
    s = "125,000";
    assertFalse(test.hasOnlyDigitsAPI(s));
    s = "abc123";
    assertFalse(test.hasOnlyDigitsAPI(s));
    s = "";
    assertFalse(test.hasOnlyDigitsAPI(s));
    s = "abc";
    assertFalse(test.hasOnlyDigitsAPI(s));
  }

  @Test
  public void testHasOnlyDigitsRegex() {
    String s = "1234";
    assertEquals(true, test.hasOnlyDigitsRegex(s));
    s = "125,000";
    assertFalse(test.hasOnlyDigitsRegex(s));
    s = "abc123";
    assertFalse(test.hasOnlyDigitsRegex(s));
    s = "";
    assertFalse(test.hasOnlyDigitsRegex(s));
    s = "abc";
    assertFalse(test.hasOnlyDigitsRegex(s));
  }
}