package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyQueue2Test {

  MyQueue2 queue = new MyQueue2();

  @Test
  public void testPush() {
    queue.push(1);
    assertEquals(1, queue.peek());
    queue.push(2);
    assertEquals(1, queue.peek());
  }

  @Test
  public void testPop() {
    queue.push(1);
    queue.push(2);
    assertEquals(1, queue.pop());
  }

  @Test
  public void testTop() {
    queue.push(1);
    queue.push(2);
    queue.pop();
    assertEquals(2, queue.peek());
  }

  @Test
  public void testEmpty() {
    assertEquals(true, queue.empty());
    queue.push(5);
    assertEquals(false, queue.empty());
  }
}