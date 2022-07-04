package Stack;

// import java.util.*;
import java.util.*;

public class SDESHEET {
    static boolean isparutil(char c) {
        if (c == '[' || c == '{' || c == '(') {
            return true;
        }
        return false;
    }

    static char isparutil2(char c) {
        switch (c) {
            case ']':
                return '[';
            case '}':
                return '{';
            case ')':
                return '(';
            default:
                return '*';
        }
    }

    static boolean ispar(String x) {
        ArrayDeque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);

            if (isparutil(c)) {
                st.push(c);
            } else {
                if (!st.isEmpty() && isparutil2(c) == st.peek()) {
                    st.pop();
                } else {
                    st.push(c);
                }
            }
        }

        if (!st.isEmpty())
            return false;

        return true;
    }

    // next permutation leetcode

    public static void nextPermutation(int[] nums) {
        int mark = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                mark = i - 1;
                break;
            }
        }
        if (mark == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int index = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            System.out.println(nums[i]);
            if (nums[i] > nums[mark]) {
                index = i;
                break;
            }
        }

        System.out.println("MARK " + mark + "  INDEX  " + index);
        if (index != -1) {
            swap(nums, index, mark);
            reverse(nums, mark + 1, nums.length - 1);
        }

    }

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse(int arr[], int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public static int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>((a, b) -> b - a);

        for (Integer integer : nums) {
            pQueue.add(integer);
        }
        int i = 1;
        while (--k > 0) {
            pQueue.poll();
        }
        return pQueue.peek();

    }

    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i + 1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                res -= map.get(s.charAt(i));
                continue;
            }
            res += map.get(s.charAt(i));

        }
        return res;
    }

    public int[] repeatedNumber(final int[] A) {
        int arr[] = new int[A.length];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i : A) {
            if (arr[i - 1] == 1) {
                res.add(i);
            }
            arr[i - 1]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                res.add(i + 1);
            }
        }
        int RESULT[] = res.stream().mapToInt(Integer::intValue).toArray();
        return RESULT;
    }

    public int trap(int[] arr) {
        int n = arr.length;
        int leftMax[] = new int[n];
        int rightMax[] = new int[n];

        int max = arr[0];
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i]);
            leftMax[i] = max;
        }

        max = arr[n - 1];
        rightMax[n - 1] = max;

        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(max, arr[i]);
            rightMax[i] = max;
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            res += min - arr[i];
        }

        return res;

    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int l[] = new int[n];
        int r[] = new int[n];

        l[0] = 1;
        for (int i = 1; i < n; i++) {
            l[i] = nums[i - 1] * l[i - 1];
        }

        r[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            r[i] = nums[i + 1] * r[i + 1];
        }

        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = l[i] * r[i];
        }
        return res;
    }

    public static int findMin(int[] nums) {
        int pivot = pivot(nums, 0, nums.length - 1);

        if (pivot == -1) {
            return nums[0];
        }

        return nums[pivot];
    }

    public static int pivot(int[] nums, int low, int high) {
        if (low >= high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if ((mid < nums.length - 1) && nums[mid] > nums[mid + 1])
            return mid + 1;
        if (mid > 0 && nums[mid] < nums[mid - 1])
            return mid;
        if (nums[mid] < nums[0])
            return pivot(nums, low, mid - 1);

        return pivot(nums, mid + 1, high);
    }

    public static int search(int[] nums, int target) {
        int pivot = pivot(nums, 0, nums.length - 1);

        if (pivot == -1)
            return binarySearch(nums, 0, nums.length - 1, target);

        int res = binarySearch(nums, 0, pivot - 1, target);
        if (res != -1)
            return res;
        return binarySearch(nums, pivot, nums.length - 1, target);

    }

    public static int binarySearch(int nums[], int low, int high, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                return binarySearch(nums, low, mid - 1, target);
            return binarySearch(nums, mid + 1, high, target);
        }
        return -1;
    }

    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int max_prod = nums[0];
        int min_prod = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = Math.max(Math.max(nums[i], nums[i] * max_prod), nums[i] * min_prod);
            min_prod = Math.min(Math.min(nums[i], nums[i] * max_prod), nums[i] * min_prod);
            max_prod = temp;
            max = Math.max(max, max_prod);
        }
        return max;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hMap.put(nums[i], i);
        }

        System.out.println(hMap.toString());
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int val = nums[i] + nums[j];
                if (hMap.containsKey(0 - val)) {
                    int k = hMap.get(0 - val);
                    if (i != j && i != k && j != k) {
                        Boolean contains = false;
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        for (List<Integer> li : res) {
                            if (li.containsAll(list)) {
                                contains = true;
                                break;
                            }
                        }
                        if (!contains) {
                            res.add(list);
                        }
                    }
                }
            }
        }

        return res;
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int low = 0, high = height.length - 1;
        while (low < high) {
            int h = Math.min(height[low], height[high]);
            int area = h * (high - low);
            maxArea = Math.max(area, maxArea);

            if (height[low] <= height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        // nextPermutation(nums);
        // int res = maxProfit(nums);
        // int res = findKthLargest(nums, 4);
        // int res[] = productExceptSelf(nums);
        // int res = findMin(nums);
        // int res = search(nums, 3);
        // List<List<Integer>> list = threeSum(nums);
        // System.out.println(list.toString());
        // System.out.println("RESULT " + res);
        System.out.println(maxArea(nums));
        // System.out.println(Arrays.toString(res));
    }
}
