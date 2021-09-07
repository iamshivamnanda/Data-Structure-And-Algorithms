package LinkedList;

public class LinkedList {
    Node head;
    Node tail = null;
    public class Node {
        int data;
        Node next = null;

        Node(int value){
            this.data = value;
        }
    }

    void append(int value){
        Node curNode =  new Node(value);
        if(this.tail != null){
            this.tail.next = curNode; 
        }
        this.tail = curNode;
        if(this.head == null ){
            this.head = curNode;
        }
    }

    void prepend(int value){
        Node curNode = new Node(value);
        if(this.head != null){
            curNode.next = this.head;
        }
        this.head = curNode;
        if(this.tail == null){
            this.tail = curNode;
        }
    }

    Node find(int value){

        if(this.head == null){
            return null;
        }
        Node curNode = this.head;
        while (curNode != null) {
            if(curNode.data == value){
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    void insertAfter(int value,int aftervalue){
        Node afterNode = this.find(aftervalue);
        if(afterNode != null){
            Node newNode = new Node(value);
            newNode.next = afterNode.next;
            afterNode.next = newNode;
        }
    }


    void printList(){
        Node head = this.head;
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    void deleteHead(){
        if(this.head == null){
            return;
        }
        this.head = this.head.next;
    }

    public static void main(String[] args) {
        LinkedList mLinkedList = new LinkedList();
        mLinkedList.append(5);
        mLinkedList.append(15);
        mLinkedList.append(20);
        mLinkedList.append(50);

        mLinkedList.printList();

        mLinkedList.prepend(100);
        mLinkedList.printList();

        System.out.println(mLinkedList.find(20).data);

        mLinkedList.insertAfter(80, 15);
        mLinkedList.printList();

        mLinkedList.deleteHead();
        mLinkedList.printList();
    }
}
