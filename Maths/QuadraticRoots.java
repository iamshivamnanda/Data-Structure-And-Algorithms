package Maths;

import java.util.ArrayList;

public class QuadraticRoots {
    static ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        ArrayList<Integer> roots = new ArrayList<Integer>();
       int D = b*b - 4*a*c;
       if(b*b < 4*a*c){
        roots.add(-1);
           
       }
       else if(b*b == 4*a*c){
           double root =  (-b + Math.sqrt(D))/(2*a);
           roots.add((int)Math.floor(root));
           roots.add((int)Math.floor(root));
       }
       else{
    
        double root1 =  (-b + Math.sqrt(D))/(2*a);
        double root2 =  (-b - Math.sqrt(D))/(2*a);
        if(root1 > root2){
            roots.add((int)Math.floor(root1));
            roots.add((int)Math.floor(root2));
        }else{
            roots.add((int)Math.floor(root2));
            roots.add((int)Math.floor(root1));
        }
       
       }

       return roots;
    }

    public static void main(String[] args) {
        System.out.println(quadraticRoots(-264,-750,540));
    }
}
