public class LinkedList {

    public static class Node {
        int val;
        Node next;
    }

    Node head = null;

    public LinkedList(int val) {
        this.head = new Node();
        head.val = val;
        head.next = null;
    }

    public void addNode(int val) {
        if(null == head) {
            new LinkedList(val);
            return;
        }

        Node temp = head;
        while(null != temp.next) {
            temp = temp.next;
        }

        Node node = new Node();
        node.val = val;
        temp.next = node;
    }

    public void printList(Node head) {
        System.out.println();
        while(null != head) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(1);
        for(int i=2 ; i <= 8; i++) {
            linkedList.addNode(i);
        }
        linkedList.printList(linkedList.head);
        System.out.println("Reversing the list ");
        linkedList.head = linkedList.reverseList(linkedList.head);
        linkedList.printList(linkedList.head);

        linkedList.head = linkedList.reverseList(linkedList.head);
        linkedList.printList(linkedList.head);

        linkedList.head = linkedList.reverseInKGroup(linkedList.head, 3);
        linkedList.printList(linkedList.head);
    }

    public Node reverseList(Node head) {
        if(null == head || null == head.next) {
            return head;
        }
        Node prev = null, curr = head, next = head.next;
        while(next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        return curr;
    }

    public Node reverseInKGroup(Node head, int k) {
        if (null == head || null == head.next) {
            return head;
        }
        Node curr = head;
        Node prev = null;
        Node next = curr.next;
        int temp = k - 1;
        Node prevLast = curr;
        while (temp > 0 && null != next) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
            temp--;
        }
        curr.next = prev;
        prevLast.next = reverseInKGroup(next, k);

        return curr;
    }
}
