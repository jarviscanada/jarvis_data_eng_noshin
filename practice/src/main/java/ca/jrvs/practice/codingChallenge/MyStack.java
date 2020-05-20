package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

  Queue<Integer> q1;
  Queue<Integer> q2;

  /**
   * Initialize your data structure here.
   */
  public MyStack() {
    q1 = new LinkedList<>();
    q2 = new LinkedList<>();
  }

  /**
   * Big-O: O(1)
   * Justification: only involves adding the element at last available index
   *
   * Push element x onto stack.
   */
  public void push(int x) {
    q1.add(x);
  }

  /**
   * Big-O: O(n), where n is the number of elements in stack
   * Justification: Remove all the elements until the last one is reached
   *
   * Removes the element on top of the stack and returns that element
   */
  public int pop() {
    int top = -1;
    while (!q1.isEmpty()) {
      top = q1.remove();
      if (!q1.isEmpty()) {
        q2.add(top);
      }
    }
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
    return top;

  }

  /**
   * Big-O: O(n), where n is the number of elements in stack
   * Justification: Remove all the elements until the last one is reached
   *
   * Get the top element.
   */
  public int top() {
    int top = -1;
    while (!q1.isEmpty()) {
      top = q1.remove();
      q2.add(top);
    }
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
    return top;
  }

  /**
   * Big-O: O(1)
   * Justification: Checks the size and returns
   *
   * Returns whether the stack is empty.
   */
  public boolean empty() {
    return q1.isEmpty();
  }


}



