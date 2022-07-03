package Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
public class Geeks
{
    Stack<Integer> st = new Stack<Integer>();
    //Function to push an element into the stack.
    public static void insert(Stack<Integer> st, int x)
    {
        st.add(x);
    }
    
    //Function to remove top element from stack.
    public static void remove(Stack<Integer> st)
    {
        st.pop();
    }
    
    //Function to print the top element of stack.
    public static void headOf_Stack(Stack<Integer> st)
    {
        System.out.println(st.peek());
    }
    
    //Function to search an element in the stack.
    public static boolean find(Stack<Integer> st, int val)
    {
        // Your code here
       return  st.contains(val);
    }
    public static String removeConsecutiveDuplicates(String str)
    {
        ArrayDeque<Character> arrayDeque = new ArrayDeque<Character>();
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(arrayDeque.contains(c)){
                continue;
            }else{
                sBuilder.append(c);
                arrayDeque.clear();
                arrayDeque.add(c);
            }
        }
        return sBuilder.toString();
    }
    static boolean matching(char a,char b){
        return (a=='(' && b==')') ||(a=='{' && b=='}') ||(a=='[' && b==']') ;
    }
    static boolean ispar(String x)
    {
        ArrayDeque<Character> arrayDeque = new ArrayDeque<Character>();
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if(c == '(' ||c == '{' ||c == '['){
                arrayDeque.push(c);
            }else{
                if(arrayDeque.isEmpty()){
                    return false;
                }
                else if(matching(arrayDeque.peek(), c) == false){
                    return false;
                }else{
                arrayDeque.pop();
                }
            }
        }
        return arrayDeque.isEmpty();
    }
    public static int[] calculateSpan(int price[], int n)
    {
        int arr[] = new int[n];
        ArrayDeque<Integer> sArrayDeque = new ArrayDeque<Integer>();
        sArrayDeque.push(0);
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            while (!sArrayDeque.isEmpty() && price[sArrayDeque.peek()] <=price[i]) {
                sArrayDeque.pop();
            }
            int res = sArrayDeque.isEmpty() ?i+ 1: i -sArrayDeque.peek() ;
            arr[i] = res;
            sArrayDeque.push(i);
        }
        return arr;
    }
    public static long[] nextLargerElement(long[] arr, int n)
    { 
        long longarr[] = new long[n];
        ArrayDeque<Long> s = new ArrayDeque<Long>();
        longarr[n-1] = -1;
        s.push(arr[n-1]);

        for (int i = n-2; i >=0; i--) {
            while (!s.isEmpty() && s.peek() <= arr[i]) {
                s.pop();
            }
            Long res = s.isEmpty() ? -1 : s.peek();
            longarr[i] = res;
            s.push(arr[i]);
        }
        
        return longarr;
    } 
      public static long getMaxArea(long hist[], long n) 
    {
        ArrayDeque<Long> el = new ArrayDeque<Long>();
        long res = Integer.MIN_VALUE;
        for (int i = 0; i < hist.length; i++) {
            while (el.isEmpty() == false && hist[el.peek().intValue()]>=hist[i]) {
                Long top = el.pop();
                Long curr = hist[top.intValue()] * (el.isEmpty()?i:(i-el.peek()-1)); 
                res = Math.max(curr, res);
            }
            el.push(Long.valueOf(i));
        }
    
    while(el.isEmpty() == false){
        Long top = el.pop();
                Long curr = hist[top.intValue()] * (el.isEmpty()?n:(n-el.peek()-1)); 
                res = Math.max(curr, res);
    }
    return res;
}
public static long maxrect(long arr[][]){
    long res = getMaxArea(arr[0], arr[0].length);
    for (int i = 1; i < arr.length; i++) {
        for (int j = 0; j < arr[i].length; j++) {
            if(arr[i][j]==1){
                arr[i][j] += arr[i-1][j];
            }
        }
        res = Math.max(res, getMaxArea(arr[i], arr[i].length));
    }
    return res;
}
public static Stack<Integer> _push(int arr[],int n)
    {
        Stack<Integer> s = new Stack<Integer>();
        s.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] <=s.peek()){
                s.push(arr[i]);
            }else{
                s.push(s.peek());
            }
        }
        return s;
    }
    
    //Function to print minimum value in stack each time while popping.
    static void _getMinAtPop(Stack<Integer>s)
    {
        while(s.empty()!=true){
            System.out.print(s.pop() + " ");
        }
    }
    public void deleteMid(Stack<Integer>s,int sizeOfStack){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < sizeOfStack/2; i++) {
            arrayList.add(s.pop());
        }
        s.pop();
        for (int i = arrayList.size()-1; i >=0 ; i--) {
            s.push(arrayList.get(i));
        }
    } 
    
    public static String hackerrankInString(String s) {
        // Write your code here
        String ha ="hackerrank"; 
      
        int index =0;

        for (int i = 0; i < s.length(); i++) {
            if(index<ha.length() && s.charAt(i) == ha.charAt(index)){
                index++;
            }
        }
        return (index == ha.length())? "YES":"NO";
        
    
    }
    public static String pangrams(String str) {
        boolean[] visited = new boolean[26];
        String s = str.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if(Character.isAlphabetic(s.charAt(i))){
                visited[s.charAt(i)-97] = true;
            }
        }
        for (boolean b : visited) {
            if(b==false){
                return "not pangram";
            }
        }
        return "pangram";

    }
    public static List<String> weightedUniformStrings(String str, List<Integer> queries) {
        // Write your code here
        HashSet<Integer> hashSet = new HashSet<>();
        String s = str.toLowerCase();
        int res = 0;
        for (int i = 0; i < s.length();) {
            if(Character.isAlphabetic(s.charAt(i))){
                res = s.charAt(i)-96;
                hashSet.add(res);
                int j =i+1;
               while(j<s.length() && s.charAt(i) == s.charAt(j)){
                   res += s.charAt(j)-96;
                   hashSet.add(res);
                   j++;
               }
               i = j;
            }
        }
               

        ArrayList<String> list = new ArrayList<>();
        for (Integer integer : queries) {
            if(hashSet.contains(integer)){
                list.add("Yes");
            }else{
                list.add("No");
            }
        }
        return list;
        }
}


