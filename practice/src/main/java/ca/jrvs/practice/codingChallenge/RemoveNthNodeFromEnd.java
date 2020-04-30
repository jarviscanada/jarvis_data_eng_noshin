package ca.jrvs.practice.codingChallenge;

public class RemoveNthNodeFromEnd {

  /**
   * Big-O: O(n), where n is length of list
   * Justification: the whole list is traversed only once
   *
   * @param head head of the list to be processed
   * @param n    distance from end of list
   * @return new list with nth node from end of list removed
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }
    ListNode toDelete = head;
    ListNode tail = head;
    ListNode parentOfToDelete = null;
    int distance = 1;
    while (distance < n) {
      tail = tail.next;
      distance++;
    }
    while (tail.next != null) {
      parentOfToDelete = toDelete;
      toDelete = toDelete.next;
      tail = tail.next;
    }
    if (toDelete == head) {
      head = toDelete.next;
    } else {
      parentOfToDelete.next = toDelete.next;
    }
    return head;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
