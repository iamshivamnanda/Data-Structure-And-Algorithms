package HACKERRANK;
import java.util.*;
import java.text.*;

public class basics {
    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int primary = 0;
            int secondary = 0;

            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < arr.get(i).size(); j++) {
                    if(i==j){
                        primary += arr.get(i).get(j);
                    }
                    if(i == arr.size()-j-1){
                        secondary += arr.get(i).get(j);
                    }
                }
            }
            return Math.abs(primary-secondary);
        }
        public static void staircase(int n) {
            // Write your code here
                for (int i = 1; i <=n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if((i+j)>n){
                            System.out.print("#");
                        }else{
                            System.out.print(" ");
                        }
                    }
                    System.out.println();;
                }
            }

            public static String timeConversion(String s) {
                // Write your code here
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ssaa");
                    DateFormat format = new SimpleDateFormat("HH:mm:ss");
                    Date time = null;
                    String out = "";
                    try {
                        time = dateFormat.parse(s);
                        out = format.format(time);
                    } catch (Exception e) {
                        System.out.println(e);
                        
                    }
                    return out;
                }
                public static List<Integer> gradingStudents(List<Integer> grades) {
                    // Write your code here
                    List<Integer> res = new ArrayList<Integer>();
                    for (Integer grade : grades) {
                        int g = grade;
                        if(grade>38){ 
                            int nextmul =5;
                            while (nextmul<grade) {
                                nextmul = nextmul+5;
                            }
                            System.out.println(nextmul);
                            if(nextmul-grade<3){
                                g = nextmul;
                            }
                        }
                        res.add(g);
                    }
                    return res;
                    }
                    public static long getWays(int value, List<Long> c) {
                        // Write your code here
                        int numberOfCoins = c.size();
                        long[][] dp= new long[value+1][numberOfCoins+1];
        for (int i = 0; i <=value; i++) {
            dp[i][0] =0;
        } 
        for (int i = 0; i <=numberOfCoins; i++) {
            dp[0][i] =1;
        } 
        for (int i = 1; i <= value; i++) {
            for (int j = 1; j <= numberOfCoins; j++) {
                dp[i][j] = dp[i][j-1];
                if(c.get(j-1)<=i){
                    dp[i][j] += dp[(int) (i-c.get(j-1))][j];
                }
            }
        }
        return dp[value][numberOfCoins];
        }


        // Dynamic Array 
        public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
            // Write your code here
            List<Integer> res = new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < n; i++) {
                arr.add(new ArrayList<Integer>());
            }

            int lastAnswer = 0;
            for (List<Integer> list : queries) {
                int type = list.get(0);
                int x = list.get(1);
                int y = list.get(2);
                if(type == 1){
                    int idx = ((x^lastAnswer)%n);
                    arr.get(idx).add(y);
                }else{
                    int idx = ((x^lastAnswer)%n);
                    lastAnswer = arr.get(idx).get(y%arr.get(idx).size());
                    res.add(lastAnswer);
                }
            }
            return res;
            }
            //leftRotate
            public static List<Integer> rotateLeft(int d, List<Integer> arr) {
                List<Integer> res = new ArrayList<Integer>();

                for (int i = d; i < arr.size(); i++) {
                    res.add(arr.get(i));
                }
            for (int i = 0; i < d; i++) {
                res.add(arr.get(i));
            }
            return res;
                }


            public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
                   List<Integer> res = new ArrayList<Integer>();
                    for (String query : queries) {
                        int match = 0;
                        for (String string : strings) {
                            if(string.equals(query)){
                                match++;
                            }
                        }
                        res.add(match);
                    }
                   return res;
                    }

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
    // Write your code here
        long arr[] = new long[n];
        long max =0;
        for (List<Integer> list : queries) {
            int a = list.get(0);
            int b = list.get(1);
            int k = list.get(2);
            for (int i = a; i <=b && i<n; i++) {
                arr[i-1] += k;
                if(arr[i-1]>max){
                    max = arr[i-1];
                }
            }
        }
        return max;
    }
   
}
