package arrays;

import java.util.Arrays;

public class BasicArray {
    static int binarySearch(int[] arr, int start, int length, int key) {
        if (start <= length) {
            int midIndex = (start + length) / 2;
            if (arr[midIndex] == key) {
                return midIndex;
            } else if (arr[midIndex] > key) {
                return binarySearch(arr, start, midIndex - 1, key);
            } else {
                return binarySearch(arr, midIndex + 1, length, key);
            }

        } else {
            return -1;
        }
    }

    static int insertSorted(int[] arr, int n, int capacity, int key) {
        if (n >= capacity) {
            return n;
        } else {
            int i;
            for (i = n - 1; (i >= 0 && arr[i] > key); i--) {
                arr[i + 1] = arr[i];
            }
            arr[i + 1] = key;
            return n + 1;
        }
    }

    static int deleteSorted(int[] arr, int n, int key) {
        int pos = binarySearch(arr, 0, n - 1, key);
        if (pos == -1) {
            return n;
        } else {
            for (int i = pos; i < n; i++) {
                arr[i] = arr[i + 1];
            }
            return n - 1;
        }
    }

    static int mean(int N , int[] A) {
        // code here
        int sum = 0;
        for(int i=0;i<N;i++){
            sum += A[i];
        }
        int mean = sum/N;
        return mean;
    }
    public int find_median(int[] v)
    {
        Arrays.sort(v);
        int size = (int) Math.floor(v.length /2);
        if(v.length %2 ==0){
            int sizeel = v[size];
            int size2el = v[size -1];
            int median = (sizeel + size2el)/2;
            return median;
        }
      return v[size];
    }

    public static void main(String[] args) {
        // int[] myarr = {1,2,3,5,6,9,10,23,26,41,32,144};

        // int index = binarySearch(myarr, 0, myarr.length - 1, 144);
        // System.out.println(index);
        // System.out.println(myarr[index]);

        int[] myarr2 = new int[30];
        myarr2[0] = 10;
        myarr2[1] = 20;
        myarr2[2] = 30;
        myarr2[3] = 50;
        myarr2[4] = 70;
        myarr2[5] = 90;

        int n = 6;
        int cap = myarr2.length;
        // System.out.println("\nBefore Insertion");
        // for (int i = 0; i < n; i++) {
        // System.err.print(myarr2[i] + " ");
        // }

        // n = insertSorted(myarr2, n, cap, 40);

        // System.out.println("\nAfter Insertion");
        // for (int i = 0; i < n; i++) {
        // System.err.print(myarr2[i] + " ");
        // }

        System.out.println("\nBefore Deletation");
        for (int i = 0; i < n; i++) {
            System.err.print(myarr2[i] + " ");
        }

        n = deleteSorted(myarr2, n, 50);

        System.out.println("\nAfter Deletation");
        for (int i = 0; i < n; i++) {
            System.err.print(myarr2[i] + " ");
        }

    }
}
