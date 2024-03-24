package medium;

public class SwapNodesInPairs {

     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

     public void printList(ListNode head) {
         System.out.println("The list entries ::");
         ListNode node = head;
         while(null != node) {
             System.out.print(node.val + "->");
             node = node.next;
         }
         System.out.println();
     }

     public static void main(String[] args) {
         SwapNodesInPairs swapNodesInPairs = new SwapNodesInPairs();
         ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
         swapNodesInPairs.printList(head);
         head = swapNodesInPairs.swapPairs(head);
         swapNodesInPairs.printList(head);
     }

        public ListNode swapPairs(ListNode head) {
            if(null == head || head.next == null){
                return head;
            }

            ListNode curr = head;
            ListNode newHead = null;
            ListNode prev = null;
            ListNode next = null;
            while(curr != null){
                int k=1;
                ListNode revCurr = curr;
                ListNode revPrev = null;
                next = curr.next;

                while(k > 0 && null != next){
                    revCurr.next = revPrev;
                    revPrev = revCurr;
                    revCurr = next;
                    next = next.next;
                    k--;
                }
                revCurr.next = revPrev;

                if(newHead == null ) {
                    newHead = revCurr;
                } else {
                    prev.next = revCurr;
                }
                prev = curr;
                curr = next;
            }

            return newHead;
        }

        public ListNode reverseList(ListNode head, ListNode next) {
            if(null == head || null == head.next) {
                return head;
            }
            int k=1;
            ListNode curr = head;
            ListNode prev = null;
            next = head.next;
            while(k > 0){
                curr.next = prev;
                prev = curr;
                curr = next;
                next = next.next;
                k--;
            }
            curr.next = prev;
            System.out.println("Next " + (null == next ? null : next.val));
            System.out.println("Reversed Curr " + curr.val);
            return curr;
        }
}
