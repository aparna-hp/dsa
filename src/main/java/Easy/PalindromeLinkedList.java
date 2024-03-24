package Easy;

public class PalindromeLinkedList {

    public static class ListNode {
           int val;
           ListNode next;
           ListNode() {}
           ListNode(int val) { this.val = val; }
           ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void printLL(ListNode head){
        if(null == head) {
            return;
        }
        System.out.println("LL : ");
        ListNode curr = head;
        while(null != curr) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println(" ");

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        System.out.println("Is palindrom " + palindromeLinkedList.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        if(null == head || null == head.next) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        int count =1;
        while(null != fast && null != fast.next){
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }
        System.out.println("Count of first half " + count);
        slow = slow.next;
        if(null != fast) {
            slow = slow.next;
        }

        ListNode part2 = reverseList(slow);
        slow = head;
        while(count > 0) {
            if(slow.val != part2.val) {
                return false;
            }
            slow = slow.next;
            part2 = part2.next;
            count--;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if(null == head || null == head.next) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;

        while(null != next) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }

        curr.next = prev;
        return curr;
    }


}
