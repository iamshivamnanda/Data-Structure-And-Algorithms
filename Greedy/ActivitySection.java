package Greedy;

import java.util.Arrays;

import java.util.Comparator;

import java.util.HashSet;


class Activity{
    int start,finish;
    Activity(int s,int f){
        start = s;
        finish =f;
    }
}
class Mycmp implements Comparator<Activity>{
    @Override
    public int compare(Activity o1, Activity o2) {
        return o1.finish - o2.finish;
    }
}

public class ActivitySection {
    public static int activitySelection(int start[], int end[], int n)
    {
        Activity[] activities  = new Activity[n];
        for (int i = 0; i < n; i++) {
            activities[i] = new Activity(start[i], end[i]);
        }
        Arrays.sort(activities,new Mycmp());
        int res =1;
        int prev = 0;
        for (int i = 1; i < n; i++) {
            if(activities[i].start > activities[prev].finish){
                res++;
                prev  = i;
            }
        }
        return res;
    }
    static int maxNumbers(int n, int k, Integer a[]){
    Arrays.sort(a);
    HashSet<Integer> set  = new HashSet<>();
    for (int i : a) {
        set.add(i);
    }
       int count = 0;
       int sum = 1;
       for (int i = 1; sum < k; i++) {
           if(set.contains(i)){
               continue;
           }
           sum += i;
           count++;
       }
       return count-1;
    }
    
}
