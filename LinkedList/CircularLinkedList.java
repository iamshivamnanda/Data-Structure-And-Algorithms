package LinkedList;

public class CircularLinkedList {
    Node head = null;
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
        if(this.head == null ){
            this.head = curNode;
            this.tail = this.head;
        }
        if(this.tail != null){
            this.tail.next = curNode;
        }
        curNode.next = head;
    }

    void prepend(int value){
        Node curNode = new Node(value);
        if(this.head != null){
            curNode.next = this.head;
        }
        this.head = curNode;
        this.tail.next = curNode;
        if(this.tail == null){
            this.tail = curNode;
            curNode.next = this.head;
        }
    }

    Node find(int value){

        if(this.head == null){
            return null;
        }
        Node curNode = this.head;
        while (curNode.next != this.head) {
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
        while(head.next != this.head){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println(this.head);
    }

    void deleteHead(){
        if(this.head == null){
            return;
        }
        this.head = this.head.next;
    }

    public static void main(String[] args) {
        // LinkedList mLinkedList = new LinkedList();
        // mLinkedList.prepend(5);
        // mLinkedList.prepend(15);
        // mLinkedList.prepend(20);
        // // mLinkedList.append(50);
        // System.out.println(mLinkedList.tail.next);

        // mLinkedList.printList();

        // mLinkedList.prepend(100);
        // mLinkedList.printList();


        // mLinkedList.insertAfter(80, 15);
        // mLinkedList.printList();

        // mLinkedList.deleteHead();
        // mLinkedList.printList();


    
    }
}
