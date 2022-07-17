package LinkedList;

import java.net.http.HttpHeaders;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

import BitMagic.numofonebits;

public class LinkedListSDEsheet {

    class Node {
        int data;
        Node next;
        Node child;

        public Node(int data) {
            this.data = data;
        }
    }

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

        for (int i = 0; i < n; i++) {
            if (second.next == null) {
                if (i == n - 1)
                    head = head.next;
                return head;
            }
            second = second.next;
        }

        while (second != null && second.next != null) {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
        return head;

    }

    public void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }

        ListNode mid = head;
        ListNode second = head;

        while (second != null && second.next != null) {
            second = second.next.next;
            mid = mid.next;
        }

        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (mid != null) {
            stack.push(mid);
            mid = mid.next;
        }

        ListNode curr = head;

        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            node.next = curr.next;
            curr.next = node;
            curr = node.next;
        }
        curr.next = null;

        return;

    }

    public static void removeLoop(Node head) {
        HashSet<Node> hSet = new HashSet<>();

        Node curr = head;
        while (curr.next != null) {
            if (hSet.contains(curr.next)) {
                curr.next = null;
                break;
            }
            hSet.add(curr);
            curr = curr.next;
        }
    }

    // Function to find intersection point in Y shaped Linked Lists.
    int intersectPoint(Node head1, Node head2) {
        boolean flag1 = false;
        boolean flag2 = false;
        Node ptr1 = head1;
        Node ptr2 = head2;

        if (ptr1 == null || ptr2 == null) {
            return -1;
        }
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            if (ptr1 == ptr2) {
                return ptr1.data;
            }
            if (ptr1 == null) {
                if (flag1)
                    return -1;
                ptr1 = head2;
                flag1 = true;
            }
            if (ptr2 == null) {
                if (flag2)
                    return -1;
                ptr2 = head1;
                flag2 = true;
            }
        }
        return ptr1.data;
    }

    public static Node flattenMultiLinkedList(Node head) {
        if (head == null)
            return null;
        Node curr = head;
        Node tail = head;

        while (tail.next != null) {
            tail = tail.next;
        }

        Node temp = null;

        while (curr != tail) {
            if (curr.child != null) {
                tail.next = curr.child;
                temp = curr.child;
                while (temp.next != null) {
                    temp = temp.next;
                }
                tail = temp;
            }
            curr = curr.next;
        }

        return head;
    }

    public static Node zigZag(Node head) {
        // 1 2 3 4
        // 1 3 2 4

        if (head == null)
            return null;
        Node prev = head;
        Node curr = head.next;
        boolean flag = false; // false means position as even true means odd position

        while (curr != null) {
            if (!flag) {
                if (curr.data < prev.data) {
                    int temp = prev.data;
                    prev.data = curr.data;
                    curr.data = temp;
                }
            } else {
                if (curr.data > prev.data) {
                    int temp = prev.data;
                    prev.data = curr.data;
                    curr.data = temp;
                }
            }
            flag = !flag;
            prev = curr;
            curr = curr.next;
        }
        return head;
    }

}