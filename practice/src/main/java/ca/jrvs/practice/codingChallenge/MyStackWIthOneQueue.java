package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class MyStackWIthOneQueue {

  Queue<Integer> q1;

  /**
   * Initialize your data structure here.
   */
  public MyStackWIthOneQueue() {
    q1 = new LinkedList<>();
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
    for (int i = 0; i < q1.size(); i++) {
      if (i != q1.size() - 1) {
        top = q1.remove();
        q1.add(top);
      } else {
        top = q1.remove();
      }
    }
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
    for (int i = 0; i < q1.size(); i++) {
      top = q1.remove();
      q1.add(top);
    }
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
