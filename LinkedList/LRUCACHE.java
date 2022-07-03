package LinkedList;

import java.util.HashMap;


class DoubleNode { 
	int key; 
	int value; 
	DoubleNode pre; 
	DoubleNode next; 

	public DoubleNode(int key, int value) 
	{ 
		this.key = key; 
		this.value = value; 
	} 
} 
public class LRUCACHE {
    private static int capacity,count;
   private static HashMap<Integer,DoubleNode> mhash;
   private static DoubleNode head, tail; 
    LRUCACHE(int cap)
    {   
        mhash = new HashMap<>(); 
		head = new DoubleNode(0, 0); 
		tail = new DoubleNode(0, 0); 
        head.next = tail; 
		tail.pre = head; 
		head.pre = null; 
		tail.next = null; 
		count = 0; 
       capacity = cap;
    }
    public static void deleteNode(DoubleNode node) 
	{ 
		node.pre.next = node.next; 
		node.next.pre = node.pre; 
	} 

	public static void addToHead(DoubleNode node) 
	{ 
		node.next = head.next; 
		node.next.pre = node; 
		node.pre = head; 
		head.next = node; 
	} 
    //Function to return value corresponding to the key.
    public static int get(int key)
    {
    	if (mhash.get(key) != null) { 
			DoubleNode node = mhash.get(key); 
			int result = node.value; 
			deleteNode(node); 
			addToHead(node); 
			return result; 
		} 
		return -1; 
    }

    //Function for storing key-value pair.
    public static void set(int key, int value)
    {
     if(mhash.get(key)!=null){
        DoubleNode node = mhash.get(key); 
        node.value = value; 
        deleteNode(node); 
        addToHead(node); 
     }   	else { 
        DoubleNode node = new DoubleNode(key, value); 
        mhash.put(key, node); 
        if (count < capacity) { 
            count++; 
            addToHead(node); 
        } 
        else { 
            mhash.remove(tail.pre.key); 
            deleteNode(tail.pre); 
            addToHead(node); 
        } 
    } 
    }
}
