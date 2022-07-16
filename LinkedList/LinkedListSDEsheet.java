package LinkedList;

import java.util.HashMap;
import java.util.HashSet;

public class LinkedListSDEsheet {
    // Definition for singly-linked list.
    public class ListNode {
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

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr.next != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> map = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (map.contains(curr)) {
                return true;
            }
            map.add(curr);
            curr = curr.next;
        }
        return false;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode headNode = new ListNode(0);

        ListNode workingNode = headNode;

        while (true) {
            if (list1 == null) {
                workingNode.next = list2;
                break;
            }
            if (list2 == null) {
                workingNode.next = list1;
                break;
            }

            if (list1.val <= list2.val) {
                workingNode.next = list1;
                list1 = list1.next;
            } else {
                workingNode.next = list2;
                list2 = list2.next;
            }
            workingNode = workingNode.next;
        }

        return headNode.next;

    }

    void deleteNode(Node del) {
        del.data = del.next.data;
        del.next = del.next.next;
    }

    public Node removeDuplicates(Node head) {
        HashSet<Integer> hSet = new HashSet<>();

        Node prev = head;
        Node curr = head.next;
        hSet.add(head.data);

        while (curr != null) {
            if (hSet.contains(curr.data)) {
                curr = curr.next;
                continue;
            }
            hSet.add(curr.data);
            prev.next = curr;
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr;
        return head;
    }

    static Node segregate(Node head) {
        if (head == null)
            return head;

        int arr[] = new int[3];
        Node curr = head;
        while (curr != null) {
            arr[curr.data]++;
            curr = curr.next;
        }

        Node newHead = null;
        curr = null;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                Node node = new Node(i);
                if (newHead == null) {
                    newHead = node;
                    curr = node;
                } else {
                    curr.next = node;
                    curr = curr.next;
                }
            }
        }

        return newHead;
    }

    public long multiplyTwoLists(Node l1, Node l2) {
        long N = 1000000007;
        long num1 = 0;
        long num2 = 0;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                num1 = (num1 * 10) % N + l1.data;
                l1 = l1.next;
            }

            if (l2 != null) {
                num2 = (num2 * 10) % N + l2.data;
                l2 = l2.next;
            }
        }

        return (num1 * num2) % N;

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1) {
            return null;
        }

        ListNode first = head;
        ListNode second = head;

        for (int i = 0; i < n ; i++) {
            second = second.next;
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
        return head;

    }

}