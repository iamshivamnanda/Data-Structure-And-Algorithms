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

    public static int activitySelection(int start[], int end[], int n) {
        ArrayList<Activity> activities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Activity activity = new Activity(start[i], end[i]);
            activities.add(activity);
        }

        Collections.sort(activities, new Comparator<Activity>() {

            @Override
            public int compare(Activity o1, Activity o2) {
                if (o1.end > o2.end) {
                    return 1;
                } else if (o1.end == o2.end) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        int resCount = 1;
        int i = 0;
        for (int j = 1; j < n; j++) {
            if (activities.get(j).start > activities.get(i).end) {
                resCount++;
                i = j;
            }
        }
        return resCount;
    }

    // Returns maximum number of pyramidcal
    // levels n boxes of given widths.
    static int maxLevel(int[] boxes, int n) {

        // Sort objects in increasing order
        // of widths
        Arrays.sort(boxes);

        int ans = 1; // Initialize result

        // Total width of previous level
        // and total number of objects in
        // previous level
        int prev_width = boxes[0];
        int prev_count = 1;

        // Number of object in current
        // level.
        int curr_count = 0;

        // Width of current level.
        int curr_width = 0;
        for (int i = 1; i < n; i++) {
            // Picking the object. So
            // increase current width
            // and number of object.
            curr_width += boxes[i];
            curr_count += 1;

            // If current width and
            // number of object
            // are greater than previous.
            if (curr_width > prev_width &&
                    curr_count > prev_count) {

                // Update previous width,
                // number of object on
                // previous level.
                prev_width = curr_width;
                prev_count = curr_count;

                // Reset width of current
                // level, number of object
                // on current level.
                curr_count = 0;
                curr_width = 0;

                // Increment number of
                // level.
                ans++;
            }
        }

        return ans;
    }

    // find maximum sum
    public static int maxEqualSum(int N1, int N2, int N3, int[] S1, int[] S2, int[] S3) {
        // code here
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        // sum1
        for (int i : S1)
            sum1 += i;
        for (int i : S2)
            sum2 += i;
        for (int i : S3)
            sum3 += i;

        int i = 0, j = 0, k = 0;

        while (sum1 > 0 && sum2 > 0 && sum3 > 0) {
            if (sum1 == sum2 && sum2 == sum3) {
                return sum1;
            } else if (sum1 >= sum2 && sum1 >= sum3) {
                sum1 -= S1[i++];
            } else if (sum2 >= sum1 && sum2 >= sum3) {
                sum2 -= S2[j++];
            } else if (sum3 >= sum1 && sum3 >= sum2) {
                sum3 -= S3[k++];
            }
        }

        return 0;
    }

    static int[] JobScheduling(Job arr[], int n) {
        Arrays.sort(arr, (job1, job2) -> job2.profit - job1.profit);
        boolean taken[] = new boolean[n];
        int count = 0;
        int profit = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = Math.min(n - 1, arr[i].deadline - 1); j >= 0; j--) {
                if (taken[j] == false) {
                    taken[j] = true;
                    count++;
                    profit += arr[i].profit;
                    break;
                }
            }
        }
        int res[] = { count, profit };
        return res;
    }

    public static void egyptianFractionUtil(int num, int den, ArrayList<Integer> output) {
        if (num == 0)
        return;
        int newDen = (int) Math.ceil(1.0 * den / num);
        output.add(newDen);
        egyptianFractionUtil(num * newDen - den, newDen * den, output);
        
    }
    
    // Egpytian Fraction
    public static String egyptianFraction(int num, int den) {
        if (num % den == 0)
        return "";
        ArrayList<Integer> output = new ArrayList<>();
        egyptianFractionUtil(num, den, output);
        String str = "";
        for (int i = 0; i < output.size(); i++) {
            str += String.format("1/%d + ", output.get(i));
        }
        str = str.substring(0, str.length() - 3);
        return str;

    }

    public static void main(String[] args) {
        // int arr[] = { 0 };
        // System.out.println(minSum(arr, arr.length));

        System.out.println(egyptianFraction(12, 13));
    }

}

class Activity {
    int start;
    int end;

    public Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Activity [start=" + start + ", end=" + end + "]";
    }

}

class Job {
    int id, profit, deadline;

    Job(int x, int y, int z) {
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}