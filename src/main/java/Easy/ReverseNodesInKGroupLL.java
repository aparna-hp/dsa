package Easy;

public class ReverseNodesInKGroupLL {

    //https://leetcode.com/problems/reverse-nodes-in-k-group/

    //Definition for singly-linked list.
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

        public static void printLL(ListNode head){
            ListNode curr = head;
            System.out.println();
            while(null != curr){
                System.out.print(curr.val + "->");
                curr = curr.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ReverseNodesInKGroupLL reverseNodesInKGroupLL = new ReverseNodesInKGroupLL();
        ListNode.printLL(reverseNodesInKGroupLL.reverseKGroup(node, 2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode curr = head;

        int index=k;
        while (index > 0 && null != curr) {
            curr = curr.next;
            index--;
        }

        if (index != 0) {
            return head;
        }

        curr = head;
        ListNode prev = null;
        ListNode prevLast = curr;

        for (int i = k ; i > 0; i--) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        prevLast.next = reverseKGroup(curr, k);
        return prev;
    }
}
