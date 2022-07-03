package Dequeue;
import java.util.*;

public class Deque {
    public static void push_back_pb(ArrayDeque<Integer> dq, int x) {
        // Your code here
        dq.addLast(x);
    }
    
    // Function to pop element from back of the deque.
      public static void pop_back_ppb(ArrayDeque<Integer> dq) {
        // Your code here
        if(dq.isEmpty()){
            return;
        }
        dq.pollLast();
    }
    
    // Function to return element from front of the deque.
      public static int front_dq(ArrayDeque<Integer> dq) {
        // Your code here
        if(dq.isEmpty()){
            return -1;
        }
        return dq.peekFirst();
    }
    
    // Function to push element x to the front of the deque.
      public static void push_front_pf(ArrayDeque<Integer> dq, int x) {
        // Your code here
        dq.addFirst(x);
    }
    
    static ArrayList <Integer> max_of_subarrays(int arr[], int n, int k)
    {
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!aDeque.isEmpty() && arr[i]>=arr[aDeque.peekLast()]) {
                aDeque.pollLast();
            }
            aDeque.offerLast(i);
        }
        for (int i = k; i < n; i++) {
            aList.add(arr[aDeque.peekFirst()]);
            while (!aDeque.isEmpty() && aDeque.peekFirst()<=i-k ) {
                aDeque.pollFirst();
            }
            while (!aDeque.isEmpty() && arr[i]>=arr[aDeque.peekLast()]) {
                aDeque.pollLast();
            }
            aDeque.offerLast(i);
        }
        aList.add(arr[aDeque.peekFirst()]);
        return aList;
    }
    public static void printDeque(ArrayDeque<Integer> deq)
{
   for (Integer integer : deq) {
       System.out.print(integer + " ");
   }
   System.out.println();
}
public static void left_Rotate_Deq_ByK(ArrayDeque<Integer> deque, int n, int k)
{
    
    for (int i = 0; i < k; i++) {
        int val = deque.pollFirst();
        deque.offerLast(val);
    }
}

//Function to rotate deque by k places in clockwise direction.
public static void right_Rotate_Deq_ByK(ArrayDeque<Integer> deque, int n, int k)
{
    for (int i = 0; i < k; i++) {
        int val = deque.pollLast();
        deque.offerFirst(val);
    }
}
public static void eraseAt(ArrayDeque<Integer> deq , int X)
{
    ArrayDeque<Integer> dq = new ArrayDeque<>();
    for (int i = 0; i < X; i++) {
        dq.offer(deq.pollFirst());
    }
    deq.pollFirst();
    while (!dq.isEmpty()) {
        deq.offerFirst(dq.pollLast());
    }
}

//Function to erase the elements in range start (inclusive), end (exclusive).
public static void eraseInRange(ArrayDeque<Integer> deq , int start, int end)
{
    //Your code here
    ArrayDeque<Integer> dq = new ArrayDeque<>();
    for (int i = 0; i < start; i++) {
        dq.offer(deq.pollFirst());
    }
    for (int i = start; i < end; i++) {
        deq.pollFirst();
    }
    while (!dq.isEmpty()) {
        deq.offerFirst(dq.pollLast());
    }
}

//Function to erase all the elements in the deque.
public static void eraseAll(ArrayDeque<Integer> deq )
{
    deq.clear();
}
    // ArrayList<Integer> aList = new ArrayList<>();
    //     ArrayDeque<Integer> aDeque = new ArrayDeque<>();
    //     for (int i = 0; i < k; i++) {
    //         while (!aDeque.isEmpty() && arr[i]>=arr[aDeque.peekLast()]) {
    //             aDeque.removeLast();
    //         }
    //         aDeque.addLast(i);
    //     }
    //     for (int i = k; i < n; i++) {
    //         aList.add(arr[aDeque.peek()]);
    //         while (!aDeque.isEmpty() && aDeque.peek()<=i-k ) {
    //             aDeque.removeFirst();
    //         }
    //         while ((!aDeque.isEmpty()) && arr[i] >= arr[aDeque.peekLast()])
    //             aDeque.removeLast();
    //         aDeque.addLast(i);
    //     }
    //     aList.add(arr[aDeque.peek()]);
    //     return aList;
}
