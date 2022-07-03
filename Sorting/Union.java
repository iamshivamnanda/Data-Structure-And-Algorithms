package Sorting;

import java.util.Arrays;

public class Union {
    public static void naive_union(int A[],int B[],int n,int m) {
        int C[] = new int[n+m];
        for (int i = 0; i < n ; i++) {
            C[i] = A[i];
        }
        for (int i = 0; i < m; i++) {
            C[n+i] = B[i];
        }
        Arrays.sort(C);
        for (int i = 0; i < n+m; i++) {
            if(i==0 || C[i] !=C[i-1]){
                System.out.print(C[i] +" ");
            }
        }
        System.out.println();
    }

    public static void efficent_union(int A[],int B[],int n,int m) {
        int i=0,j=0;
        while (i<n && j<m) {
            if(i>0 && A[i]==A[i-1]){
                i++;
                continue;
            }
            if(j>0 && B[j]==B[j-1]){
                j++;
                continue;
            }
            if(A[i]<B[j]){
                System.out.print(A[i] + " ");
                i++;
            }else if(A[i]>B[j]){
                System.out.print(B[j] + " ");
                j++;
            }else{
                System.out.print(B[j] + " ");
                i++;j++;
            }
        }
        while(i<n){
            if(i>0 && A[i] !=A[i-1]){
                System.out.print(A[i] + " ");
            }
            i++;
        }
        while(j<m){
            if(j>0 && B[j] !=B[j-1]){
                System.out.print(B[j] + " ");
            }
            j++;
        }
    }
    public static void main(String[] args) {
        int A[]= {10,20,20};
        int B[] = {5,20,40,40};
        efficent_union(A, B, A.length, B.length);
    }
}
