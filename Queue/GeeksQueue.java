package Queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



class MyQueue {

    int front, rear;
	int arr[] = new int[100005];

    MyQueue()
	{
		front=0;
		rear=0;
	}
    
	
	//Function to push an element x in a queue.
	void push(int x)
	{
	    // Your code here
	    arr[rear++] = x;
	} 

    //Function to pop an element from queue and return that element.
	int pop()
	{
	    if(front==rear){
	        return -1;
	    }
		int val = arr[front++];
		return val;
	} 
    void enqueue(Queue<Integer> q, int x){
        q.add(x);
    }
    
    //Function to remove front element from queue.
    void dequeue(Queue<Integer> q){
        // code here
        q.poll();
    }
    
    //Function to find the front element of queue.
    int front(Queue<Integer> q){
      return q.peek();
    }
    
    //Function to find an element in the queue.
    String find(Queue<Integer> q, int x){
        
     

          if(q.contains(x)){
              return "Yes";
          }
      return "No";
    }
    static void insert(Queue<Integer> q, int k){
        
        q.add(k);
        
    }
    
    // Function to find frequency of an element
    // rteturn the frequency of k
    static int findFrequency(Queue<Integer> q, int k){
        
        // Your code here
        Object arr[] = q.toArray();
        int freq = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(k)){
                freq++;
            }
        }
        return freq;
    }
    public Queue<Integer> rev(Queue<Integer> q){
        Stack<Integer> arrayDeque = new Stack<Integer>();
        while(q.isEmpty()==false){
            arrayDeque.add(q.poll());
        }
        while (arrayDeque.isEmpty() == false) {
            q.add(arrayDeque.pop());
        }
        return q;
    }
}

class StackQueue
{
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    //Function to push an element in queue by using 2 stacks.
    void Push(int x)
    {
	   while (s1.isEmpty()==false) {
           s2.add(s1.pop());
       }
       s1.add(x);
       while (s2.isEmpty()==false) {
           s1.add(s2.pop());
       }
    }
	
    
    //Function to pop an element from queue by using 2 stacks.
    int Pop()
    {
        if (s1.isEmpty()) {
            return -1;
        }
	   return s1.pop();
    }
}
class Queues
{
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();
    
    //Function to push an element into stack using two queues.
    void push(int a)
    {
	    while (q1.isEmpty()==false) {
            q2.add(q1.remove());
        }
        q1.add(a);
        while (q2.isEmpty()==false) {
            q1.add(q2.remove());
        }
    }
    
    //Function to pop an element from stack using two queues. 
    int pop()
    {
	    if(q1.isEmpty()){
            return -1;
        }
        return q1.poll();
    }
	
}

public class GeeksQueue {
    static ArrayList<String> generate(int N)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        Queue<String> q = new LinkedList<String>();
        q.add("1");
        for (int i = 0; i < N; i++) {
            String top =  q.poll();
            arrayList.add(top);
            q.add(top + "0");
            q.add(top + "1");
        }
        return arrayList;
    }
    public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
        Queue<Integer> q1 = new LinkedList<Integer>();
        Stack<Integer> s1 = new Stack<Integer>();
        int i = 0;
        while(!q.isEmpty() &&i<k) {
            s1.push(q.remove());
            i++;
        }
        while (q.isEmpty() ==false) {
            q1.add(q.remove());
        }
        while (s1.isEmpty() == false) {
            q.add(s1.pop());
        }
        q.addAll(q1);
        return q;
    }
    int tour(int petrol[], int distance[])
    {
	// Your code here	
	int start=0,deficit=0;
   int capacity=0;
   
   for(int i=0;i<petrol.length;i++)
   {
       capacity+=petrol[i]-distance[i];
       if(capacity<0){
           start=i+1;
           deficit+=capacity;
           capacity=0;
       }
   }
   return(capacity+deficit>0)?start:-1;
    }
}
