package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidAnagramTest {

  @Test
  public void testIsAnagramWithSorting() {
    ValidAnagram validAnagram = new ValidAnagram();

    assertEquals(true, validAnagram.isAnagramWithSorting("", ""));
    assertEquals(false, validAnagram.isAnagramWithSorting("", " "));
    assertEquals(false, validAnagram.isAnagramWithSorting("", "a"));
    assertEquals(true, validAnagram.isAnagramWithSorting("a", "a"));
    assertEquals(false, validAnagram.isAnagramWithSorting("a", "b"));
    assertEquals(false, validAnagram.isAnagramWithSorting("a", "ab"));
    assertEquals(false, validAnagram.isAnagramWithSorting("ab", "b"));
    assertEquals(true, validAnagram.isAnagramWithSorting("anagram", "nagaram"));
    assertEquals(false, validAnagram.isAnagramWithSorting("rat", "cat"));
    assertEquals(false, validAnagram.isAnagramWithSorting("anagram", "anagrams"));
  }

  @Test
  public void testIsAnagramWithoutSorting() {
    ValidAnagram validAnagram = new ValidAnagram();

    assertEquals(true, validAnagram.isAnagramWithoutSorting("", ""));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("", " "));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("", "a"));
    assertEquals(true, validAnagram.isAnagramWithoutSorting("a", "a"));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("a", "b"));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("a", "ab"));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("ab", "b"));
    assertEquals(true, validAnagram.isAnagramWithoutSorting("anagram", "nagaram"));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("rat", "cat"));
    assertEquals(false, validAnagram.isAnagramWithoutSorting("anagram", "anagrams"));
  }
}