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
                
}
