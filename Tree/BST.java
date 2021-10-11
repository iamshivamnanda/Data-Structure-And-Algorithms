package Tree;

import java.util.ArrayList;

public class BST {
    ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> mArrayList = new ArrayList<>();
       if(root == null){
           return mArrayList;
       }
       ArrayList<Integer> lArrayList = inOrder(root.left);
       mArrayList.addAll(lArrayList);
       mArrayList.add(root.data);
       ArrayList<Integer> rArrayList = inOrder(root.right);
       mArrayList.addAll(rArrayList);
       return mArrayList;
    }
    Node insert(Node root, int Key) {
        // if(root == null){
        //     return new Node(Key);
        // }
        // else if(root.data<Key){
        //     root.right = insert(root.right, Key);
        // }
        // else{
        //     root.left = insert(root.left, Key);
        // }
        // return root;

        Node curr = root;
        Node parent = null;
        Node temp = new Node(Key);
        while(curr != null){
            parent = curr;
            if(curr.data < Key){
                curr = curr.right;
            }
            else if(curr.data > Key){
                curr = curr.left;
            }else{
                return root;
            }
        }
        if(parent == null){
            return temp;
        }
        if(parent.data < Key){
            parent.right = temp;
        }else{
            parent.left = temp;
        }
        return root;
    }
    boolean search(Node root, int x) {
      Node curr = root;
      while(curr != null){
          if(curr.data == x){
              return true;
          }else if( curr.data < x){
              curr = curr.right;
          }else{
              curr = curr.left;
          }
      }
      return false;
    }

    public static Node succ(Node root){
        Node curr = root.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
    public static Node deleteNode(Node root, int X) {
       if(root == null){
           return null;
       }else if( root.data <X){
           root.right = deleteNode(root.right, X);
       }else if(root.data > X){
           root.left = deleteNode(root.left, X);
       }else{
           if(root.left == null){return root.right;}
           else if(root.right == null){return root.left;}
           else{
               Node succ = succ(root);
               root.data = succ.data;
               root.right = deleteNode(root.right, succ.data);
           }
       }
       return root;
    }
    int minValue(Node node) {
        int res = Integer.MAX_VALUE;
        Node curr = node;
        while (curr != null) {
            res = Math.min(res, curr.data);
            curr = curr.left;
        }
        return (res == Integer.MAX_VALUE) ? -1 :res;
    }
    int floor(Node root, int key) {
        int res = -1;
        Node curr = root;
        while(curr != null){
            if(curr.data == key){
                return key;
            }else if(curr.data > key){
                curr = curr.left;
            }else{
                res = curr.data;
                curr = curr.right;
            }
        }
        return res;

    }
    int findCeil(Node root, int key) {
        if (root == null) return -1;
        int res = Integer.MIN_VALUE;
        Node curr = root;
        while(curr != null){
            if(curr.data == key){
                return key;
            }else if(curr.data < key){
                curr = curr.right;
            }else{
                res = curr.data;
                curr = curr.left;
            }
        }
        return res;
    }
}
