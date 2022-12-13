package LinkedList;

import java.util.ArrayDeque;
import java.util.HashSet;
public class LinkedListSDEsheet {

    static class Node {
        int data;
        Node next;
        Node child;

        public Node(int data) {
            this.data = data;
        }
    }

    // Definition for singly-linked list.
    public class ListNode {
        public int val;
        public ListNode next;

        ListNode() {
        }

        public ListNode(int val) {
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

    Node compute(Node head) {
        // 12 15 10 11 5 6 2 3
        // 15 11 6 3
        Node curr = head;
        while (curr.next != null) {
            if (curr.data < curr.next.data) {
                curr.data = curr.next.data;
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    Node divide(int N, Node head) {
        // 17 15 8 9 2 4 6
        // 8 2 4 6 17 15 9

        Node curr = head;
        Node evenStart = null;
        Node evenEnd = null;
        Node oddStart = null;
        Node oddEnd = null;

        while (curr != null) {
            if (curr.data % 2 == 0) {
                if (evenStart == null) {
                    evenStart = curr;
                    evenEnd = curr;
                } else {
                    evenEnd.next = curr;
                    evenEnd = curr;
                }
            } else {
                if (oddStart == null) {
                    oddStart = curr;
                    oddEnd = curr;
                } else {
                    oddEnd.next = curr;
                    oddEnd = curr;
                }
            }
            curr = curr.next;
        }
        if (evenEnd != null) {
            evenEnd.next = oddStart;
        }
        if (oddEnd != null) {
            oddEnd.next = null;
        }

        return (evenStart != null) ? evenStart : oddStart;
    }

    public static Node getMiddle(Node node) {
        if (node == null || node.next == null)
            return node;
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 1 - 2 - 3 - 4
    //

    public static Node reverseList(Node node) {
        if (node == null || node.next == null)
            return node;
        Node prev = null;
        Node curr = node;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static Node rearrange(Node root) {
        /*
         * To Rearrange Linked List
         * 1. find the middle of linked list
         * 2. reverse the linked list from middle to end
         * 3. now alternativily merge list 1 and list 2
         */
        Node node1 = root;
        Node mNode = getMiddle(root);
        Node node2 = mNode.next;
        mNode.next = null;
        node2 = reverseList(node2);

        // merge the list
        Node start = node1;
        Node curr = node1;
        node1 = node1.next;
        while (node1 != null || node2 != null) {
            if (node2 != null) {
                curr.next = node2;
                node2 = node2.next;
                curr = curr.next;
            }
            if (node1 != null) {
                curr.next = node1;
                node1 = node1.next;
                curr = curr.next;
            }
        }
        return start;

    }

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        int inteval = 1;
        while (inteval < n) {
            for (int i = 0; i < n - inteval; i = i + inteval * 2) {
                lists[i] = mergeTwoList(lists[i], lists[i + inteval]);
            }
            inteval = inteval * 2;
        }

        return n > 0 ? lists[0] : null;
    }

    public ListNode mergeTwoList(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
                curr = curr.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
                curr = curr.next;
            }
        }
        if (list1 != null) {
            curr.next = list1;
        } else if (list2 != null) {
            curr.next = list2;
        }

        return head.next;
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;

        Node middle = getMiddle(head);
        Node node2 = middle.next;
        middle.next = null;

        Node node1 = mergeSort(head);
        node2 = mergeSort(node2);

        return sortedMerge(node1, node2);
    }

    static Node sortedMerge(Node first, Node second) {
        if (first == null)
            return second;
        if (second == null)
            return first;
        Node res = null;
        if (first.data < second.data) {
            res = first;
            first.next = sortedMerge(first.next, second);
        } else {
            res = second;
            second.next = sortedMerge(first, second.next);
        }

        return res;
    }

    // algorithn is we will find the partion element and partition all elements
    // around it
    // we will have start and end
    // traverse through all
    // elements greater then selected partition elment (which in this case is end)
    // put then above the end
    // return the partition head
    // rec call for start and end

    public static Node quickSort(Node node) {
        if (node == null || node.next == null)
            return node;
        Node end = node;
        while (end.next != null) {
            end = end.next;
        }

        return quickSortUtil(node, end);
    }

    public static Node quickSortUtil(Node start, Node end) {
        if (start == null || end == null || start == end)
            return start;

        Node partition = partition(start, end);
        Node node = quickSortUtil(start, partition);
        quickSortUtil(partition.next, end);

        return node;

    }

    public static Node partition(Node start, Node end) {

        Node prev = start;
        Node current = start;
        int pivot = end.data;
        while (start != end) {
            if (start.data < pivot) {
                prev = current;
                int temp = current.data;
                current.data = start.data;
                start.data = temp;
                current = current.next;

            }
            start = start.next;
        }
        int temp = current.data;

        current.data = pivot;

        end.data = temp;

        return prev;
    }

    // getlen of linkedlist
    public static int getLength(Node node) {
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    public static Node padd(Node head, int n) {
        if (head == null || n == 0)
            return head;

        Node temp = new Node(0);
        temp.next = head;
        n--;

        while (n-- > 0) {
            Node newv = new Node(0);
            newv.next = temp;
            temp = newv;
        }
        return temp;
    }

    public static boolean big(Node l1, Node l2) {
        if (l1 == null) {
            return false;
        }
        if (l2 == null) {
            return true;
        }
        while (l1.data != l2.data) {
            l1 = l1.next;
            l2 = l2.next;
        }

        return (l1.data > l2.data) ? true : false;
    }

    static int borrow = 0;

    public static void subUtil(Node l1, Node l2) {
        if (l1 == null && l2 == null)
            return;
        subUtil(l1.next, l2.next);
        l1.data -= borrow;

        if (l1.data < l2.data) {
            l1.data += 10;
            borrow = 1;
        } else {
            borrow = 0;
        }
        l1.data = l1.data - l2.data;
    }

    // sub two linked list can be of different size
    static Node subLinkedList(Node l1, Node l2) {
        // first find the linked list with large size
        // if size are not equal then pad the smaller linked list
        // check which linked list is big
        // then call sub util and maintain borrow

        int length1 = getLength(l1);
        int length2 = getLength(l2);

        if (length1 < length2) {
            l1 = padd(l1, length2 - length1);
        } else {
            l2 = padd(l2, length1 - length2);
        }

        if (big(l1, l2)) {
            subUtil(l1, l2);
            return l1;
        } else {
            subUtil(l2, l1);
            return l2;
        }

    }
}

class GfG {

    Node cur; // Dont change the variable name, its used in main function
    int carry; // Dont change the variable name, its used in main function

    // This function is called after the smaller list is added to the sublist of
    // bigger list of same size. Once the right sublist is added, the carry
    // must be added to left side of larger list to get the final result.
    void addCarryToRemaining(Node head, LinkedList res) {
        // Write code here
        if (this.carry > 0) {
            res.append(carry);
        }
    }

    // Function which adds two linked lists of same size represented by head1
    // and head2 and returns head of the resultant linked list. Carry
    // is propagated while returning from the recursion
    void addSameSize(Node head1, Node head2, LinkedList res) {

        ArrayDeque<Integer> stach1 = new ArrayDeque<>();
        ArrayDeque<Integer> stach2 = new ArrayDeque<>();
        while (head1 != null) {
            stach1.push(head1.data);
            head1 = head1.next;
        }
        while (head2 != null) {
            stach2.push(head2.data);
            head2 = head2.next;
        }

        while (!stach1.isEmpty() || !stach2.isEmpty()) {
            int a = 0, b = 0;
            if (!stach1.isEmpty()) {
                a = stach1.pop();
            }
            if (!stach2.isEmpty()) {
                b = stach2.pop();
            }
            int ress = a + b + this.carry;
            res.prepend(ress % 10);
            this.carry = ress / 10;
        }
    }
}