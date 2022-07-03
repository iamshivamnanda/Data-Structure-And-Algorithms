package Hash;

import java.util.Arrays;

public class LinearProbing {
   public static  int[]  linearProbing(int hash_size, int arr[], int sizeOfArray)
    {
        //Your code here
        int hashTable[] = new int[hash_size];
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] =-1;
        }
        int size = 0;
        int i = 0;
        while (size<hash_size && i<sizeOfArray) {
            int hash = arr[i]%hash_size;
            if(hashTable[hash] == -1 || hashTable[hash] == arr[i]){
                hashTable[hash] = arr[i];
            }else{
                int j =1;
                int newhash = (arr[i] + j)%hash_size;
                while ((hashTable[newhash] != -1 && hashTable[newhash] != arr[i] ) && newhash!=hash) {
                     j++;
                    newhash = (arr[i] + j)%hash_size;
                }
                if(newhash ==hash){

                }else{
                    hashTable[newhash] = arr[i];
                }
            }
            i++;
            size++;
        }
        return hashTable;
    }
    public static void main(String[] args) {
        int myarr[] = {4,14,24,44};
        int []arr =linearProbing(10, myarr, 4);
        System.out.println(Arrays.toString(arr));
    }
}
