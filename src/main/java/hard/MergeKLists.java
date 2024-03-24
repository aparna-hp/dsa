package hard;

import medium.SwapNodesInLinkedList;

import java.util.List;

public class MergeKLists {
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
        ListNode[] lists = new ListNode[3];
        ListNode a = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode b = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode c = new ListNode(2, new ListNode(6));

        lists[0] = a;
        lists[1] = b;
        lists[2] = c;

        MergeKLists mergeKLists = new MergeKLists();
        ListNode ans = mergeKLists.mergeKLists(lists);
        mergeKLists.printList(ans);
      }

        public ListNode mergeKLists(ListNode[] lists) {
            if(null == lists || lists.length == 0 ){
                return null;
            }

            if(lists.length == 1) {
                return lists[0];
            }
            ListNode ans = lists[0];
            for(int i=1; i < lists.length; i++){
                ans = merge(ans, lists[i]);
            }
            return ans;
        }

        public ListNode merge(ListNode a, ListNode b) {
            ListNode c = new ListNode(-1);
            ListNode temp = c;
            while(a!= null && b!= null){
                if(a.val < b.val){
                    c.next = a;
                    a = a.next;
                    c = c.next;
                } else if(a.val > b.val){
                    c.next = b;
                    b = b.next;
                    c = c.next;
                }else {
                    c.next = new ListNode(a.val);
                    c = c.next;
                    c.next = a;
                    a = a.next;
                    b = b.next;
                    c = c.next;
                }
            }

            if(a != null){
                c.next = a;
            }

            if(b!= null){
                c.next = b ;
            }

            printList(c);
            return temp.next;
        }

}
