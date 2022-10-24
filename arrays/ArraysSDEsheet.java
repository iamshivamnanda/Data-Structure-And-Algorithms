package arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.*;

class Query {
    int L;
    int R;

    Query(int L, int R) {
        this.L = L;
        this.R = R;
    }
}

public class ArraysSDEsheet {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for (int i = 0; i < res.length; i++) {
            hMap.put(nums[i], i);
        }

        for (int i = 0; i < res.length; i++) {
            if (hMap.containsKey(target - nums[i])) {
                int j = hMap.get(target - nums[i]);
                if (i != j) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public int[][] overlappedInterval(int[][] Intervals) {
        Arrays.sort(Intervals, (a, b) -> a[0] - b[0]);
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(Intervals[0]);
        for (int i = 1; i < Intervals.length; i++) {
            if (stack.peek()[1] >= Intervals[i][0]) {
                stack.peek()[1] = Intervals[i][1];
            } else {
                stack.push(Intervals[i]);
            }
        }

        int res[][] = new int[stack.size()][2];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[i] = stack.poll();
            i--;
        }
        return res;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashSet<Integer> hSet = new HashSet<>();
        for (int integer : nums) {
            if (hSet.contains(integer)) {
                res.add(integer);
                continue;
            }
            hSet.add(integer);
        }
        return res;
    }

    public static int longSubarrWthSumDivByK(int a[], int n, int k) {
        HashMap<Integer, Integer> hMap = new HashMap<>();

        int[] mod_arr = new int[n];

        int maxLen = 0;
        int currSum = 0;

        for (int i = 0; i < a.length; i++) {
            currSum += a[i];
            mod_arr[i] = ((currSum % k) + k) % k;

            if (mod_arr[i] == 0) {
                maxLen = i + 1;
            } else if (hMap.containsKey(mod_arr[i])) {

                if (maxLen < i - hMap.get(mod_arr[i]))
                    maxLen = i - hMap.get(mod_arr[i]);

            } else {
                hMap.put(mod_arr[i], i);
            }
        }
        return maxLen;
    }

    public static void queryResults(int a[], int n, ArrayList<Query> q, int m) {
        int block = (int) Math.sqrt(n);

        Collections.sort(q, new Comparator<Query>() {

            @Override
            public int compare(Query o1, Query o2) {
                if (o1.L / block != o2.L / block) {
                    return (o1.L < o2.L) ? -1 : 1;
                }

                return (o2.R < o2.R) ? -1 : 1;
            }

        });

        int currL = 0, currR = 0;
        int currSum = 0;

        for (int i = 0; i < m; i++) {
            int L = q.get(i).L;
            int R = q.get(i).R;

            while (currL < L) {
                currSum -= a[currL];
                currL++;
            }

            while (currL > L) {
                currSum += a[currL - 1];
                currL--;
            }

            while (currR <= R) {
                currSum += a[currR];
                currR++;
            }

            while (currR > R + 1) {
                currSum -= a[currR - 1];
                currR--;
            }

            System.out.printf("Sum Of Elements from range %d to %d  is %d\n", L, R, currSum);
        }

    }

    public static void setZeros(int matrix[][]) {
        int row[] = new int[matrix.length];
        int col[] = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = -1;
                    col[j] = -1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] == -1 || col[j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // kadane's algo
    public static long maxSubarraySum(int[] arr, int n) {
        int res = 0;
        int maxRes = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
            maxRes = Math.max(maxRes, res);
            if (res < 0) {
                res = 0;
            }
        }
        return maxRes;
    }

    public static void sort012(int[] arr) {
        int count[] = new int[3];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < count.length; j++) {
            while (count[j] > 0) {
                arr[i++] = j;
                count[j]--;
            }
        }
    }

    static double medianOfArrays(int n, int m, int a[], int b[]) {
        if (n > m)
            return medianOfArrays(m, n, b, a);

        int low = 0;
        int high = n;

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = (n + m + 1) / 2 - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];

            int r1 = cut1 == n ? Integer.MAX_VALUE : a[cut1];
            int r2 = cut2 == m ? Integer.MAX_VALUE : b[cut2];

            if (l1 <= r2 && l2 <= r1) {
                if ((n + m) % 2 == 0)
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.00;

                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high--;
            } else {
                low++;
            }
        }
        return 0.00;

    }

    public static void main(String[] args) {
        ArrayList<Query> q = new ArrayList<Query>();
        q.add(new Query(0, 4));
        q.add(new Query(1, 3));
        q.add(new Query(2, 4));

        int a[] = { 1, 1, 2, 1, 3, 4, 5, 2, 8 };
        queryResults(a, a.length, q, q.size());
    }
}

class Solution {
    static PriorityQueue<Integer> small = new PriorityQueue<>();
    static PriorityQueue<Integer> large = new PriorityQueue<>();

    // Function to insert heap.
    public static void insertHeap(int x) {
        small.add(-1 * x);
        large.add(-1 * small.poll());
        balanceHeaps();
    }

    // Function to balance heaps.
    public static void balanceHeaps() {
        if (large.size() > small.size()) {
            small.add(-1 * large.poll());
        }
    }

    // Function to return Median.
    public static double getMedian() {
        if (large.size() != small.size()) {
            return (double) -1 * small.peek();
        } else {
            return (large.peek() - small.peek()) / 2.00;
        }
    }

}