package Greedy;

import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

class Mycmpp implements Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        return o1.weight*o2.value - o1.value*o2.weight;
    }
    
}
public class FractionalKnapSack {
    double fractionalKnapsack(int W, Item arr[], int n) 
    {
       Arrays.sort(arr,new Mycmpp());
       double res = 0;
       for (int i = 0; i < n; i++) {
           if(arr[i].weight <=W){
               res = res + arr[i].value;
               W = W - arr[i].weight;
           }else{
               res = res + arr[i].value * ((double) W/arr[i].weight);
               break;
           }
       }
       return res;
    }
}
