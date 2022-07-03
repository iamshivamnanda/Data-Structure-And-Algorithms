package Greedy;

import java.util.HashMap;

class MinHeapNode
{
    char data;
    int freq;
    MinHeapNode left, right, top;
    MinHeapNode(char c, int freq)
    {
        left = right = null;
        this.data = c;
        this.freq = freq;
    }
}
public class Hufman {

    static void printCodeChar(MinHeapNode node,String str,HashMap<String,Character> map){
        if(node == null){
            return;
        }
        if(node.data != '$'){
            map.put(str, node.data);
            return;
        }
        printCodeChar(node.left, str + "1", map);
        printCodeChar(node.right, str + "0", map);
    }
   
    static String decodeHuffmanData(MinHeapNode root, String binaryString)
    {
        MinHeapNode t = root;
        String ans= "";
        for (int i = 0; i < binaryString.length(); i++) {
            if(binaryString.charAt(i) =='0'){
                t = t.left;
            }else{
                t = t.right;
        }
        if(t.data !='$'){
            ans += t.data;
            t = root;
        }
        }
        return ans;
    }
}
