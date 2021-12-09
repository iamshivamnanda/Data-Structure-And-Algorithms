package Greedy;

import java.util.Arrays;
import java.util.Comparator;


class Job{
    int id,profit,deadline;
    Job(int i,int p,int d){
        this.id = i;
        this.profit = p;
        this.deadline = d;
    }
}

class Mycomppp implements Comparator<Job>{

    @Override
    public int compare(Job o1, Job o2) {
        return o2.profit - o1.profit;
    }
    
}
public class JobSequencing {
    int[] JobScheduling(Job arr[], int n)
    { int res = 0;
        int count = 0;
        boolean[] result = new boolean[n];
        Arrays.sort(arr,new Mycomppp());
        for (int i = 0; i < arr.length; i++) {

            for (int j = Math.min(n-1, arr[i].deadline-1); j >=0  ; j--) {
            if(!result[j]){
                result[j] = true;
                res = res + arr[i].profit;
                count++;
                break;
                
            }
        }
        }
        int[] ress = {count,res};
        return ress;
    }
}
