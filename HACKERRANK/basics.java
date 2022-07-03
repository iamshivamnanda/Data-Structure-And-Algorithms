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

    long arr[] = new long[n+2];
    for(List<Integer> lis : queries){
        int a = lis.get(0);
        int b = lis.get(1);
        int k = lis.get(2);
        arr[a] += k;
        arr[b+1] -= k;
    }
    long maxval =0;
    long intterval=0;
    for(long l:arr){
        intterval += l;
        if(intterval>maxval){
            maxval = intterval;
        }
    }
    
    
    return maxval;
    }

// watch minimum swaps video
public static void minswaps(List<Integer> q) {
HashMap<Integer,Integer> map = new HashMap<>();
for (int i = 0; i <q.size(); i++) {
    map.put(q.get(i), i);
}

q.sort(new Comparator<Integer>() {

    @Override
    public int compare(Integer o1, Integer o2) {

        if(o1>o2){
            return -1;
        }else if(o1 == o2){
            return 0;
        }else{
            return 1;
        }
    }
    
});

boolean visited[] = new boolean[q.size()];
Arrays.fill(visited, false);
int ans = 0;
for (int i = 0; i < q.size(); i++) {
    if(visited[i] || map.get(q.get(i)) == i){
        continue;
    }
    int j =i;
    int cycle_size = 0;
    while(!visited[j]){
        visited[j] = true;
        j = map.get(q.get(j));
        cycle_size++;
    }
    if(cycle_size>0){
        ans = (cycle_size -1);
    }
}
System.out.println(ans);
}

public static void minimumBribes(List<Integer> q) {
    // Write your code here
        int i=0;
        int bribes=0;
        int n = q.size();
        boolean chaoetic  = false;
        HashMap<Integer,Integer> map = new HashMap<>();
        while (i<n) {
            if(q.get(i) != i+1){
                int temp = q.get(i);
                q.set(i, q.get(i+1));
                q.set(i+1, temp);
                bribes++;
                if(map.containsKey(temp)){
                    int val = map.get(temp);
                    map.replace(temp, val+1);
                    if(val+1 >2){
                        System.out.println(map.toString());
                        chaoetic = true;
                        break;
                    }
                }else{
                    map.put(temp, 1);
                }
            }else{
                i++;
            }
        }
        if(chaoetic){
            System.out.println("Too chaotic");
        }else{
            System.out.println(bribes);
        }
    }

static int minimumSwaps(int[] arr) {
    int i =0;
    int count=0;
    int temp;
    int  n = arr.length;
    while(i<n){
        if(arr[i] != i+1){
            temp = arr[i];
            arr[i] = arr[temp-1];
            arr[temp-1]=temp;
            count++;
        }
        else{
            i++;
        }
    }
    return count;
}

    public static List<Integer> rotLeft(List<Integer> a, int d) {
        ArrayList<Integer> list  = new ArrayList<>();

        for (int i = d; i < a.size(); i++) {
            list.add(a.get(i));
        }

        for (int i = 0; i < d; i++) {
            list.add(a.get(i));
        }

        return list;
    
        }
   
}
