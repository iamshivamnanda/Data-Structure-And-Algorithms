package DynamicP;

import java.time.Year;
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

    public long findNthFibonacci(int number, long dp[])
    {
        if(dp[number]!= 0){
            return dp[number];
        }
        if(number == 0||number==1||number==2){
            return dp[number];
        }
       long res = findNthFibonacci(number-1,dp)+ findNthFibonacci(number-2,dp);
        dp[number] = res;
        return dp[number];
    }
    public static long[] printFibb(int n) 
    {
        long res[] =new long[n];
        if(n==1){
            res[0]=1;
            return res;
        }
        res[0]=1;res[1]=1;
        for (int i = 2; i <n ; i++) {
            res[i]=res[i-1]+res[i-2];
        }
        return res;
    }

    static long nthFibonacci(long number){
        // code here
        long res[] = new long[(int)number];
        res[0] =1; 
        res[1]= 1;
        for (int i = 2; i <number; i++) {
            res[i] = (res[i-1] + res[i-2] )%1000000007;
        }
        return res[(int)number-1];
    }

    static int lcs(int x, int y, String s1, String s2)
    {
        // your code here
        int memo[][] = new int[x+1][y+1];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                memo[i][j] =-1;
            }
        }
        return lcsUtil( x,  y,  s1,  s2,memo);
    }

    static int lcsUtil(int x, int y, String s1, String s2,int[][] memo){
        if(memo[x][y] != -1){
            return memo[x][y];
        }
        if(x==0||y==0){
            memo[x][y] = 0;
        }else{        
        if(s1.charAt(x-1) == s2.charAt(y-1)){
            memo[x][y] = 1+lcsUtil(x-1, y-1, s1, s2, memo);
        }
        else{
            memo[x][y] =  Math.max(lcsUtil(x-1, y, s1, s2, memo), lcsUtil(x, y-1, s1, s2, memo));

        }
    }
    return  memo[x][y];
    }

    //tabular Solution
    static int lcsTabular(int x, int y, String s1, String s2)
    {
        int memo[][] = new int[x+1][y+1];
        
        for (int i = 1; i <=x; i++) {
            for (int j = 1; j <=y; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    memo[i][j] = 1+memo[i-1][j-1];
                }else{
                    memo[i][j] = Math.max(memo[i-1][j],memo[i][j-1]);
                }
            }
        }
        return memo[x][y];
    }
    public static int shortestCommonSupersequence(String s1,String s2,int x,int y)
    {
        //Your code here
         int memo[][] = new int[x+1][y+1];
        
        for (int i = 1; i <=x; i++) {
            for (int j = 1; j <=y; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    memo[i][j] = 1+memo[i-1][j-1];
                }else{
                    memo[i][j] = Math.max(memo[i-1][j],memo[i][j-1]);
                }
            }
        }
        return x+y -memo[x][y];
    }
    public static void main(String[] args) {
        System.out.println(findNthFibonacci(7));
    }
}
