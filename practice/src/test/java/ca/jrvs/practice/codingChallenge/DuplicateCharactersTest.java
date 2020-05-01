package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuplicateCharactersTest {

  @Test
  public void find() {
    DuplicateCharacters dp = new DuplicateCharacters();
    assertArrayEquals(new String[]{"c","a"}, dp.find("A black cat"));
  }
}