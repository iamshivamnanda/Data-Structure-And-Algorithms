package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Basic {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (hMap.containsKey(val)) {
                int[] toreturn = { hMap.get(val), i };
                return toreturn;
            }
            hMap.put(nums[i], i);
        }
        int[] fail = { -1, -1 };
        return fail;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        StringBuilder sB = new StringBuilder();
        while (l1 != null && l2 != null) {
            int val = carry + l1.val + l2.val;
            carry = val / 10;
            val = val % 10;
            sB.append(val);
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null ) {
            int val = carry + l1.val ;
            carry = val / 10;
            val = val % 10;
            sB.append(val);
            l1 = l1.next;
        }
        while (l2 != null ) {
            int val = carry + l2.val ;
            carry = val / 10;
            val = val % 10;
            sB.append(val);
            l2 = l2.next;
        }
        if (carry > 0) {
            sB.append(carry);
        }
        String res = sB.toString();
        ListNode head = new ListNode(res.charAt(0) - '0');
        ListNode curr = head;
        for (int i = 1; i < res.length(); i++) {
            ListNode node = new ListNode(res.charAt(i) - '0');
            curr.next = node;
            curr = node;
        }
        return head;
    }
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        int maxlen = 1;
        int len = 1;
        HashSet<Character> hSet = new HashSet<>();
        hSet.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if(hSet.contains(s.charAt(i))){
                maxlen = Math.max(len, maxlen);
                len = 0;
                hSet.clear();
                continue;
            }
            len++;
            hSet.add(s.charAt(i));
        }
        return maxlen;
    }
}
