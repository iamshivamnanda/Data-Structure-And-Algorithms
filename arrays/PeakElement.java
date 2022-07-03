package arrays;

public class PeakElement {
    static int peakElement(int[] arr,int n)
    {
       int l = 0; int r = n ;
       while (l<=r) {
            int mid = (l +r)/2;
            if( (mid == 0 || arr[mid-1] <= arr[mid] ) &&  
            (mid == n-1 || arr[mid+1]<=arr[mid])){
                return 1;
            }
            else if(mid>0 && arr[mid-1]>arr[mid]){
                r = mid -1;
            }
            else{
                l = mid +1;
            }
       }
       return 0;
    }

    public static void main(String[] args) {
        int a[] = {1,2,3};
        System.out.println(peakElement(a, 3));
    }
}
