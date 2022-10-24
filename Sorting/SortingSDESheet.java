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

    static long inversionCount(long arr[], long N) {
        return inversionCounttUtil(arr, N, 0, N - 1);
    }

    public static long inversionCounttUtil(long arr[], long N, long low, long high) {
        long count = 0;
        if (low < high) {
            long mid = (low + high) / 2;
            count += inversionCounttUtil(arr, count, low, mid);
            count += inversionCounttUtil(arr, count, mid + 1, high);
            count += inversionCountMerge(arr, low, mid, high);
        }
        return count;
    }

    public static long inversionCountMerge(long arr[], long low, long mid, long high) {
        long left[] = Arrays.copyOfRange(arr, (int) low, (int) mid + 1);
        long right[] = Arrays.copyOfRange(arr, (int) mid + 1, (int) high + 1);
        int i = 0;
        int j = 0;
        int k = (int) low;
        long swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                swaps += (mid + 1) - (low + i);
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }

        return swaps;
    }

    public static ArrayList<Integer> duplicates(int arr[], int n) {
        HashMap<Integer, Integer> set = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();

        for (int integer : arr) {
            if (set.containsKey(integer) && set.get(integer) == 1) {
                res.add(integer);
            }
            set.put(integer, set.getOrDefault(integer, 0) + 1);
        }

        if (res.size() == 0) {
            res.add(-1);
        }
        Collections.sort(res);
        return res;
    }

    public static long[] productExceptSelf(int nums[], int n) {
        long left[] = new long[n];
        long right[] = new long[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            left[i] = left[i] * right[i];
        }

        return left;
    }

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int res = 0;
        while (start <= end) {
            res += nums[end] - nums[start];
            start++;
            end--;
        }
        return res;
    }

    public static boolean canBeEqual(int[] target, int[] arr) {
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            System.out.println(target[i]);
            hMap.put(target[i], hMap.getOrDefault(target[i], 0) + 1);
        }
        System.out.println(hMap.toString());
        for (int i = 0; i < arr.length; i++) {
            if (!hMap.containsKey(arr[i])) {
                return false;
            }
            int value = hMap.get(arr[i]);
            if (value == 1) {
                hMap.remove(arr[i]);
            } else {
                hMap.put(arr[i], value - 1);
            }
        }
        System.out.println(hMap.size());
        return (hMap.size() == 0) ? true : false;
    }

    public static void main(String[] args) {
        int target[] = { 1, 1, 1, 1, 1 };
        int arr[] = { 1, 1, 1, 1, 1 };
        System.out.println(canBeEqual(target, arr));
    }

    public static double median(int arr[], int n) {
        if (n == 0) {
            return -1;
        }
        if (n % 2 == 0) {
            return (double) (arr[n / 2] + arr[n / 2 + 1]) / 2.00;
        }
        return arr[n / 2];
    }

    public static double MO2(int a, int b) {
        return (double) (a + b) / 2.00;
    }

    public static double MO3(int a, int b, int c) {
        return a + b + c - Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c));
    }

    static double MO4(int a, int b, int c, int d) {
        int Max = Math.max(a, Math.max(b, Math.max(c, d)));
        int Min = Math.min(a, Math.min(b, Math.min(c, d)));
        return (double) ((a + b + c + d - Max - Min) / 2.0);
    }

    static double medianOfArrays(int n, int m, int a[], int b[]) {
        if (n > m) {
            return medianOfArraysUtil(m, n, b, a);
        }
        return medianOfArraysUtil(n, m, a, b);
    }

    // a is a smaller array having length n , b is a larger array having length m
    public static double medianOfArraysUtil(int n, int m, int a[], int b[]) {
        // case: if smaller array is of len 0
        // return median of larger array
        if (n == 0)
            return median(b, m);
        // case: if smaller array is of len 1 (n == 1)
        if (n == 1) {
            // case if larger array is also of len 1 then return median of these two number
            if (m == 1)
                return MO2(a[0], b[0]);

            // case if larger array is of size odd i.e m >=3 then res will be med of middle
            // element of m , m-1, m-2, a[0]
            if (m % 2 == 1)
                MO2(b[m / 2], (int) MO3(a[0], b[m / 2 - 1], b[m / 2 + 1]));

            // case three if larger arrray is of len even i.e m >= 2 then med will be med of
            // larger array and a[0]
            return MO3(a[0], b[n / 2], b[n / 2 - 1]);
        } else if (n == 2) {
            // if larger size is also of len 2 then med will be a[0], a[1], b[1], b[2]
            if (m == 2)
                return MO4(a[0], a[1], b[0], b[1]);

            // if larger arr is of size odd i.e m >=3 then med will be
            // med of b[m/2] , MAx of a[0] , b[n/2 -1] , MIN a[1] , b[n/2 + 1]
            if (m % 2 == 1)
                return MO3(b[m / 2], Math.max(a[0], b[n / 2 - 1]), Math.min(a[1], b[n / 2 + 1]));

            // if m is even then median will be
            return MO4(b[m / 2], b[m / 2 - 1], Math.max(a[0], b[m / 2 - 2]), Math.min(a[1], b[m / 2 + 1]));
        }
        int indA = (n - 1) / 2;
        int indB = (m - 1) / 2;

        // if indA <= indB then median lies between a[indA....] & b[....indeB]
        if (a[indA] <= b[indB])
            return medianOfArraysUtil(n / 2 + 1, m - indA, Arrays.copyOfRange(a, indA, a.length), b);

        return medianOfArraysUtil(n / 2 + 1, m - indA, a, Arrays.copyOfRange(b, indB, b.length));
    }

    public static long findSubarray(long[] arr, int n) {
        long res = 0;
        long sum = 0;
        HashMap<Long, ArrayList<Integer>> hMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum == 0)
                res++;

            ArrayList<Integer> al = new ArrayList<>();
            if (hMap.containsKey(sum)) {
                al = hMap.get(sum);
                res += al.size();
            }
            al.add(i);
            hMap.put(sum, al);
        }
        return res;
    }
}
