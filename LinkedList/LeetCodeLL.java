package LinkedList;


 class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

public class LeetCodeLL {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr = l1;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        ListNode firstHead  = prev;
        curr = l2;
        prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        ListNode secondHead  = prev;
        ListNode res = null;
        prev = null;
        int carry = 0,sum =0;
        ListNode temp = null;

        while (firstHead != null || secondHead!=null) {
            sum =  carry + ((firstHead!=null)?firstHead.val:0) + ((secondHead!=null)?secondHead.val:0);
            carry = sum >=10 ? 1:0;
            sum = sum%10;
            temp = new ListNode(sum);
            if(res == null){
                res = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
            if (firstHead != null) {
                firstHead = firstHead.next;
            }
            if (secondHead != null) {
                secondHead = secondHead.next;
            }
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
          curr = res;
        prev = null;
       while (curr != null) {
           ListNode next = curr.next;
           curr.next = prev;
           prev = curr;
           curr = next;
       }

        curr = prev;
         prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;


    }
}
