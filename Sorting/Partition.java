package Sorting;

import java.util.Arrays;

public class Partition {
    public static void naive_partition(int arr[], int l, int h, int p) {
        int[] temp = new int[h - l + 1];
        int index = 0;
        for (int i = l; i <= h; i++) {
            if (arr[i] <= arr[p] && i != p) {
                temp[index] = arr[i];
                index++;
            }
        }
        temp[index++] = arr[p];
        for (int i = l; i <= h; i++) {
            if (arr[i] > arr[p]) {
                temp[index] = arr[i];
                index++;
            }
        }
        for (int i = l; i <= h; i++) {
            arr[i] = temp[i - l];
        }
    }

    public static void lomuto_partition(int arr[], int l, int h, int p) {
        int i = -1;
        int temp = arr[p];
        arr[p] = arr[h];
        arr[h] = temp;
        int j;
        for (j = l; j <= h - 1; j++) {
            if (arr[j] < arr[h]) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[j];
        arr[j] = temp;
    }

    public static int hoare_partition(int arr[], int l, int h) {
        int pivot = arr[l];
        int i = l - 1;
        int j = h + 1;
        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i >= j) {
                return j;
            }
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        // int arr[] = new int[]{5,13,6,9,12,11,8};

        // int n = arr.length;
        // lomuto_partition(arr,0,n-1,4);
        int arr[] = new int[] { 5, 3, 8, 4, 2, 7, 1, 10 };

        int n = arr.length;
        System.out.println( hoare_partition(arr, 0, n - 1));
        System.out.println(Arrays.toString(arr));
    }
}
