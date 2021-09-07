package Sorting;

import java.util.Arrays;

public class MergeSort {
    public static void merge(int[] arr, int l, int mid, int r) {
        int i = 0;
        int j = 0;
        int k = 0;
        int n1 = mid - l + 1;
        int n2 = r - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];


        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];


        i=0;j=0;k=l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;k++;
            } else {
                arr[k] = R[j];
                j++; k++;
            }
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;k++;
        }
    }

    static public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 7, 8, 1 };
        mergeSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }
}
