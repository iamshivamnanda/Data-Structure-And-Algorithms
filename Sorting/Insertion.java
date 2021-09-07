package Sorting;

public class Insertion {
    public static int naive_insertion(int arr[],int n) {
        int res = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i; j < n; j++) {
                if(arr[i]>arr[j]){
                    res++;
                }
            }
        }
        return res;
    }

    public static int countAndMerge(int arr[],int l,int m ,int r) {;
        int n1 =m-l+1;
        int n2= r-m;
        int A[] = new int[n1];
        int B[] = new int[n2];
        int i,j,k=l;
        for (i = 0; i < n1; i++) {
            A[i] = arr[i+l];
        }
        for (j = 0; j < n2; j++) {
            B[j] = arr[j+m+1];
        }
        i=0;j=0;
        int res=0;
        while (i<n1 &&j<n2) {
            if(A[i]<=B[j]){
                arr[k] = A[i];
                i++;k++;
            }else{
                arr[k] = B[j];
                j++;k++;
                res += (n1-i);
            }
        }
        while (i<n1) {
            arr[k] = A[i];
            k++;i++;
        }
        while (j<n2) {
            arr[k] = B[j];
            k++;j++;
        }
        return res;
        
    }
    public static int efficent_insertion(int arr[],int l,int r) {
        int res =0;
        if(l<r){
            int m =(r+l)/2;
            res += efficent_insertion(arr, l, m);
            res += efficent_insertion(arr, m+1, r);
            res += countAndMerge(arr,l,m,r);
        }
        return res;
    }
    public static void main(String[] args) {
        int A[] = {2,4,1,3,5};
        System.out.println(efficent_insertion(A,0, A.length-1));
        System.out.println(naive_insertion(A, A.length));
    }
}
