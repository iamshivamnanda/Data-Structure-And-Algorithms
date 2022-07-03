package arrays;

import java.util.Arrays;

public class RotatedArray {

    //rotate By using Temp array
    static void rotateArray(int[] arr,int n,int d){
        int temp[] = Arrays.copyOfRange(arr, 0, d);
        for (int i = 0; i < arr.length - d; i++) {
            arr[i] = arr[i+d];
        }
        int j = 0;
        for (int i = n-d; i < arr.length; i++) {
            arr[i] = temp[j];
            j++;
        }
    }

    //rotate By using oneRotation

    static void rotateArrayByOne(int[] arr,int n,int d){
        for (int i = 0; i < d; i++) {
            rotateByOne(arr, n);
        }
    }

    static void rotateByOne(int[] arr,int n){
        int temp = arr[0];
        for (int i = 0; i < n-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[n-1] =  temp;
    }

    static void rotateHalfArray(int[] arr,int n){
        int midIndex = n/2;
        int[] temp = Arrays.copyOfRange(arr, 0, midIndex);
        for (int i = 0; i < midIndex; i++) {
            arr[i] = arr[midIndex+i];
        }
        int j = 0;
        for (int i = midIndex; i < arr.length; i++) {
            arr[i] = temp[j];
            j++;
        }

        temp = null;
    }



    static void printArray(int[] arr,int n){
        for (int i = 0; i < n; i++) {
            System.err.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int[] myarr = {1,2,3,4,5,6};
        // System.out.println("Before Rotation");
        // printArray(myarr, myarr.length);

        // rotateArray(myarr, myarr.length, 3);
        // System.out.println("After Rotation");
        // printArray(myarr, myarr.length);


        // rotateArrayByOne(myarr, myarr.length, 2);
        // printArray(myarr, myarr.length);


        System.out.println("Rotate Array By Half");
        rotateHalfArray(myarr, myarr.length);
        printArray(myarr, myarr.length);
    }
}
