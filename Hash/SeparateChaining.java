package Hash;

import java.util.ArrayList;


public class SeparateChaining{

public static ArrayList<ArrayList<Integer>> separateChaining(int arr[], int n, int hashSize)
{
    ArrayList<ArrayList<Integer>> aList =  new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < hashSize; i++) {
        ArrayList<Integer> aIntegers = new ArrayList<Integer>();
        aList.add(aIntegers);
    }
    for (int val : arr) {
        int Hash = val %hashSize;
        ArrayList<Integer> arrayList = aList.get(Hash);
        arrayList.add(val);
    }

    return aList;
}
public static void main(String[] args) {
    int arr[] = {92,4,14,24,44,91};

    System.out.println("WORKING...");
    ArrayList<ArrayList<Integer>> aLists =  separateChaining(arr,6,10);
    System.out.println(aLists.toString());
}
}