package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringToIntegerTest {

  @Test
  public void testAtoiWithJavaBuiltInParse() {
    StringToInteger stringToInteger = new StringToInteger();

    assertEquals(0, stringToInteger.atoiWithJavaBuiltInParse(""));
    assertEquals(0, stringToInteger.atoiWithJavaBuiltInParse(" "));
    assertEquals(0, stringToInteger.atoiWithJavaBuiltInParse("- "));
    assertEquals(0, stringToInteger.atoiWithJavaBuiltInParse("+ "));
    assertEquals(0, stringToInteger.atoiWithJavaBuiltInParse("abc"));
    assertEquals(43, stringToInteger.atoiWithJavaBuiltInParse("43  "));
    assertEquals(-1, stringToInteger.atoiWithJavaBuiltInParse(" -1  "));
    assertEquals(2147483647, stringToInteger.atoiWithJavaBuiltInParse("434343434343434343"));
    assertEquals(2147483647, stringToInteger.atoiWithJavaBuiltInParse("2147483648"));
  }

  @Test
  public void testAtoiWithoutJavaBuiltInParse() {
    StringToInteger stringToInteger = new StringToInteger();

    assertEquals(0, stringToInteger.atoiWithoutJavaBuiltInParse(""));
    assertEquals(0, stringToInteger.atoiWithoutJavaBuiltInParse(" "));
    assertEquals(0, stringToInteger.atoiWithoutJavaBuiltInParse("- "));
    assertEquals(0, stringToInteger.atoiWithoutJavaBuiltInParse("+ "));
    assertEquals(0, stringToInteger.atoiWithoutJavaBuiltInParse("abc"));
    assertEquals(43, stringToInteger.atoiWithoutJavaBuiltInParse("43  "));
    assertEquals(-1, stringToInteger.atoiWithoutJavaBuiltInParse(" -1  "));
    assertEquals(2147483647, stringToInteger.atoiWithoutJavaBuiltInParse("434343434343434343"));
    assertEquals(2147483647, stringToInteger.atoiWithoutJavaBuiltInParse("2147483648"));
  }
}