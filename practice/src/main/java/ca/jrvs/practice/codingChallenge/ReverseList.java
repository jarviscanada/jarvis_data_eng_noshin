package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Reverse-Linked-List-a8fbc0c9ad04436cb91cdd6ce0d2503c
 */
public class ReverseList {

  /**
   * Big-O: O(n)
   * Justification: traverses the list once
   *
   * @param head list to be reversed
   * @return reversed list
   */
  public ListNode reverseListIterative(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = null;
    ListNode n = head;
    while (n != null) {
      ListNode temp = n.next;
      n.next = newHead;
      newHead = n;
      n = temp;
    }
    return newHead;
  }

  /**
   * Big-O: O(n)
   * Justification: traverses the list once
   *
   * @param head list to be reversed
   * @return reversed list
   */
  public ListNode reverseListRecursive(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = reverseListRecursive(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
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
