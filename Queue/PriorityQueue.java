package Queue;

import java.util.ArrayList;

public class PriorityQueue {
    /**
     * Heap
     */
    static class Heap {
    
        void heapify(ArrayList<Integer> arr,int i){
            int size = arr.size();
            int largest = i;
            int l = 2*i +1;
            int r = 2*i +2;
            if(l<size && arr.get(largest)<arr.get(l)){
                largest = l;
            }
            if(r<size && arr.get(largest)<arr.get(r)){
                largest = r;
            }
            if(largest !=i){
                int temp = arr.get(largest);
                arr.set(largest, arr.get(i));
                arr.set(i, temp);
                heapify(arr, largest);
            }
        }
    
        void insert(ArrayList<Integer> arr,int newNum){
            int size = arr.size();
            if(size == 0 ){
                arr.add(newNum);
            }else{
                arr.add(newNum);
                for (int i = size/2 -1; i >= 0; i--) {
                    heapify(arr, i);
                }
            }
        }

        void deleteNode(ArrayList<Integer> arr,int num){
            int size = arr.size();
            int i;
            for (i = 0; i < size; i++) {
                if(arr.get(i) == num){
                    break;
                }
            }
            int temp = arr.get(i);
            arr.set(i, arr.get(size-1));
            arr.set(size-1, temp);
            arr.remove(size -1);

            for (int j = size/2 -1; j >=0; j--) {
                heapify(arr, j);
            }
        }
    
        void printQueue(ArrayList<Integer> arr){
            for (Integer i : arr) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }

        
    
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> mArrayList = new ArrayList<Integer>();

        Heap h = new Heap();
        h.insert(mArrayList, 3);
        h.insert(mArrayList, 6);
        h.insert(mArrayList, 9);
        h.insert(mArrayList, 5);

        System.out.println("Max-Heap Array");
        h.printQueue(mArrayList);

        h.deleteNode(mArrayList, 6);        
        System.out.println("After Deletion");
        h.printQueue(mArrayList);

    }
}
