package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class MyQueue2 {

  Stack<Integer> s1, s2;

  /**
   * Initialize your data structure here.
   */
  public MyQueue2() {
    s1 = new Stack<>();
    s2 = new Stack<>();
  }

  /**
   * Big-O: O(n), where n is size of queue
   * Justification: move items from one stack to other twice
   *
   * Push element x to the back of queue.
   */
  public void push(int x) {
    while (!s1.isEmpty()) {
      s2.push(s1.pop());
    }
    s1.push(x);
    while (!s2.isEmpty()) {
      s1.push(s2.pop());
    }
  }

  /**
   * Big-O: O(1)
   * Justification: Just pop from stack
   *
   * Removes the element from in front of queue and returns that element.
   */
  public int pop() {
    return s1.pop();
  }

  /**
   * Big-O: O(1)
   * Justification: Just peek from stack
   *
   * Get the front element.
   */
  public int peek() {
    return s1.peek();
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
