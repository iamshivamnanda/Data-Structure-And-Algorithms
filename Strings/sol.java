package Strings;
import java.util.*;

public class sol {
    ArrayList<Integer> search(String x, String s)
    {
       ArrayList<Integer> list = new  ArrayList<Integer>();
        /*
            the idea here is that we will slide through the window but rather than checking the all chars 
            of pattern directly we will first comapare the hash value of of the pattern and txt selcted
            if it matches we will compare and if it matches the we will add to res
            and slide the window next.
        */
        
        int p=0,t=0;
        //reprsent the char range
        int d = 256;
        //our hash function is (d*p + char)%q where q is a prime no which helps to avoid collitons in hashing
        int q = 101;
        int i,j;
        int h = (int) Math.pow(d,s.length());
        
        //compute the pattern hash val and first window val of string
        for(i=0;i<s.length();i++){
            p = (d*p + s.charAt(i))%q;
            t = (d*t + x.charAt(i))%q;
        }
        
        for(i=0;i<=x.length()-s.length();i++){
            if(p == t){
                for(j=0;j<s.length();i++){
                    if(x.charAt(i+j) != s.charAt(j)){
                        break;
                    }
                }
                if(j==s.length()){
                    list.add(i);
                }
            }
            if(i<x.length()-s.length()){
                t = (d*(t -x.charAt(i)*h) + x.charAt(i+s.length()))%q;
                if(t<0){
                    t =t+q;
                }
            }
        }
        
    
      return list;
    }
}
