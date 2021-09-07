package Sorting;

import java.util.Arrays;
import java.util.Comparator;

class Myclass implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        // TODO Auto-generated method stub
        return o1%2 - o2%2;
    }



}

public class Sorting {
    public static void main(String[] args) {
        Integer arr[] = {20,5,10,12,3};
        int arr2[] = {2,6,1,5,4,9};
        Arrays.sort(arr,new Myclass());
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr));
        System.out.println("\narr2");
        System.out.println(Arrays.toString(arr2));
        
    }
}
