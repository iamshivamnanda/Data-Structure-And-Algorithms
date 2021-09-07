package Heap;

import java.util.ArrayList;

public class Heap {
    ArrayList<Integer> mArrayList = new ArrayList<Integer>();

    void heapify(int i) {
        int size = mArrayList.size();
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if(l<size && (mArrayList.get(largest) < mArrayList.get(l))){
            largest = l;
        }
        if(r<size && (mArrayList.get(largest) < mArrayList.get(r))){
            largest = r;
        }
        if(largest != i){
            int temp = mArrayList.get(i);
            mArrayList.set(i, mArrayList.get(largest));
            mArrayList.set(largest, temp);

            heapify(largest);
        }
    }

    void insert(int value){
        if(mArrayList.size() == 0){
            mArrayList.add(value);
        }else{
            mArrayList.add(value);
            for (int i = mArrayList.size()/2 -1; i >=0; i--) {
                heapify(i);
            }
        }
    }

    void delete(int value){
        if(mArrayList.size() == 0){
            return ;
        }else{
            mArrayList.remove(value);
            for (int i = mArrayList.size()/2 -1; i >=0; i--) {
                heapify(i);
            }
        }
    }

    void printHeap(){
        for (Integer integer : mArrayList) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    int removePeek(){
        if(mArrayList.size() == 0){
            return -1;
        }
        int peek =  mArrayList.get(0);
       
        mArrayList.remove(0);
        for (int i = mArrayList.size()/2 -1; i >=0; i--) {
            heapify(i);
        }

        return peek;

    }
    int getPeek(){
        if(mArrayList.size() == 0){
            return -1;
        }
        int peek =  mArrayList.get(0);
        return peek;
    }

    public static void main(String[] args) {
        Heap mHeap = new Heap();
        mHeap.insert(10);
        mHeap.insert(30);
        mHeap.insert(54);
        mHeap.insert(62);
        mHeap.insert(45);

        
        System.out.println(mHeap.getPeek());
        mHeap.printHeap();
        System.out.println(mHeap.removePeek());
        mHeap.printHeap();
        System.out.println(mHeap.removePeek());
        mHeap.printHeap();
    }
}
