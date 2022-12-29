package Heap;

import java.util.*;
import java.util.Map.Entry;

import LinkedList.LinkedListSDEsheet.ListNode;

public class SDEHEAP {

    public static void print(Object o) {
        System.out.println(o);
    }

    // Function to build a Heap from array.
    void buildHeap(int arr[], int n) {
        for (int i = n / 2; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }

    }

    // Heapify function to maintain heap property.
    void heapify(int arr[], int n, int i) {
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        int largerIndex = i;
        if (leftIndex < n && arr[leftIndex] > arr[largerIndex])
            largerIndex = leftIndex;
        if (rightIndex < n && arr[rightIndex] > arr[largerIndex])
            largerIndex = rightIndex;

        if (largerIndex != i) {
            // swap
            int temp = arr[i];
            arr[i] = arr[largerIndex];
            arr[largerIndex] = temp;

            // recursively call for rest
            heapify(arr, n, largerIndex);
        }

    }

    // Function to sort an array using Heap Sort.
    public void heapSort(int arr[], int n) {
        // code here
        buildHeap(arr, n);
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // put all elements to map
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (Integer o1, Integer o2) -> map.get(o2) - map.get(o1));

        for (Integer num : map.keySet())
            priorityQueue.add(num);

        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }

        return res;

    }

    int[] kLargest(int[] arr, int n, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((Integer o1, Integer o2) -> o2 - o1);
        for (int i = 0; i < arr.length; i++)
            priorityQueue.add(arr[i]);

        int res[] = new int[k];
        for (int i = 0; i < res.length; i++)
            res[i] = priorityQueue.poll();

        return res;

    }

    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = l; i <= r; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = 1; i < k; i++)
            priorityQueue.poll();

        return priorityQueue.poll();
    }

    int maxRepeating(int[] arr, int n, int k) {
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for (int i : arr)
            hMap.put(i, hMap.getOrDefault(i, 0) + 1);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (Integer o1, Integer o2) -> hMap.get(o2) - hMap.get(o1));
        for (Integer integer : hMap.keySet()) {
            priorityQueue.add(integer);
        }

        return priorityQueue.peek();
    }

    // Function to find the next greater element for each element of the array.
    public static long[] nextLargerElement(long[] arr, int n) {
        ArrayDeque<Long> arrayDeque = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!arrayDeque.isEmpty() && arrayDeque.peek() <= arr[i]) {
                arrayDeque.pop();
            }
            long no = arr[i];
            if (arrayDeque.isEmpty()) {
                arr[i] = -1;
            } else {
                arr[i] = arrayDeque.peek();
            }
            arrayDeque.push(no);
        }
        return arr;
    }

    public static int findElement(int[] arr, int low, int high, int x) {
        if (arr[high] <= x) {
            return high;
        }

        if (arr[low] > x)
            return low;

        int mid = (low + high) / 2;

        if (arr[mid] <= x && arr[mid + 1] > x)
            return mid;

        if (arr[mid] < x)
            return findElement(arr, mid + 1, high, x);

        return findElement(arr, low, mid - 1, x);
    }

    // idea is to first use binary search to find the element or element closed to
    // it
    int[] printKClosest(int[] arr, int n, int k, int x) {
        int l = findElement(arr, 0, n - 1, x);
        int r = l + 1;

        if (arr[l] == x)
            l--;

        int count = 0;
        int res[] = new int[k];

        while (l >= 0 && r < n && count < k) {
            if (x - arr[l] < arr[r] - x) {
                res[count] = arr[l--];
            } else {
                res[count] = arr[r++];
            }

            count++;
        }

        while (count < k && l >= 0) {
            res[count] = arr[l--];
            count++;
        }

        while (count < k && r < n) {
            res[count] = arr[r++];
            count++;
        }

        return res;
    }

    static int[] kthLargest(int k, int[] arr, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int res[] = new int[n];

        for (int i = 0; i < n; i++) {
            if (minHeap.size() < k) {
                minHeap.add(arr[i]);
            } else {
                if (arr[i] > minHeap.peek()) {
                    minHeap.remove();
                    minHeap.add(arr[i]);
                }
            }

            if (minHeap.size() < k) {
                res[i] = -1;
            } else {
                res[i] = minHeap.peek();
            }
        }
        return res;
    }

    // Function to return the minimum cost of connecting the ropes.
    long minCost(long arr[], int n) {
        long res = 0;
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++)
            minHeap.add(arr[i]);

        while (minHeap.size() >= 2) {
            long temp = minHeap.poll() + minHeap.poll();
            // System.out.printf("%d + %d \n", a , b);
            res += temp;
            minHeap.add(temp);
        }

        return res;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        HashMap<String, String> reveMap = new HashMap<>();
        HashMap<String, String> ticketsmap = new HashMap<>();
        for (List<String> list : tickets) {
            reveMap.put(list.get(1), list.get(0));
            ticketsmap.put(list.get(0), list.get(1));
        }
        String start = null;
        for (List<String> list : tickets) {
            if (!reveMap.containsKey(list.get(0))) {
                start = list.get(0);
                break;
            }
        }
        while (start != null) {
            res.add(start);
            start = ticketsmap.get(start);
        }
        return res;

    }

    int maxLen(int arr[], int n) {
        HashMap<Integer, Integer> sums = new HashMap<>();
        int max_len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum == 0) {
                max_len = Math.max(max_len, i + 1);
            } else if (sums.containsKey(sum)) {
                max_len = Math.max(max_len, i - sums.get(sum));
            } else {
                sums.put(sum, i);
            }
        }

        return max_len;
    }

    ArrayList<Integer> countDistinct(int A[], int n, int k) {
        // key -> no , val -> count
        HashMap<Integer, Integer> map = new HashMap<>();

        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        res.add(map.size());

        int start = 0;
        for (int i = k; i < A.length; i++) {
            if (map.get(A[start]) == 1) {
                map.remove(A[start]);
            } else {
                map.put(A[start], map.get(A[start]) - 1);
            }
            start++;
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);

            res.add(map.size());
        }
        return res;
    }

    public static String getDifString(String str) {
        StringBuilder shift = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            int dif = str.charAt(i) - str.charAt(i - 1);
            if (dif < 0)
                dif += 26;

            shift.append(dif + 'a');
        }
        return shift.toString();
    }

    public static void groupShiftedString(String str[], int n) {
        HashMap<String, ArrayList<Integer>> groupStrings = new HashMap<>();

        for (int i = 0; i < str.length; i++) {
            String difString = getDifString(str[i]);
            if (groupStrings.containsKey(difString)) {
                groupStrings.get(difString).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                groupStrings.put(difString, list);
            }
        }

        for (Entry<String, ArrayList<Integer>> mapEntry : groupStrings.entrySet()) {
            ArrayList<Integer> list = mapEntry.getValue();
            for (Integer integer : list) {
                System.out.print(str[integer] + " ");
            }
            System.out.println();
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }
        }
        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
        }

        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        int interval = 1;
        while (interval < n) {
            for (int i = 0; i < n - interval; i = i + interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval = interval * 2;
        }
        return n > 0 ? lists[0] : null;
    }

    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (Integer o1, Integer o2) -> arr[o2] - arr[o1]);

        for (int i = 0; i < k; i++) {
            maxHeap.add(i);
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.add(arr[maxHeap.peek()]);
        int start = 0;
        for (int i = k; i < arr.length; i++) {
            maxHeap.remove(start++);
            maxHeap.add(i);
            res.add(arr[maxHeap.peek()]);
        }
        return res;
    }

    // invalid solutiuon
    int[] findSurpasser2(int[] arr, int n) {
        int res[] = new int[n];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (Integer o1, Integer o2) -> arr[o2] - arr[o1]);
        maxHeap.add(n - 1);
        res[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int count = 0;
            if (!maxHeap.isEmpty() && arr[i] < arr[maxHeap.peek()]) {
                System.out.println(maxHeap.toString());
                while (!maxHeap.isEmpty() && arr[maxHeap.peek()] > arr[i]) {
                    count++;
                    maxHeap.poll();
                }
            }
            if (arr[i] < arr[i + 1]) {
                count += res[i + 1];
            }
            res[i] = count;
            maxHeap.add(i);
        }

        return res;

    }

    public static int segregeatePositiveNo(int arr[]) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 0) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
        return j;
    }

    public static int findMissingPosNo(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int x = Math.abs(arr[i]);

            if (arr[x - 1] > 0 && x - 1 < arr.length) {
                arr[x - 1] = -arr[x - 1];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                return i + 1;
            }
        }

        return arr.length + 1;
    }

    // Function to find the smallest positive number missing from the array.
    static int missingNumber(int arr[], int size) {
        int shift = segregeatePositiveNo(arr);
        int arr2[] = new int[size - shift];
        int j = 0;
        for (int i = shift; i < arr.length; i++) {
            arr2[j++] = arr[i];
        }

        return findMissingPosNo(arr2);
    }

    // public static void merge(int arr[], int l, int m, int h, HashMap<Integer,
    // Integer> hMap) {
    // int left[] = Arrays.copyOfRange(arr, l, m + 1);
    // int right[] = Arrays.copyOfRange(arr, m + 1, h + 1);

    // int i = 0;
    // int j = 0;
    // int k = l;
    // int count = 0;

    // while (i < left.length && j < right.length) {
    // if (left[i] <= right[i]) {
    // hMap.put(left[i], hMap.getOrDefault(left[i], 0) + count);
    // arr[k++] = left[i++];
    // } else {
    // arr[k++] = right[j++];
    // count++;
    // }
    // }

    // while (i < left.length) {
    // hMap.put(left[i], count);
    // arr[k++] = left[i++];
    // }

    // while (j < right.length) {
    // arr[k++] = right[i++];
    // }

    // }

    // public static void mergeSort(int arr[], int l, int h, HashMap<Integer,
    // Integer> hMap) {
    // if (l < h) {
    // int mid = l + (h - l) / 2;
    // mergeSort(arr, l, mid, hMap);
    // mergeSort(arr, mid + 1, h, hMap);
    // merge(arr, l, mid, h, hMap);
    // }
    // }

    // int[] findSurpasser(int[] arr, int n) {
    // HashMap<Integer, Integer> hMap = new HashMap<>();
    // int dup[] = Arrays.copyOf(arr, n);
    // mergeSort(arr, 0, n - 1, hMap);

    // for (int i = 0; i < dup.length; i++) {
    // dup[i] = n - i - hMap.getOrDefault(arr[i], 0);
    // }
    // return dup;

    // }

    public static void merge(int arr[], int l, int m, int h, HashMap<Integer, Integer> hMap, int ans[]) {
        int left[] = Arrays.copyOfRange(arr, l, m + 1);
        int right[] = Arrays.copyOfRange(arr, m + 1, h + 1);

        int i = 0;
        int j = 0;
        int k = l;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                ans[hMap.get(left[i])] += right.length - j;
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }

    }

    public static void mergeSort(int arr[], int l, int h, HashMap<Integer, Integer> hMap, int ans[]) {
        if (l < h) {
            int mid = l + (h - l) / 2;
            mergeSort(arr, l, mid, hMap, ans);
            mergeSort(arr, mid + 1, h, hMap, ans);
            merge(arr, l, mid, h, hMap, ans);
        }
    }

    int[] findSurpasser(int[] arr, int n) {
        HashMap<Integer, Integer> hMap = new HashMap<>();
        int dup[] = Arrays.copyOf(arr, n);
        int ans[] = new int[n];
        for (int i = 0; i < dup.length; i++) {
            hMap.put(arr[i], i);
        }
        mergeSort(arr, 0, n - 1, hMap, ans);

        return ans;

    }

    public static void checkPalindromes(String str) {
        if (str.length() == 0)
            return;

        System.out.println(str.charAt(0) + " Yes ");
        if (str.length() == 1)
            return;

        int q = 103;
        int d = 256;
        int h = 1;
        int j;
        int firstH = str.charAt(0) % q;
        int secondH = str.charAt(1) % q;

        for (int i = 1; i < str.length(); i++) {
            if (firstH == secondH) {
                for (j = 0; j < i / 2; j++) {
                    if (str.charAt(j) != str.charAt(i - j)) {
                        break;
                    }
                }
                System.out.println(str.charAt(i) + ((j == i / 2) ? " Yes " : " No "));
            } else {
                System.out.println(str.charAt(i) + " No ");
            }
            if (i != str.length() - 1) {
                // if it is even
                if (i % 2 == 0) {
                    // then need to add char after first half at beginig
                    h = (h * d) % q;
                    firstH = (firstH + h * str.charAt(i / 2)) % q;

                    // append chart at second
                    secondH = (secondH * d + str.charAt(i + 1)) % q;
                } else {
                    secondH = (d * (secondH + q - str.charAt((i + 1) / 2) * h) % q + str.charAt(i + 1)) % q;
                }
            }
        }
    }

    public static int largestSubArray2(int arr[]) {
        int n = arr.length;
        if (n == 0)
            return 0;
        Arrays.sort(arr);
        int maxCount = 1;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] + 1 == arr[i]) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 1;
            }
        }

        return maxCount;

    }

    public static int largestSubArray(int arr[]) {
        int maxlen = 1;

        for (int i = 0; i < arr.length - 1; i++) {
            HashSet<Integer> hSet = new HashSet<>();
            hSet.add(arr[i]);
            int min = arr[i], max = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (hSet.contains(arr[j]))
                    break;

                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                hSet.add(arr[j]);

                if ((max - min) == (j - i)) {
                    maxlen = Math.max(maxlen, j - i + 1);
                }
            }
        }
        return maxlen;

        

    }

    public static void main(String[] args) {
        // // print(12);
        // String str[] = { "acd", "dfg", "wyz", "yab", "mop",
        // "bdfh", "a", "x", "moqs"
        // };
        // groupShiftedString(str, str.length);

        // String txt = "aabaacaabaa";
        // checkPalindromes(txt);

        int arr[] = { 10, 12, 12, 10, 10, 11, 10 };
        System.out.println("Length of the longest contiguous subarray is "
                + largestSubArray(arr));
    }
}

class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        this.maxHeap.add(-1 * num);
        this.minHeap.add(-1 * this.maxHeap.poll());

        if (this.minHeap.size() > this.maxHeap.size())
            this.maxHeap.add(-1 * this.minHeap.poll());

    }

    public double findMedian() {
        if (this.minHeap.size() != this.maxHeap.size()) {
            return -1 * this.maxHeap.peek();
        } else {
            // substracting as max heap contains elements multiple of -1
            return (this.minHeap.peek() - this.maxHeap.peek()) / 2.00;
        }
    }
}