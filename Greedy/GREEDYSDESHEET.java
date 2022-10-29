package Greedy;

import java.util.*;

public class GREEDYSDESHEET {
    static int coins[] = { 2000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };

    static List<Integer> minPartition(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= N) {
                int count = (int) Math.floor(N / coins[i]);
                for (int j = 0; j < count; j++) {
                    res.add(coins[i]);
                }
                N -= count * coins[i];
            }
        }
        return res;

    }

    public static long minSum(int arr[], int n) {
        Arrays.sort(arr);
        String val1 = "";
        String val2 = "";

        int index = 0;
        while (index < n - 1) {
            val1 += arr[index++];
            val2 += arr[index++];
        }
        if (index < n) {
            val1 += arr[index];
        }

        val1 = "0" + val1;
        val2 = "0" + val2;
        return Long.valueOf(val1) + Long.valueOf(val2);

    }

    long findMinSum(int[] A, int[] B, int N) {
        Arrays.sort(A);
        Arrays.sort(B);
        long res = 0;
        for (int i = 0; i < N; i++) {
            res += Math.abs(A[i] - B[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[] = { 0 };
        System.out.println(minSum(arr, arr.length));
    }
}
