package ca.jrvs.practice.codingChallenge;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Ticket URL: https://www.notion.so/Duplicate-Characters-f55d9e3e78384697b4de63a546531c23
 */
public class DuplicateCharacters {

  /**
   * Big-O: O(n), where n is length of string
   * Justification: iterate over string once
   *
   * @param s input string
   * @return array of duplicate characters
   */
  public String[] find (String s){
    List<String> duplicates = new LinkedList<>();
    HashSet<Character> set = new HashSet<>();
    for(int i=0; i<s.length(); i++){
      if(s.charAt(i)!=' ') {
        if (set.contains(s.charAt(i))) {
          duplicates.add(Character.toString(s.charAt(i)));
        } else {
          set.add(s.charAt(i));
        }
      }
    }
    String[] duplicateArray = new String[duplicates.size()];
    return duplicates.toArray(duplicateArray);
  }
}
