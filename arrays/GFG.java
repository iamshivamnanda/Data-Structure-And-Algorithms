/*package whatever //do not write package name here */
package arrays;

import java.util.*;
import java.io.*;

class GFG {
    
    static void printArray(int[] arr,int n){
        for (int i = 0; i < n; i++) {
            System.err.print(arr[i] + " ");
        }
        System.out.println();
    }
    
     static void rotateArray(int[] arr,int n,int d){
        int temp[] = Arrays.copyOfRange(arr, 0, d);
        for (int i = 0; i < arr.length - d; i++) {
            arr[i] = arr[i+d];
        }
        int j = 0;
        for (int i = n-d; i < arr.length; i++) {
            arr[i] = temp[j];
            j++;
        }

    }
    
    
    
	public static void main (String[] args) throws IOException {
		//code
		Scanner in = new Scanner(System.in);
	         int test = in.nextInt();
	         while(test-->0)
	         {
	             int N = in.nextInt();
	             int D = in.nextInt();
	             int a[]= new int[N];
	             for(int i=0; i<N; i++)
	             {
	                a[i] = in.nextInt();
	             }
            rotateArray(a, N, D);
            printArray(a,N);
	}
    in.close();
	
}
}