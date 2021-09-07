package Sorting;

public class SelectionSort {
    
    static int  select(int arr[], int i)
	{
	    int min_index = i;
	    for(int j=i+1;j<arr.length;j++){
	        if(arr[min_index] > arr[j]){
	            min_index = j;
	        }
	    }
	    return min_index;
        // code here such that selectionSort() sorts arr[]
	}
	
	 static void selectionSort(int arr[], int n)
	{
	    //code here
	    for(int i=0;i<n;i++){
	        int min = select(arr,i);
	        
	        int temp = arr[i];
	        arr[i] = arr[min];
	        arr[min] = temp;
	    }
	}
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        selectionSort(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
