package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import ca.jrvs.practice.codingChallenge.ReverseList.ListNode;
import org.junit.Before;
import org.junit.Test;

public class ReverseListTest {

  ReverseList rl = new ReverseList();
  ListNode head = new ListNode(1, null);
  ListNode n = head;

  @Before
  public void setup() {
    for (int i = 2; i <= 5; i++) {
      n.next = new ListNode(i, null);
      n = n.next;
    }
  }

  @Test
  public void testReverseListIterative() {
    ListNode reversedList = rl.reverseListIterative(head);
    n = reversedList;
    for (int i = 5; i > 0; i--) {
      assertEquals(i, n.val);
      n = n.next;
    }
  }

  @Test
  public void testReverseListRecursive() {
    ListNode reversedList = rl.reverseListRecursive(head);
    n = reversedList;
    for (int i = 5; i > 0; i--) {
      assertEquals(i, n.val);
      n = n.next;
    }
  }
}