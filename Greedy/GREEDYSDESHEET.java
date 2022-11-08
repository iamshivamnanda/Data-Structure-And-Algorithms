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

    // Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) {
        // Your code here
        Arrays.sort(arr, new Comparator<Item>() {
            @Override
            public int compare(Item item1,
                    Item item2) {
                double cpr1 = (double) item1.value / (double) item1.weight;
                double cpr2 = (double) item2.value / (double) item2.weight;

                if (cpr1 < cpr2)
                    return 1;
                else
                    return -1;
            }
        });
        double res = 0;
        for (int i = 0; i < n; i++) {
            if (W - arr[i].weight > 0) {
                res += arr[i].value;
                W -= arr[i].weight;
            } else {
                double fraction = ((double) W / (double) arr[i].weight);
                res += arr[i].value * fraction;
                break;
            }
        }
        return res;
    }

    int maxChainLength(Pair arr[], int n) {
        Arrays.sort(arr, (a, b) -> a.y - b.y);
        int res = 0;
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i].x > prev) {
                res++;
                prev = arr[i].y;
            }
        }
        return res;
    }

    static String smallestNumber(int S, int D) {
        if (S == 0) {
            return (D == 1) ? "0" : "-1";
        }
        if (S > 9 * D) {
            return "-1";
        }
        StringBuilder res = new StringBuilder();
        S -= 1;
        for (int i = D - 1; i > 0; i--) {
            if (S > 9) {
                res.insert(0, "9");
                S -= 9;
            } else {
                res.insert(0, S);
                S = 0;
            }
        }
        res.insert(0, String.valueOf(S + 1));
        return res.toString();
    }

    long maxSum(long arr[], int n) {
        long res = 0;
        Arrays.sort(arr);
        for (int i = 0; i < n / 2; i++) {
            res -= 2 * arr[i];
            res += 2 * arr[n - i - 1];
        }
        return res;
    }

    static List<Long> minimumSquares(long L, long B) {
        // considering B as smallest
        if (L < B) {
            long temp = L;
            L = B;
            B = temp;
        }

    }

    // lexigraphically snallest array
    public static void minSwapsLexiSmallest(int arr[], int n, int k) {

        for (int i = 0; i < n && k > 0; i++) {
            int pos = i;
            for (int j = i + 1; j < n; j++) {
                if (j - i > k) {
                    break;
                }
                if (arr[j] < arr[pos])
                    pos = j;
            }
            // swap numbers
            int temp;
            for (int j = pos; j > i; j--) {
                temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
            k -= pos - i;
        }
    }

    public static String rearrangeCharacters(String str) {

        int count[] = new int[256];
        PriorityQueue<Key> priorityQueue = new PriorityQueue<>(new Comparator<Key>() {

            @Override
            public int compare(Key o1, Key o2) {
                if (o1.freq < o2.freq) {
                    return 1;
                } else if (o1.freq == o2.freq) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }

        for (char i = 'a'; i <= 'z'; i++) {
            int val = i - 'a';
            if (count[val] > 0) {
                priorityQueue.add(new Key(count[val], i));
            }
        }
        StringBuilder res = new StringBuilder();
        Key prev = new Key(-1, '#');

        while (priorityQueue.size() != 0) {
            Key pq = priorityQueue.poll();
            res.append(pq.ch);

            if (prev.freq > 0) {
                priorityQueue.add(prev);
            }

            pq.freq--;
            prev = pq;
        }

        return (res.length() == str.length()) ? res.toString() : "-1";

    }

    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // dijkstra algorithm will be using hashmap+ queue for futher optimization
        boolean visited[] = new boolean[V];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        HashMap<Integer, Pair> hMap = new HashMap<>();

        int ans[] = new int[V];
        Arrays.fill(ans, Integer.MAX_VALUE);

        priorityQueue.add(new Pair(S, 0));
        ans[S] = 0;

        while (!priorityQueue.isEmpty()) {
            Pair cur = priorityQueue.remove();
            hMap.remove(cur.x);

            if (visited[cur.x]) {
                continue;
            }

            visited[cur.x] = true;
            ArrayList<ArrayList<Integer>> neg = adj.get(cur.x);

            for (ArrayList<Integer> arrayList : neg) {
                int vertex = arrayList.get(0);
                int wt = arrayList.get(1);

                if (ans[vertex] > ans[cur.x] + wt) {
                    ans[vertex] = ans[cur.x] + wt;
                    if (hMap.containsKey(vertex)) {
                        Pair pair = hMap.get(vertex);
                        pair.y = ans[vertex];
                    } else {
                        Pair newPair = new Pair(vertex, ans[vertex]);
                        priorityQueue.add(newPair);
                        hMap.put(vertex, newPair);
                    }
                }
            }
        }

        return ans;
    }

    public static int getMax(int amount[]) {
        int mxInx = 0;
        for (int i = 1; i < amount.length; i++) {
            if (amount[i] > amount[mxInx]) {
                mxInx = i;
            }
        }
        return mxInx;
    }

    public static int getMin(int amount[]) {
        int minInx = 0;
        for (int i = 1; i < amount.length; i++) {
            if (amount[i] < amount[minInx]) {
                minInx = i;
            }
        }
        return minInx;
    }

    public static void minCashFlowRec(int amount[], ArrayList<ArrayList<Integer>> res) {
        // get the max crediter and debitor
        int maxCreditor = getMax(amount);
        int maxDebitor = getMax(amount);

        if (amount[maxCreditor] == 0 && amount[maxDebitor] == 0) {
            return;
        }

        // get the minimum of both creditor and debitor
        int am = Math.min(amount[maxCreditor], -amount[maxDebitor]);

        amount[maxCreditor] -= am;
        amount[maxDebitor] += am;
        res.get(maxDebitor).set(maxCreditor, am);

        minCashFlowRec(amount, res);
    }

    // get the min cash flow amoung freinds/people
    public static ArrayList<ArrayList<Integer>> minCashFlow(int n, int[][] g) {
        // store the net amount for the person
        int amount[] = new int[n];

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < n; j++) {
                amount[i] += (g[j][i] - g[i][j]);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(0);
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>(list));
        }
        minCashFlowRec(amount, res);
        return res;

    }

    public static int minimumCostOfBreaking(int[] X, int[] Y, int M, int N) {
        int res = 0;

        X = Arrays.stream(X).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        Y = Arrays.stream(Y).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int i = 0, j = 0;
        int hor = 1, ver = 1;
        while (i < X.length && j < Y.length) {
            if(X[i] > Y[j]){
                res += X[i] * ver;
                hor++;
                i++;
            }else{
                res += Y[j] * hor;
                ver++;
                j++;
            }
        }
        int total = 0;
        while (i<X.length) {
            total = X[i++];
            res += total * ver;
        }

        while (j< Y.length) {
            total = Y[j++];
            res += total * hor;
        }

        return res;
    }

    public static void main(String[] args) {
        int arr[] = { 7, 6, 9, 2, 1 };
        minSwapsLexiSmallest(arr, arr.length, 3);
        System.out.println(Arrays.toString(arr));
        // System.out.println(minSum(arr, arr.length));

        // System.out.println(egyptianFraction(12, 13));
        // System.out.println(smallestNumber(9, 2));
    }

}

class Key {
    int freq;
    char ch;

    public Key(int freq, char ch) {
        this.freq = freq;
        this.ch = ch;
    }

}

class Pair implements Comparable<Pair> {
    int x;
    int y;

    public Pair(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public int compareTo(Pair that) {
        return this.y - that.y;
    }
}

class Item {
    int value, weight;

    Item(int x, int y) {
        this.value = x;
        this.weight = y;
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