package medium;

public class SwapNodesInLinkedList {

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
        SwapNodesInLinkedList swapNodesInLinkedList = new SwapNodesInLinkedList();
        ListNode head = new ListNode(7, new ListNode(9, new ListNode(6, new ListNode(6, new ListNode(7,
                new ListNode(8, new ListNode(3, new ListNode(0, new ListNode(9, new ListNode(2))))))))));
        head = swapNodesInLinkedList.swapNodes(head, 5);
        swapNodesInLinkedList.printList(head);
    }


    public ListNode swapNodes(ListNode head, int k) {
            if(null == head || null == head.next){
                return head;
            }

            ListNode front = head;
            ListNode prevFront = null;
            int temp = k;
            while(temp > 1 && front != null) {
                temp--;
                prevFront = front;
                front = front.next;
            }

            if(null == front) {
                return head;
            }
            ListNode prevNext = front.next;

            ListNode reverse = reverseList(head);
            ListNode currRev = reverse;
            while(null != currRev && k > 1) {
                currRev = currRev.next;
                k--;
            }
            ListNode reverseNext = currRev.next;

            head = reverseList(reverse);

            reverseNext.next = front;
            front.next = currRev.next;

            prevFront.next = currRev;
            currRev.next = prevNext;

            return head;
        }

        public ListNode reverseList(ListNode node){
            if(null == node || null  == node.next) {
                return node;
            }

            ListNode curr = node;
            ListNode prev = null;
            ListNode next = curr.next;
            while(null != next){
                curr.next = prev;
                prev = curr;
                curr = next;
                next = next.next;
            }
            curr.next = prev;
            return curr;
        }

}
