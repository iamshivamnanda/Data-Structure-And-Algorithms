package arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ArraysSDEsheet {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for (int i = 0; i < res.length; i++) {
            hMap.put(nums[i], i);
        }

        for (int i = 0; i < res.length; i++) {
            if (hMap.containsKey(target - nums[i])) {
                int j = hMap.get(target - nums[i]);
                if (i != j) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public int[][] overlappedInterval(int[][] Intervals)
    {
        Arrays.sort(Intervals, (a,b)-> a[0]-b[0]);
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(Intervals[0]);
        for (int i = 1; i < Intervals.length; i++) {
            if(stack.peek()[1]>=Intervals[i][0]){
                stack.peek()[1]=Intervals[i][1];
            }else{
                stack.push(Intervals[i]);
            }
        }

        int res[][] = new int[stack.size()][2];
        int i=stack.size()-1;
        while (!stack.isEmpty()) {
            res[i] = stack.poll();
            i--;
        }
        return res;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res =  new ArrayList<>();
        HashSet<Integer> hSet = new HashSet<>();
        for (int integer : nums) {
            if(hSet.contains(integer)){
                res.add(integer);
                continue;
            }
            hSet.add(integer);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
