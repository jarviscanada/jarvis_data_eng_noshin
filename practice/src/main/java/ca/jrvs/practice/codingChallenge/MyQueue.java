package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class MyQueue {

  Stack<Integer> s1, s2;

  /**
   * Initialize your data structure here.
   */
  public MyQueue() {
    s1 = new Stack<>();
    s2 = new Stack<>();
  }

  /**
   * Big-O: O(1)
   * Justification: Just add the top of the stack
   *
   * Push element x to the back of queue.
   */
  public void push(int x) {
    s1.push(x);
  }

  /**
   * Big-O: O(n), where n is the size of stack
   * Justification: O(n) to remove from 1 stack and O(n) to remove from another
   *
   * Removes the element from in front of queue and returns that element.
   */
  public int pop() {
    int top = -1;
    while (!s1.isEmpty()) {
      top = s1.pop();
      if (!s1.isEmpty()) {
        s2.push(top);
      }
    }
    while (!s2.isEmpty()) {
      s1.push(s2.pop());
    }
    return top;
  }

  /**
   * Big-O: O(n), where n is the size of stack
   * Justification: O(n) to remove from 1 stack and O(n) to remove from another
   *
   * Get the front element.
   */
  public int peek() {
    int top = -1;
    while (!s1.isEmpty()) {
      top = s1.pop();
      s2.push(top);
    }
    while (!s2.isEmpty()) {
      s1.push(s2.pop());
    }
    return top;
  }

  /**
   * Big-O: O(1)
   * Justification: Just return the size
   *
   * Returns whether the queue is empty.
   */
  public boolean empty() {
    return s1.isEmpty();
  }

}
