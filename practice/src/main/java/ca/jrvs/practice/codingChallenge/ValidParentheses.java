package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

  /**
   * Big-O: O(n), where n is length of string
   * Justification: Iterate through the string to check the parentheses
   *
   * @param s input string
   * @return true if valid, false otherwise
   */
  public boolean isValid(String s) {
    HashMap<Character, Character> parenthesesPairs = new HashMap<>();
    parenthesesPairs.put(')', '(');
    parenthesesPairs.put('}', '{');
    parenthesesPairs.put(']', '[');

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      if (!stack.isEmpty() && stack.peek() == parenthesesPairs.get(s.charAt(i))) {
        stack.pop();
      } else {
        stack.push(s.charAt(i));
      }
    }
    if (stack.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
}
