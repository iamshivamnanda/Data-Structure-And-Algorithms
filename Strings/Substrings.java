package Strings;

import java.util.Arrays;
import java.util.HashMap;

public class Substrings {
    public static boolean isRotated(String str1, String str2)
    {
        if(str2.length() < 3){
            if(str2.length() ==1){
                return str2.equals(str1);
            }
            return true;
        }
          String clockString = str2.substring(2).concat(str2.substring(0,2));
        int length = str2.length();
        String anticlockString = str2.substring(length-2,length).concat(str2.substring(0,length-2));

        if(clockString.equals(str1)){
            return true;
        }else if(anticlockString.equals(str1)){
            return true;
        }else{
            return false;
        }
    }
    public static boolean areRotations(String s1, String s2 )
    {
        if(s2.length() != s1.length()){
            return false;
        }
        String concatedString = s1.concat(s1);
     int index =   concatedString.indexOf(s2);
     if(index>=0){
         return true;
     }else{
         return false;
     }
    }

    public static boolean areIsomorphic(String str1,String str2)
    {
        if(str1.length() != str2.length()){
            return false;
        }
        char str1arr[] = new char[256];
        char str2arr[] = new char[256];
        for (int i = 0; i < str1.length(); i++) {
            if(str1arr[str1.charAt(i)] ==0){
                if(str2arr[str2.charAt(i)] !=0){
                    return false;
                }else{
                    str2arr[str2.charAt(i)] =  str1.charAt(i);
                    str1arr[str1.charAt(i)] = str2.charAt(i);
                }
            }else{
                if( str1arr[str1.charAt(i)] != str2.charAt(i) ){
                    return false;
                }

            }
        }

        return true;
    }
    static boolean isIsogram(String data){
        boolean arr[] = new boolean[256];
        for (int i = 0; i < data.length(); i++) {
            if(arr[data.charAt(i)]){
                return false;
            }else{
                arr[data.charAt(i)] = true;
            }
        }
        return true;
    }
    public static String printNumber(String s, int n) 
{
    String res = "";
    for (int i = 0; i < n; i++) {
        char ch = s.charAt(i);
       if(ch >='a' && ch<='c'){
          res =  res.concat("2");
       }else if(ch >='d' && ch<='f'){
        res =  res.concat("3");
     }else if(ch >='g' && ch<='i'){
        res =  res.concat("4");
     }else if(ch >='j' && ch<='l'){
        res =  res.concat("5");
     }else if(ch >='m' && ch<='o'){
        res =  res.concat("6");
     }else if(ch >='p' && ch<='s'){
        res =  res.concat("7");
     }else if(ch >='t' && ch<='v'){
        res =  res.concat("8");
     }else {
        res =  res.concat("9");
     }
    }
    return res;
}
static int repeatedCharacter(String S)
    {
        int barr[] = new int[256];
        Arrays.fill(barr,-1);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); i++) {
            if(barr[S.charAt(i)] != -1){
                res = Math.min(res, barr[S.charAt(i)]);
            }else{
                barr[S.charAt(i)] = i;;
            }
        }

        return res == Integer.MAX_VALUE ? -1 :res;
    }
    static char nonrepeatingCharacter(String S)
    {
        int arr[] = new int[256];
       for (int i = 0; i < S.length(); i++) {
           arr[S.charAt(i)]++;
       }
       for (int i = 0; i < S.length(); i++) {
           if(arr[S.charAt(i)] ==1){
               return S.charAt(i);
           }
       }
       return '$';
    }
    public static char getMaxOccuringChar(String line)
    {
        int arr[] = new int[256];
        for (int i = 0; i < line.length(); i++) {
            arr[line.charAt(i)]++;
        }
        int res = Integer.MIN_VALUE;
        char c = 0;
        for (int i = 0; i < line.length(); i++) {
            if(res == arr[line.charAt(i)]){
                c = (char) Math.min(c, line.charAt(i));
                res = arr[c];
            }else{
                if(res < arr[line.charAt(i)]){
                    res = arr[line.charAt(i)];
                    c = line.charAt(i);
                }
            }
        }
        return c;
    }
    public static String concatenatedString(String s1,String s2)
    {
        String s2Orginal  =   s2;
        int s1arr[] = new int[256];
        
        for (int i = 0; i < s1.length(); i++) {
            s1arr[s1.charAt(i)] = i;
        }

        for (int i = 0; i < s2.length(); i++) {
            if (s1arr[s2.charAt(i)] != 0 ){
                if(i==0){
                    s2 = s2.substring(1);

                }
                s2 = s2.substring(0, i).concat(s2.substring(i+1));
            }
        }
        s1arr = new int[256];
        
        for (int i = 0; i < s2Orginal.length(); i++) {
            s1arr[s2Orginal.charAt(i)] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (s1arr[s1.charAt(i)] != 0 ){
                if(i==0){
                    s1 = s1.substring(1);

                }
                s1 = s1.substring(0, i).concat(s1.substring(i+1));
            }
        }
        
        return s1.concat(s2);
    }
   
    static void reverse(char str[],int low, int high){
        while(low<=high){
            //swap
            char temp=str[low];
            str[low]=str[high];
            str[high]=temp;
            //
            low++;
            high--;
        }
        }

    String reverseWords(String S)
    {
       int i = 0;
       char[] str = S.toCharArray();
       for (int j = 0; j < S.length(); j++) {
           if(str[j] == '.'){
                reverse(str,i,j-1);
                i = j+1;
           }
       }
       reverse(str, i, S.length()-1);
       reverse(str, 0, S.length()-1);
       String res = new String(str);
       return res;
    }
    public static long findSum(String str)
    {
        int sum = 0;
        String temp = "0";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                System.out.println(ch);
                temp  = temp.concat(String.valueOf(ch));
                System.out.println(temp);
            }else{
                sum += Integer.parseInt(temp);
                temp = "0";
            }
        }
        sum += Integer.parseInt(temp);
        return sum;
    }
    public static boolean checkPangram  (String st) {
        boolean sarr[] = new boolean[26];
       String str = st.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if(Character.isAlphabetic(str.charAt(i))){
                sarr[str.charAt(i)-97] =true;
            }
        }
        for (boolean b : sarr) {
            if(b == false){
                return false;
            }
        }
       return true;
    }
    public static int minIndexChar(String str, String patt)
    {
       HashMap<Character,Integer> hMap = new HashMap<Character,Integer>();
       for (int i = 0; i < str.length(); i++) {
           if(!hMap.containsKey(str.charAt(i))){
               hMap.put(str.charAt(i), i);
           }
       }
       int res =Integer.MAX_VALUE;
       for (int i = 0; i < patt.length(); i++) {
           if(hMap.containsKey(patt.charAt(i))){
                res = Math.min(res, hMap.get(patt.charAt(i)));
           }
       }
       return res == Integer.MAX_VALUE ? -1: res;
    }
    public static void main(String[] args) {
        System.out.println(minIndexChar("hasjkhflaskdf", "sdlkjfshd"));
    }

}
