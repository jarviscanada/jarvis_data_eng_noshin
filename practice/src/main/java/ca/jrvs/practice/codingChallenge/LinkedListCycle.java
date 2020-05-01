package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/LinkedList-Cycle-9763d7f37c4e425184f6bd0ddff9771d
 */
public class LinkedListCycle {

  /**
   * Big-O: O(n), where n is the number of elements in the list
   * Justification: Iterates over the list once
   *
   * @param head head of linked list
   * @return true if cyclepresent, false otherwise
   */
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slowPointer = head;
    ListNode fastPointer = head.next;
    if (fastPointer.next != null) {
      fastPointer = fastPointer.next;
    }
    while (fastPointer != null && fastPointer.next != null) {
      if (slowPointer != fastPointer) {
        slowPointer = slowPointer.next;
        fastPointer = fastPointer.next.next;
      } else {
        return true;
      }
    }
    return false;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

}
