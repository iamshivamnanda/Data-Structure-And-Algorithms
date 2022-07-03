package Sorting;

public class Intersection {
    public static void naive_intersection(int A[],int B[],int n,int m) {
        for (int i = 0; i < A.length; i++) {
            if(i>0 && A[i] == A[i-1]){
                continue;
            }
            for (int j = 0; j < B.length; j++) {
                if(A[i]==B[j]){
                    System.out.print(A[i] + " ");
                }
            }
        }
    }

    public static void efficent_intersection(int A[],int B[],int n,int m) {
        int i= 0,j=0;
        while(i<n && j<m){
            if(i>0 && A[i]==A[i-1]){
                i++;
                continue;
            }
            if(A[i]>B[j]){
                j++;
            }else if(A[i]<B[j]){
                i++;
            }else{
                System.out.print(A[i] + " ");
                i++;j++;
            }
        }
    }

    public static void main(String[] args) {
        int A[] = {2,3,5,5,6,9};
        int B[] = {3,5,9,8,10};
        efficent_intersection(A, B, A.length, B.length);
    }
}
