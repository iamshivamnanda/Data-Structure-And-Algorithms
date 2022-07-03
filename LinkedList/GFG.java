package LinkedList;

import java.util.HashMap;
import java.util.HashSet;

class Node{
    int data;
    Node next,arb;
    Node(int a){  data = a; next = arb = null; }
}
public class GFG {
    public static int getCount(Node head)
    {
        if(head == null){
            return 0;
        }
        Node currNode = head;
        int count = 0;
        while (currNode !=null) {
            count++;
            currNode = currNode.next;
        }
        return count;
    }
     //Function to insert a node at the beginning of the linked list.
     public static Node insertAtBeginning(Node head, int x)
     {
         Node temp = new Node(x);
         temp.next = head;
         return temp;
     }
     
     //Function to insert a node at the end of the linked list.
     public static Node insertAtEnd(Node head, int x)
     {
        Node temp = new Node(x);
        if(head == null){
            return temp;
        }
        Node curNode = head;
        while (curNode.next !=null) {
            curNode = curNode.next;
        }
        curNode.next = temp;
        return head;
     }
     public static void addNode(Node head_ref, int pos, int data)
     {
             int l = 0;
             Node curnode = head_ref;
             while(l!=pos && curnode.next!=null){
                 l++;
                 curnode = curnode.next;
             }
             Node temp = new Node(data);
             temp.next = curnode.next;
             curnode.next = temp;
            //  temp.prev = curnode;
             
     }
     public Node insertInMid(Node head, int data){
         if(head==null){
            Node temp = new Node(data);
            return temp;
         }
        Node first = head;
        Node second = head;
        while (second.next != null && second.next.next !=null) {
            first = first.next;
            second = second.next.next;
        }
        Node temp = new Node(data);
        temp.next = first.next;
        first.next= temp;
        return head;
    }
    public boolean isIdentical (Node head1, Node head2){
        Node curNode1 = head1; 
        Node curNode2 = head2;
        while (curNode1.next !=null && curNode2.next !=null) {
            if(curNode1.data != curNode2.data){
                return false;
            }
            curNode1 = curNode1.next;
            curNode2 = curNode2.next;
        } 
        if(curNode1.next!=null || curNode2.next !=null){
            return false;
        }
        if(curNode1.data != curNode2.data){
            return false;
        }
        return true;
    }
    void deleteNode(Node del)
    {
         Node temp = del.next;
         del.data = temp.data;
         del.next = temp.next;
    }
    Node removeDuplicates(Node head)
    {
        if(head == null){return null;}
	Node curNode = head;
    while(curNode.next !=null){
        if(curNode.next.data == curNode.data){
            curNode.next = curNode.next.next;
            continue;
        }
        curNode = curNode.next;
    }
    return head;
}
public Node removeDuplicatesUnsorted(Node head) 
    {
        if(head == null){
            return null;
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        Node curNode = head;
        hashSet.add(curNode.data);
         while(curNode.next != null){
            if(hashSet.contains(curNode.next.data)){
                curNode.next = curNode.next.next;
                continue;
            }
            curNode = curNode.next;
            hashSet.add(curNode.data);
         }
         return head;
    }
    Node sortedMerge(Node head1, Node head2) {
        
        Node headNode = new Node(0);
        
        Node workingNode = headNode;

        while (true) {
            if(head1 == null){
                workingNode.next = head2;
                break;
            }
            if(head2 == null){
                workingNode.next = head1;
                break;
            }

            if(head1.data<=head2.data){
                workingNode.next = head1;
                head1 = head1.next;
            }else{
                workingNode.next = head2;
                head2 = head2.next;
            }
            workingNode = workingNode.next;
        }
       
        return headNode.next;
      } 
      int getNthFromLast(Node head, int n)
    {
    	if(head == null){
            return -1;
        }
        Node first = head;
        for (int i = 0; i < n; i++) {
            if(first==null){
                return -1;
            }
            first = first.next;
        }
        Node second = head;
        while (first!=null) {
            second = second.next;
            first= first.next;
        }
        return second.data;
    }
    Node swapkthnode(Node head, int num, int K)
    {
        if(K>num){
            return head;
        }
        if (2 * K - 1 == num){
            return  head;
        }
       
        Node kthNode = head;
        Node kthNode_prev = null;
         for (int i = 1; i < K; i++) {
            kthNode_prev = kthNode;
            kthNode = kthNode.next;
        }

        Node kthNodeend = head;
        Node kthNodeend_prev = null;
         for (int i = 1; i < num-K+1; i++) {
            kthNodeend_prev = kthNodeend;
            kthNodeend = kthNodeend.next;
        }
      
        if(kthNode_prev != null){
            kthNode_prev.next = kthNodeend;
        }
      
        if(kthNodeend_prev != null){
            kthNodeend_prev.next = kthNode;
        }
        Node temp = kthNode.next;
        kthNode.next = kthNodeend.next;
        kthNodeend.next = temp;
        if (K == 1)
        head = kthNodeend;

    if (K == num)
        head = kthNode;
        return head;
    }
   public static Node reverseList(Node head)
    {
       Node cur = head;
       Node prev = null;
       while (cur != null) {
           Node next = cur.next;
           cur.next = prev;
           prev = cur;
           cur = next;
       }
       return prev;
    }
    public static String printMinIndexChar(String str, String patt){
        
        // Your code here
        
        // you don't need to print anything
         HashMap<Character,Integer> hMap = new HashMap<Character,Integer>();
       for (int i = 0; i < str.length(); i++) {
           if(!hMap.containsKey(str.charAt(i))){
               hMap.put(str.charAt(i), i);
           }
       }
       int res = 1000000000;
       for (int i = 0; i < patt.length(); i++) {
           if(hMap.containsKey(patt.charAt(i))){
                res = Math.min(res, hMap.get(patt.charAt(i)));
           }
       }
       return res == 1000000000 ? "$": String.valueOf(patt.charAt(res));
    
    }
}
