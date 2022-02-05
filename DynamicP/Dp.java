package DynamicP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Dp {

    // Bottom Up Approch
    static public long findNthFibonacci(int number) {
        int res[] = new int[number + 1];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i <= number; i++) {
            res[i] = res[i - 1] + res[i - 2];
            System.out.println(i + "  " + res[i]);
        }
        return res[number - 1];
    }

    public long findNthFibonacci(int number, long dp[]) {
        if (dp[number] != 0) {
            return dp[number];
        }
        if (number == 0 || number == 1 || number == 2) {
            return dp[number];
        }
        long res = findNthFibonacci(number - 1, dp) + findNthFibonacci(number - 2, dp);
        dp[number] = res;
        return dp[number];
    }

    public static long[] printFibb(int n) {
        long res[] = new long[n];
        if (n == 1) {
            res[0] = 1;
            return res;
        }
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res;
    }

    static long nthFibonacci(long number) {
        // code here
        long res[] = new long[(int) number];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < number; i++) {
            res[i] = (res[i - 1] + res[i - 2]) % 1000000007;
        }
        return res[(int) number - 1];
    }

    static int lcs(int x, int y, String s1, String s2) {
        // your code here
        int memo[][] = new int[x + 1][y + 1];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                memo[i][j] = -1;
            }
        }
        return lcsUtil(x, y, s1, s2, memo);
    }

    static int lcsUtil(int x, int y, String s1, String s2, int[][] memo) {
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        if (x == 0 || y == 0) {
            memo[x][y] = 0;
        } else {
            if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
                memo[x][y] = 1 + lcsUtil(x - 1, y - 1, s1, s2, memo);
            } else {
                memo[x][y] = Math.max(lcsUtil(x - 1, y, s1, s2, memo), lcsUtil(x, y - 1, s1, s2, memo));

            }
        }
        return memo[x][y];
    }

    // tabular Solution
    static int lcsTabular(int x, int y, String s1, String s2) {
        int memo[][] = new int[x + 1][y + 1];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        return memo[x][y];
    }

    public static int shortestCommonSupersequence(String s1, String s2, int x, int y) {
        // Your code here
        int memo[][] = new int[x + 1][y + 1];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        return x + y - memo[x][y];
    }

    public long minimumNumberOfCoins(int coins[], int numberOfCoins, int value) {
        long[][] dp = new long[numberOfCoins + 1][value + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE - 1);
        for (int i = 1; i <= numberOfCoins; i++) {
            for (int j = 1; j <= value; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return (dp[numberOfCoins][value] == Integer.MAX_VALUE - 1) ? -1 : dp[numberOfCoins][value];
    }

    public long numberOfWays(int coins[], int numberOfCoins, int value) {
        int[][] dp = new int[value + 1][numberOfCoins + 1];
        for (int i = 0; i <= value; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= numberOfCoins; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= value; i++) {
            for (int j = 1; j <= numberOfCoins; j++) {
                dp[i][j] = dp[i][j - 1];
                if (coins[j - 1] <= i) {
                    dp[i][j] += dp[i - coins[j - 1]][j];
                }
            }
        }
        return dp[value][numberOfCoins];
    }

    public int editDistance(String s, String t) {
        // Code here
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= t.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        int[] lcs = new int[arr.length];
        lcs[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            lcs[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    lcs[i] = Math.max(lcs[i], lcs[j] + 1);
                }
            }
        }
        int maxres = lcs[0];
        for (int i = 1; i < lcs.length; i++) {
            if (lcs[i] > maxres) {
                maxres = lcs[i];
            }
        }
        System.out.println("LIS  " + String.valueOf(maxres));
        return maxres;
    }

    public int minDeletions(int arr[], int n) {
        return n - longestIncreasingSubsequence(arr);
    }

    public int maxSumIS(int arr[], int n) {
        int[] maxSumLis = new int[n];
        maxSumLis[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxSumLis[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    maxSumLis[i] = Math.max(maxSumLis[i], arr[i] + maxSumLis[j]);
                }
            }
        }
        int max = maxSumLis[0];
        for (int k = 0; k < n; k++) {
            if (maxSumLis[k] > max) {
                max = maxSumLis[k];
            }
        }
        return max;
    }

    public int LongestBitonicSequence(int[] nums) {
        int[] lis = new int[nums.length];
        int[] lds = new int[nums.length];

        lis[0] = 1;
        for (int i = 1; i < lds.length; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int n = nums.length;
        lds[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            lds[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    lds[i] = Math.max(lds[i], 1 + lds[j]);
                }
            }
        }

        int max = lis[0] + lds[0] - 1;
        for (int i = 1; i < lds.length; i++) {
            if (max < lis[i] + lds[i] - 1) {
                max = lis[i] + lds[i] - 1;
            }
        }
        return max;
    }

    public static int maxSumBS(int nums[], int n) {
        int[] lis = new int[nums.length];
        int[] lds = new int[nums.length];

        lis[0] = nums[0];
        for (int i = 1; i < lds.length; i++) {
            lis[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + nums[i]);
                }
            }
        }

        lds[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            lds[i] = nums[i];
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    lds[i] = Math.max(lds[i], nums[i] + lds[j]);
                }
            }
        }

        int max = lis[0] + lds[0] - nums[0];
        for (int i = 1; i < lds.length; i++) {
            if (max < lis[i] + lds[i] - nums[i]) {
                max = lis[i] + lds[i] - nums[i];
            }
        }
        return max;
    }

    public int maximizeCuts(int n, int x, int y, int z) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = -1;
            if (i - x >= 0) {
                dp[i] = Math.max(dp[i], dp[i - x]);
            }
            if (i - y >= 0) {
                dp[i] = Math.max(dp[i], dp[i - y]);
            }
            if (i - z >= 0) {
                dp[i] = Math.max(dp[i], dp[i - z]);
            }
            if (dp[i] != -1) {
                dp[i]++;
            }
        }
        return dp[n];

    }

    static int minJumps(int[] arr) {
        // your code here
        int[] dp = new int[arr.length];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (arr[j] + j >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                    break;
                }
            }
        }
        return dp[arr.length - 1] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1];
    }

    // Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= W; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (wt[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                }
            }
        }
        return dp[n][W];
    }
    //Optimal Strategy For A Game
    static long countMaximum(int arr[], int n) {
        long[][] dp = new long[n][n];
        for (int i = 0; i < n-1; i++) {
            dp[i][i + 1] = Math.max(arr[i], arr[i + 1]);
        }
        for (int gap = 3; gap < n; gap += 2) {
            for (int i = 0; i + gap < n; i++) {
                int j = i + gap;
                dp[i][j] = Math.max(arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                        arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            int t = Integer.parseInt(line);
            for (int i = 0; i <= t; i++) {
                System.out.println(i);
            }
            // System.out.println(line);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(findNthFibonacci(7));

    }
}
