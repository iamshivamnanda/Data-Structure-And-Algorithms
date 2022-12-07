package Hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Hash {

    public static int[][] pairSum(int[] arr, int s) {
        HashSet<Integer> hSet = new HashSet<>();
        for (int val : arr)
            hSet.add(val);

        ArrayList<Integer[]> list = new ArrayList<>();
        for (int val : arr) {
            if (hSet.contains(s - val)) {
                Integer reslist[] = new Integer[2];
                reslist[0] = val;
                reslist[1] = s - val;
                list.add(reslist);
            }
        }
        int res[][] = new int[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            Integer rList[] = list.get(i);
            int newRes[] = new int[2];
            newRes[0] = rList[0];
            newRes[1] = rList[1];
            res[i] = newRes;
        }
        return res;

    }

    public static void main(String[] args) {
        Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();

        ht.put(123, 432);
        ht.put(12, 2345);
        ht.put(15, 5643);
        ht.put(3, 321);

        ht.remove(12);

        System.out.println(ht);
    }
}
