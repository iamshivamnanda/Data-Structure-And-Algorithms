package LinkedList;

class Node
{
	int data;
	Node next;
	Node bottom;
	
	Node(int d)
	{
		data = d;
		next = null;
		bottom = null;
	}
}

public class LinkedListSDESheet2 {
    static Node sortedMerge(Node first, Node second) {
        if (first == null)
            return second;
        if (second == null)
            return first;
        Node res = null;
        if (first.data < second.data) {
            res = first;
            first.bottom = sortedMerge(first.bottom, second);
        } else {
            res = second;
            second.bottom = sortedMerge(first, second.bottom);
        }
        res.next = null;
        return res;
    }

    Node flatten(Node root)
    {
        if(root == null || root.next == null) return root;

        root.next = flatten(root.next);

        root = sortedMerge(root, root.next);

        return root;
    }
}
