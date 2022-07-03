package Recursion;

public class basic {
    static void printTillN(int N) {
        if (N == 1) {
            System.out.print(N + " ");
            return;
        } else {
            printTillN(N - 1);
            System.out.print(N + " ");
        }
    }

    static int SumofDigits(int A, int B) {
        long sum = sumdigits(A);

        long rem = B % 6;
        if ((sum == 3 || sum == 6) && B > 1) {
            return 9;
        } else if (B == 1) {
            return (int) sum;
        } else if (B == 0) {
            return 1;
        } else if (rem == 0) {
            return sumdigits((int) Math.pow(sum, 6));
        } else {
            return sumdigits((int) Math.pow(sum, rem));
        }
    }

    static int sumdigits(int n) {
        if (n == 0)
            return 0;
        return (n % 9 == 0) ? 9 : (n % 9);
        // if(n<10){
        // return (int)n;
        // }else{
        // int sum = 0;
        // while(n!=0){
        // sum += n%10;
        // n = n/10;
        // }
        // // System.out.println("SUM" + sum);
        // return sumdigits(sum);
        // }
    }

    public long toh(int N, int from, int to, int aux) {
        // Your code here
        if (N == 1) {
            System.out.println("move disk 1 from rod " + from + " to rod " + to);
            return 1;
        } else {
            long f = toh(N - 1, from, aux, to);
            System.out.println("move disk " + N + " from rod " + from + " to rod " + to);
            long e = toh(N - 1, aux, to, from);
            return 1 + f + e;
        }
    }

    public static int counter = 2;

    public static boolean isLucky(int n) {
        if (counter > n) {
            return true;
        }
        if (n % counter == 0) {
            return false;
        }
        int next = n - (n / counter);
        counter++;
        return isLucky(next);
        // Your code here
    }

    static int knapSack(int W, int wt[], int val[], int n) {
        if (n == 0 || W == 0) {
            return 0;
        }
        if (wt[n - 1] > W) {
            return knapSack(W, wt, val, n - 1);
        } else {
            return Math.max(val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1), knapSack(W, wt, val, n - 1));
        }
    }

    // Optimal Strategy For Game SOl 1
    static int Optimal_Strategy_Util(int[] arr, int i, int j, int sum) {
        if (j == i + 1) {
            return Math.max(arr[i], arr[j]);
        }
        return Math.max(sum - Optimal_Strategy_Util(arr, i + 1, j, sum - arr[i]),
                sum - Optimal_Strategy_Util(arr, i, j - 1, sum - arr[j]));
    }

    static int main_Optimal_Strategy(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return Optimal_Strategy_Util(arr, 0, arr.length - 1, sum);
    }

    // Optimal Strategy For Game SOl 2
    static int Optimal_Strategy(int[] arr, int i, int j) {
        if (j == i + 1) {
            return Math.max(arr[i], arr[j]);
        }
        return Math.max(arr[i] + Math.min(Optimal_Strategy(arr, i + 2, j), Optimal_Strategy(arr, i + 1, j - 1)),
                arr[j] + Math.min(Optimal_Strategy(arr, i + 1, j - 1), Optimal_Strategy(arr, i, j - 2)));
    }

    public static void main(String[] args) {
        // // printTillN(10);
        // System.out.println(SumofDigits(256712,1046));
        // System.out.println(sumdigits(12345));
        System.out.println(isLucky(74));
    }
}
