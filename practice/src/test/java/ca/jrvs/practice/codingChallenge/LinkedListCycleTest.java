package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.practice.codingChallenge.LinkedListCycle.ListNode;
import org.junit.Test;

public class LinkedListCycleTest {

  @Test
  public void testHasCycle() {
    LinkedListCycle linkedListCycle = new LinkedListCycle();

    ListNode head = null;
    assertFalse(linkedListCycle.hasCycle(head));

    head = new ListNode(1);
    assertFalse(linkedListCycle.hasCycle(head));

    ListNode n = head;
    n.next = new ListNode(2);
    assertFalse(linkedListCycle.hasCycle(head));

    n = n.next;
    n.next = head;
    assertTrue(linkedListCycle.hasCycle(head));

    n.next = new ListNode(3);
    assertFalse(linkedListCycle.hasCycle(head));

    n = n.next;
    n.next = new ListNode(4);
    assertFalse(linkedListCycle.hasCycle(head));

    n = n.next;
    n.next = head.next;
    assertTrue(linkedListCycle.hasCycle(head));
  }
}