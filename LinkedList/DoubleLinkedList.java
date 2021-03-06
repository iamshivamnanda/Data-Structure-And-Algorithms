package LinkedList;

public class DoubleLinkedList {
    Node head;
    Node tail = null;

    class Node {
        int data;
        Node next, prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    void append(int value) {
        Node curNode = new Node(value);
        if (this.tail != null) {
            curNode.prev = this.tail;
            this.tail.next = curNode;
        }
        this.tail = curNode;
        if (this.head == null) {
            this.head = curNode;
        }
    }

    void prepend(int value) {
        Node curNode = new Node(value);
        if (this.head != null) {
            this.head.prev = curNode;
            curNode.next = this.head;
        }
        this.head = curNode;
        if (this.tail == null) {
            this.tail = curNode;
        }
    }

    Node find(int value) {

        if (this.head == null) {
            return null;
        }
        Node curNode = this.head;
        while (curNode != null) {
            if (curNode.data == value) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    void insertAfter(int value, int aftervalue) {
        Node afterNode = this.find(aftervalue);
        if (afterNode != null) {
            Node newNode = new Node(value);
            newNode.prev = afterNode;
            newNode.next = afterNode.next;
            afterNode.next = newNode;
        }
    }

    void printList() {
        Node head = this.head;
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    void deleteHead() {
        if (this.head == null) {
            return;
        }
        this.head = this.head.next;
        this.head.prev = null;
    }

    public static Node reverseDLL(Node head) {
        // 3 4 5
        // 5 4 3

        if (head == null)
            return null;
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        prev.prev = null;
        return prev;
    }

    public static void main(String[] args) {
        DoubleLinkedList mLinkedList = new DoubleLinkedList();
        mLinkedList.append(5);
        mLinkedList.append(15);
        mLinkedList.append(20);
        mLinkedList.append(50);

        mLinkedList.printList();

        // mLinkedList.prepend(100);
        // mLinkedList.printList();

        System.out.println(mLinkedList.find(5).prev);

        mLinkedList.deleteHead();
        mLinkedList.printList();
        System.out.println(mLinkedList.find(15).prev);

        // mLinkedList.insertAfter(80, 15);
        // mLinkedList.printList();

        // mLinkedList.deleteHead();
        // mLinkedList.printList();
    }
}
