package Hash;

import java.util.HashMap;
import java.util.*;

public class NonRepeated {
    static long countNonRepeated(int arr[], int n)
    {
        HashMap<Integer,Integer> mHashMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < n; i++) {
            mHashMap.put(arr[i], mHashMap.getOrDefault(arr[i], 0)+1);
        }
        long l = 0L;
        for (Map.Entry<Integer,Integer> mEntry : mHashMap.entrySet()) {
            if((int)mEntry.getValue()==1){
                l++;
            }
        }
        return l;
    }
    static ArrayList<Integer> printNonRepeated(int arr[], int n)
    {
        // add your code here
        HashMap<Integer,Integer> mHashMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < n; i++) {
            mHashMap.put(arr[i], mHashMap.getOrDefault(arr[i], 0)+1);
        }
        ArrayList<Integer> aList = new ArrayList<Integer>();
       for(int i=0;i<n;i++){
           if((int)mHashMap.get(arr[i])==1){
            aList.add(arr[i]);
           }
       }
        return aList;
    }
    public static int firstRepeated(int []arr, int n) 
    {
        //Your code here
        HashMap<Integer,Integer> mHashMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < n; i++) {
            mHashMap.put(arr[i], mHashMap.getOrDefault(arr[i], 0)+1);
        }
       for(int i=0;i<n;i++){
           if((int)mHashMap.get(arr[i])!=1){
            return i+1;
           }
       }
        return -1;
    }
    public static int NumberofElementsInIntersection(int a[],int b[],int n,int m)
    {
        int count = 0;
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            hashSet.add(a[i]);
        }
        HashSet<Integer> BhashSet = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            BhashSet.add(b[i]);
        }
        for (Integer i:BhashSet) {
            if(hashSet.contains(i)){
                count++;
            }
        }
        return count;
    }
    public static int doUnion(int a[], int n, int b[], int m) 
    {
        //Your code here
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            hashSet.add(a[i]);
        }
        for (int i = 0; i < m; i++) {
            hashSet.add(b[i]);
        }
       
        return hashSet.size();
    }
    public static boolean check(long A[],long B[],int n)
    {
        //Your code here
        HashMap<Long,Integer> mHashMap = new HashMap<Long,Integer>();
        for (int i = 0; i < n; i++) {
            mHashMap.put(A[i], mHashMap.getOrDefault(A[i], 0)+1);
        }
        HashMap<Long,Integer> BHashMap = new HashMap<Long,Integer>();
        for (int i = 0; i < n; i++) {
            BHashMap.put(B[i], BHashMap.getOrDefault(B[i], 0)+1);
        }
        for (Map.Entry<Long,Integer> mEntry : mHashMap.entrySet()) {
            if(BHashMap.containsKey(mEntry.getKey())){
                if(BHashMap.get(mEntry.getKey()) != mEntry.getValue()){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }
    public static int sumExists(int arr[], int N, int sum)
    {
       HashSet<Integer> hashSet = new HashSet<Integer>();
       for (int i = 0; i < N; i++) {
           if(hashSet.contains(sum-arr[i])){
               return 1;
           }
           hashSet.add(arr[i]);
       }
       return 0;
    }
    static boolean findsum(int arr[],int n)
    {
        HashSet<Integer> set = new HashSet<Integer>();
        int prevsum = 0;
        set.add(0);
        for (int i = 0; i < n; i++) {
            prevsum += arr[i];
            if(set.contains(prevsum)==true){
                return true;
            }
            
            set.add(prevsum);
        }
        return false;
        
        
        
    }
}
