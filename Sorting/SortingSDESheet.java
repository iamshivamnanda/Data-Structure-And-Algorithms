package Sorting;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class SortingSDESheet {
    public boolean isPossible(long a[], long b[], int n, long k) {
        Arrays.sort(a);
        Arrays.sort(b);
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            if (a[i] + b[j--] >= k)
                continue;
            return false;
        }

        return true;
    }

    ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) {
        ArrayList<Integer> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2 && k < n3) {
            if (A[i] == B[j] && B[j] == C[k]) {
                if (!set.contains(A[i])) {
                    set.add(A[i]);
                    res.add(A[i]);
                }
                i++;
                j++;
                k++;
            } else if (A[i] < B[j]) {
                i++;
            } else if (B[j] < C[k]) {
                j++;
            } else {
                k++;
            }
        }
        return res;
    }

    public static String countSort(String arr) {
        int[] count = new int[26];

        // count arr
        for (int i = 0; i < arr.length(); i++) {
            count[arr.charAt(i) - 97]++;
        }

        // modified the count arr base on the position of prev elment so that we get the
        // position of element in sorted array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // traverse through original arr and place the result in new arr of same length
        char res[] = new char[arr.length()];
        for (int i = arr.length() - 1; i >= 0; i--) {
            int index = --count[arr.charAt(i) - 97];
            res[index] = arr.charAt(i);
        }

        return new String(res);
    }

    // searching in an element where adjacent elemnnt diff
    public static int search(int arr[], int n, int x, int k) {
        int i = 0;
        while (i < n) {
            if (arr[i] == x)
                return i;

            i += Math.max(1, Math.abs(x - arr[i]) / k);
        }
        return -1;
    }

    static int majorityElement(int a[], int size) {
        int maj_index = 0, count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[maj_index] == a[i]) {
                count++;
            } else {
                count--;
            }

            if (count <= 0) {
                maj_index = i;
                count = 1;
            }
        }
        count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == a[maj_index]) {
                count++;
            }
        }

        return (count > size / 2) ? a[maj_index] : -1;
    }

    public boolean findPair(int arr[], int size, int n) {
        HashMap<Integer, Integer> hMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            hMap.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (hMap.containsKey(n - arr[i])) {
                int j = hMap.get(n - arr[i]);
                if (i != j)
                    return true;
            }
        }
        return false;
    }

    // find floor in arr
    static int findFloor(long arr[], int n, long x) {
        int low = 0;
        int high = n - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low) + (high - low) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    long countTriplets(long arr[], int n, int sum) {
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] >= sum) {
                    k--;
                } else {
                    ans += k - j;
                    j++;
                }
            }
        }
        return ans;
    }

    public int FindMaxSum(int arr[], int n) {
        int[][] dp = new int[n][2];
        // 0 not inclued 1 inlcued

        dp[0][0] = 0;
        dp[0][1] = arr[0];

        for (int i = 1; i < n; i++) {
            // exclued will be max of last inclued or not inclued
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // inclued will be last not inclued but inclued now as two adjacent cannot be
            // added
            dp[i][1] = dp[i - 1][0] + arr[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // merge two sorted arrays without extra space
    public static void merge(long arr1[], long arr2[], int n, int m) {
        for (int i = m - 1; i >= 0; i--) {
            long last = arr1[n - 1];
            int j;
            for (j = n - 2; j >= 0 && arr1[j] > arr2[i]; j--) {
                arr1[j + 1] = arr1[j];
            }

            if (last > arr2[i]) {
                arr1[j + 1] = arr2[i];
                arr2[i] = last;
            }

        }
        return;
    }
}
