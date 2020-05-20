package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Middle-of-the-Linked-List-56742840087149bd8bfbf4d1fb760b48
 */
public class MiddleOfLinkedList {

  /**
   * Big-o: O(n), where is the number of nodes in list
   * Justification: traverses list only once
   *
   * @param head head of the linked list
   * @return middle node of the linked list. If even number of nodes, return the second middle one.
   */
  public ListNode middleNode(ListNode head) {
    if (head.next == null) {
      return head;
    } else {
      ListNode left = head;
      ListNode right = head;
      while (right != null && right.next != null) {
        left = left.next;
        right = right.next.next;
      }
      return left;
    }
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
