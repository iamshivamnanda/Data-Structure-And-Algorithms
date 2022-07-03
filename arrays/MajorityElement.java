package arrays;

import java.util.Arrays;

public class MajorityElement {
    static int majorityElement(int a[], int size)
    {
        // your code here
        Arrays.sort(a);
        int count = 0;
        int temp = -1;
        if(size == 1){
            return a[0];
        }
        for (int i = 0; i < a.length; i++) {
            if(a[i] == temp){
                count++;
                if(count > size/2){
                    return temp;
                }
            }else{
                temp = a[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = {1,2,3};
        System.out.println( majorityElement(a,a.length));
    }
}
