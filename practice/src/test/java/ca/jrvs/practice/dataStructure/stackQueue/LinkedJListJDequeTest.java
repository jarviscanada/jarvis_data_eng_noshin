package ca.jrvs.practice.dataStructure.stackQueue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;

public class LinkedJListJDequeTest {

  LinkedJListJDeque<Integer> deque = new LinkedJListJDeque<>(3);

  @Test
  public void remove() {
    System.out.println("Testing Queue remove (removes from beginning of list)");

    deque.add(1);
    deque.add(2);
    deque.add(3);

    assertEquals(java.util.Optional.of(1), java.util.Optional.ofNullable(deque.remove()));
    assertEquals(2, deque.size());
    Integer[] expected = {2, 3};
    assertArrayEquals(expected, deque.toArray());
  }

  @Test
  public void pop() {
    System.out.println("Testing Stack pop (pops from beginning of list)");

    deque.add(1);
    deque.add(2);
    deque.add(3);

    assertEquals(java.util.Optional.of(1), java.util.Optional.ofNullable(deque.pop()));
    assertEquals(2, deque.size());
    Integer[] expected = {2, 3};
    assertArrayEquals(expected, deque.toArray());
  }

  @Test
  public void push() {
    System.out.println("Testing Stack push (pushes to beginning of list)");

    deque.push(1);
    deque.push(2);
    deque.push(3);

    assertEquals(3, deque.size());
    Integer[] expected = {3, 2, 1};
    assertArrayEquals(expected, deque.toArray());
    assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(deque.pop()));
  }

  @Test
  public void peek() {
    System.out.println("Testing Stack/Queue peek (peeks from beginning of list)");

    deque.push(1);
    deque.push(2);
    deque.push(3);

    assertEquals(java.util.Optional.of(3), Optional.ofNullable(deque.peek()));
  }
}