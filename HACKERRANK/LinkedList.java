package HACKERRANK;

import java.util.List;
import java.util.Scanner;

class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

public class LinkedList {
    public static Node insertAtTheHead(Node list,int data){
        Node node = new Node(data);
        node.next = list;
        return node;
    }
    public static void printLinkedList(Node head) {
        while (head !=null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        SinglyLinkedListNode head = llist;
        if(position == 0){
            SinglyLinkedListNode node = new SinglyLinkedListNode(data);
            node.next = llist;
            return node;
        }
        for (int i = 1; i < position && llist != null; i++) {
            llist = llist.next;
        }
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        node.next = llist.next;
        llist.next = node;
        return head;
        }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Node head = null;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- >0) {
            int val = sc.nextInt();
           head =  insertAtTheHead(head, val);
        }
        sc.close();
        printLinkedList(head);
    }

}
