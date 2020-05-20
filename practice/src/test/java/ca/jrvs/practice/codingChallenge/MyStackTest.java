package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyStackTest {

  MyStack stack = new MyStack();

  @Test
  public void testPush() {
    stack.push(1);
    assertEquals(1, stack.top());
    stack.push(2);
    assertEquals(2, stack.top());
  }

  @Test
  public void testPop() {
    stack.push(1);
    stack.push(2);
    assertEquals(2, stack.pop());
  }

  @Test
  public void testTop() {
    stack.push(1);
    stack.push(2);
    stack.pop();
    assertEquals(1, stack.top());
  }

  @Test
  public void testEmpty() {
    assertEquals(true, stack.empty());
    stack.push(5);
    assertEquals(false, stack.empty());
  }
}