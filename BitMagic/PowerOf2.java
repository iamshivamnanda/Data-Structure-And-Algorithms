package BitMagic;

import java.util.ArrayList;

public class PowerOf2 {
    static Long numberOfSubsequences(int N, ArrayList<Long> A){
        Long numSequence = 0L;
        for (int i = 0; i < N; i++) {
            if(powerOf2(A.get(i))){
                numSequence++;
            }
        }
        double out =   Math.pow(2, numSequence.doubleValue()) - 1 ;
        return (long)out;
    }

    static boolean powerOf2(Long n){
        if((n & (n-1)) ==0){
            return true;
        }else{
            return false;
        }
     }

    public static void main(String[] args) {
        ArrayList<Long> mArrayList = new ArrayList<Long>();
        mArrayList.add(1L);
        mArrayList.add(6L);
        mArrayList.add(2L);

        System.out.println(numberOfSubsequences(3,mArrayList));
    }
}
