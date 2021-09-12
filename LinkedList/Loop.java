package LinkedList;

public class Loop {

    public static boolean detectLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    static int countNodesinLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return 0;
        }
        int count = 1;
        fast = fast.next;
        while (slow != fast) {
            count++;
            fast = fast.next;
        }
        return count;
    }

    public static void removeLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return;
        }
        slow = head;
        if (slow != fast) {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
        } else {
            while (fast.next != slow) {
                fast = fast.next;
            }
            fast.next = null;
        }
        

    }
    public Node rotate(Node head, int k) {
        // add code here
        Node curr = head;
        for(int i=1;i<k;i++){
            curr = curr.next;
        }
       
        Node next = curr.next;
        curr.next = null;
        curr = next;
        if(curr == null){
            return head;
        }
        while(curr.next!=null){
            curr = curr.next;
        }
        curr.next = head;
        return next;
    }
    static Node addTwoLists(Node first, Node second){
        Node cur = first;
       Node prev = null;
       while (cur != null) {
           Node next = cur.next;
           cur.next = prev;
           prev = cur;
           cur = next;
       }
       Node firsthead =  prev;
         cur = second;
        prev = null;
       while (cur != null) {
           Node next = cur.next;
           cur.next = prev;
           prev = cur;
           cur = next;
       }
       Node secondhead =  prev;
        
       Node res = null;
         prev = null;
        Node temp = null;
        int carry = 0, sum;
        while (firsthead != null || secondhead!=null) {
            sum = carry + (firsthead != null ? firsthead.data : 0)
                  + (secondhead != null ? secondhead.data : 0);
            carry = (sum >= 10) ? 1 : 0;
            sum = sum % 10;
            temp = new Node(sum);
            if (res == null) {
                res = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
            if (firsthead != null) {
                firsthead = firsthead.next;
            }
            if (secondhead != null) {
                secondhead = secondhead.next;
            }
        }
        if (carry > 0) {
            temp.next = new Node(carry);
        }
          cur = res;
        prev = null;
       while (cur != null) {
           Node next = cur.next;
           cur.next = prev;
           prev = cur;
           cur = next;
       }
        return prev;
    }
    public static Node pairwise_swap(Node node)
    {
        if(node== null || node.next ==null){
            return node;
        }
        Node curr = node.next.next;
        Node prev = node;
        node = node.next;
        node.next = prev;
        while(curr!=null && curr.next!=null){
            prev.next = curr.next;
            prev = curr;
            Node next = curr.next.next;
            curr.next.next = curr;
            curr = next;
        }
        prev.next = curr;
        return node;
    }
    Node copyList(Node head) {
        Node curr = head;
        while(curr!=null){
            Node next = curr.next;
            curr.next = new Node(curr.data);
            curr.next.next = next;
            curr = next;
        }
        for( curr = head;curr!=null;curr = curr.next.next){
            curr.next.arb = (curr.arb!=null) ? curr.arb.next : null;
        }
        Node clonehead = head.next;
        Node original = head, copy = head.next; 
        while (original!=null && copy!=null) 
        { 
            original.next = 
             original.next!=null? original.next.next : original.next; 
      
            copy.next = copy.next!=null?copy.next.next:copy.next; 
            original = original.next; 
            copy = copy.next; 
        } 
        return clonehead;
     }
    
        boolean isPalindrome(Node head) 
        {
            //Your code here
            if(head.next == null){
                return true;
            }
            if(head.next.next == null){
                return head.data == head.next.data ? true:false;
            }
              
             Node slow = head;
            Node fast = head.next.next;
            while (fast!=null && fast.next!=null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            Node center = slow;
            Node prev = null;
            while (slow!=null) {
                Node next = slow.next;
                slow.next = prev;
                prev = slow;
                slow = next;
            }
            Node reverNode = prev;
            Node simpleNode =head;
            boolean flag = true;
            while (simpleNode!=null && reverNode!=null) {
    
                if(simpleNode.data != reverNode.data){
                    flag = false;
                }
                simpleNode = simpleNode.next;
                reverNode = reverNode.next;
            }
            Node curr = prev;
            prev = null;
            while (slow!=null) {
                Node next = slow.next;
                slow.next = prev;
                prev = slow;
                slow = next;
            }
            center.next = prev;
            return flag;
        } 
    
}
