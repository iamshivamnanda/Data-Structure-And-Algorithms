package Heap;

import java.util.ArrayList;

import java.util.Collections;
import java.util.PriorityQueue;

class Triplet implements Comparable<Triplet>{
    int val,aPos,vPos;
    Triplet(int v,int ap, int vp){
        val=v;aPos=ap;vPos=vp;
    }
    public int compareTo(Triplet t){
        if(val<=t.val)return -1;
        else return 1;
    }
}
public class  MinHeap {
    int[] harr;
    int capacity ;
     int heap_size;
    MinHeap(int cap) {
        heap_size = 0;
        capacity = cap;
        harr = new int[cap];
    }
    int parent(int i) { return (i - 1) / 2; }
    int left(int i) { return (2 * i + 1); }
    int right(int i) { return (2 * i + 2); }

    //Function to extract minimum value in heap and then to store 
    //next minimum value at first index.

    
    int extractMin()
    {
        if(heap_size == 0){
            return -1;
        }
        if(heap_size == 1){
            heap_size--;
            return harr[0];
        }
        int temp = harr[0];
        harr[0] = harr[heap_size-1];
        harr[heap_size-1] = temp;
        heap_size--;
        MinHeapify(0);
        return harr[heap_size];
    }

    //Function to insert a value in Heap.
    void insertKey(int k) 
    {
        if(heap_size == capacity){
            return;
        }
        heap_size++;
        harr[heap_size-1] = k;
        for (int i = heap_size-1; i!=0 && (harr[i] < harr[parent(i)]); ) {
            int temp = harr[i];
            harr[i]=  harr[parent(i)];
            harr[parent(i)] = temp;
            i = parent(i);
        }

    }

    //Function to delete a key at ith index.
    void deleteKey(int i) 
    {
       
        if(i<heap_size){
            decreaseKey(i, Integer.MIN_VALUE);
            //extracting the minimum value from heap.
            extractMin();
        }
       
    }

    //Function to change value at ith index and store that value at first index.
    void decreaseKey(int i, int new_val) 
    {
        harr[i] = new_val;
        while (i != 0 && harr[parent(i)] > harr[i]) {
            int temp = harr[i];
            harr[i] = harr[parent(i)];
            harr[parent(i)] = temp;
            i = parent(i);
        }
    }

    /* You may call below MinHeapify function in
      above codes. Please do not delete this code
      if you are not writing your own MinHeapify */
    void MinHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && harr[l] < harr[i]) smallest = l;
        if (r < heap_size && harr[r] < harr[smallest]) smallest = r;
        if (smallest != i) {
            int temp = harr[i];
            harr[i] = harr[smallest];
            harr[smallest] = temp;
            MinHeapify(smallest);
        }
    }

    void buildHeap(int arr[], int n)
    {
        for (int i = n/2-1; i >=0; i--) {
            heapify(arr, n, i);
        }
    }
 
    //Heapify function to maintain heap property.
    void heapify(int harr[], int n, int i)
    {
        // Your code here
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;
        if (l < n && harr[l] > harr[i]) largest = l;
        if (r < n && harr[r] > harr[largest]) largest = r;
        if (largest != i) {
            int temp = harr[i];
            harr[i] = harr[largest];
            harr[largest] = temp;
            heapify(harr, n, largest);
        }
    }
    
    //Function to sort an array using Heap Sort.
    public void heapSort(int arr[], int n)
    {
       buildHeap(arr, n);
       for (int i = n-1; i >0; i--) {
           int temp = arr[0];
           arr[0] = arr[i];
           arr[i] = temp;
           heapify(arr, i, 0);
       }
    }
    public static ArrayList<Integer> mergeKArrays(int[][] arr,int K) 
    {
        ArrayList<Integer> res=new ArrayList<Integer>();
    PriorityQueue<Triplet> pq=new PriorityQueue<Triplet>();
      
    for(int i=0;i<arr.length;i++) 
        pq.add(new Triplet(arr[i][0],i,0));
        
    while(pq.isEmpty()==false){
        Triplet curr=pq.poll();
        res.add(curr.val);
        int ap=curr.aPos;int vp=curr.vPos;
        if(vp+1<arr[ap].length){
            pq.add(new Triplet(arr[ap][vp+1],ap,vp+1));
        }
    }

    return res;
        
        
    }
    public static ArrayList<Integer> kLargest(int arr[], int n, int k)
    {
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> mheap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            mheap.add(arr[i]);
        }
        
        for (int i = k; i < n; i++) {
            if(arr[i]>mheap.peek()){
                mheap.poll();
                mheap.add(arr[i]);
            }
        }
        int i =0;
        while (!mheap.isEmpty() && i<k) {
            list.add(mheap.poll());
            i++;
        }
        
        Collections.reverse(list);
        return list;
    }
    public static int kthSmallest(int arr[], int n, int k)
    {
        // Your code here
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> mheap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            mheap.add(arr[i]);
        }
        
        for (int i = k; i < n; i++) {
            if(arr[i]<mheap.peek()){
                mheap.poll();
                mheap.add(arr[i]);
            }
        }
        int i =0;
        while (!mheap.isEmpty() && i<k) {
            list.add(mheap.poll());
            i++;
        }
        
        Collections.reverse(list);
        return list.get(k-1);
    }
    long minCost(long arr[], int n) 
    {
        // your code here
        long sum = 0;
        PriorityQueue<Long> mheap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            mheap.add(arr[i]);
        }
        
        while (mheap.size() >1) {
             long a = mheap.poll();
             long b = mheap.poll();
            sum += a+b;
            mheap.add(a +b);
        }
        return sum;
    }
    ArrayList <Integer> nearlySorted(int arr[], int num, int k)
    {
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> mheap = new PriorityQueue<>();
        for (int i = 0; i < k+1; i++) {
            mheap.add(arr[i]);
        }
      
        for (int i = k+1; i < arr.length; i++) {
            list.add(mheap.poll());
            // arr[index++] = mheap.poll();
            mheap.add(arr[i]);
        }
        while (!mheap.isEmpty()) {
            list.add(mheap.poll());
        }
        return list;
    }

    public static void printMedians(int arr[],int n){
        PriorityQueue<Integer> g=new PriorityQueue<Integer>();
        PriorityQueue<Integer> s=new PriorityQueue<Integer>(Collections.reverseOrder());
        s.add(arr[0]);
        System.out.print(arr[0]+" ");
        for(int i=1;i<n;i++){
            int x=arr[i];
            if(s.size()>g.size())
            {
                if(s.peek()>x){
                    g.add(s.poll());
                    s.add(x);
                }else g.add(x);
                System.out.print(((double)(s.peek()+g.peek())/2)+" ");
            }else{
                if(x<=s.peek()){
                    s.add(x);
                }else{
                    g.add(x);
                    s.add(g.poll());
                }
                System.out.print(s.peek()+" ");
            }
        }
    }
}
