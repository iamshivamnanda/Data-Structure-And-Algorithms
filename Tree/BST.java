package Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;


class Pair{
    Node node;
    int hd;
    Pair(Node n,int h){node=n;hd=h;}
}
public class BST {
    
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
    static int count = 0;
    static int res = -1;
    public int KthSmallestElement(Node root, int K) {
        // Write your code here
       count = K;
        helper(root);
        if(count >0){
            return -1;
        }
        return res;
    }
    static void helper(Node root){
         if(root ==null){
            return;
        }
            helper(root.left);
            count--;
            if(count==0){
                res = root.data;
            }
            helper(root.right);
        
    }
    boolean isBST(Node root)
    {
       return isBSThelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    static boolean isBSThelper(Node root,int min,int max){
        if(root == null){
            return true;
        }
        return ( (root.data>min) && (root.data <max) && isBSThelper(root.left, min, root.data) && isBSThelper(root.right, root.data, max) );
    }

  static  ArrayList<Integer> inOrder(Node root) {
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
    public static ArrayList<Integer> findCommon(Node root1,Node root2)
    {
        HashSet<Integer> set = new HashSet<>();
       ArrayList<Integer> list1 = inOrder(root1);
        for (Integer integer : list1) {
            set.add(integer);
        }
        list1 = inOrder(root2);
        ArrayList<Integer> mArrayList = new ArrayList<>();
        for (Integer integer : list1) {
            if(set.contains(integer)){
                mArrayList.add(integer);
            }
        }
        return mArrayList;
    }
    Node LCA(Node root, int n1, int n2)
	{
        if(root == null || root.data == n1 || root.data == n2){
            return root;
        } 
        Node lLCNode = LCA(root.left, n1, n2);
        Node rLCNode = LCA(root.right, n1, n2);
        if(lLCNode == null){
            return rLCNode;
        }else if(rLCNode == null){
            return lLCNode;
        }else{
            return root;
        }
    }

    public Node correctBST(Node root)
    {
        ArrayList<Node> res = new ArrayList<>();
        res.add(null);
        res.add(null);
        res.add(null);
        correctBSTHelper(root,res);
            int temp = res.get(1).data;
            res.get(1).data = res.get(2).data;
            res.get(2).data = temp;
        return root;
    }
    static public void correctBSTHelper(Node root,ArrayList<Node> res){
        if(root == null){
            return;
        }
        correctBSTHelper(root.left,res);
        if(res.get(0) != null && root.data <res.get(0).data){
            if(res.get(1) == null){
                res.set(1, res.get(0));
            }
            res.set(2, root);
        }
        res.set(0, root);
        correctBSTHelper(root.right,res);
    }
    static boolean findPair(Node root, int X) 
    {
        HashSet<Integer> set =new HashSet<>();
        return findPairhelper(root, X, set);
    }
    static boolean findPairhelper(Node root,int x,HashSet<Integer> s){
        if(root == null){
            return false;
        }
        if(findPairhelper(root.left, x, s) == true){
            return true;
        }
        if(s.contains(x-root.data)){
            return true;
        }else{
            s.add(root.data);
        }
        if(findPairhelper(root.right, x, s) == true){
            return true;
        }
        return false;
    }
    public ArrayList <Integer> verticalSum(Node root) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        verticalSumHelper(root, 0, map);
        for (Map.Entry sum : map.entrySet()) {
            list.add((int) sum.getValue());
        }
        return list;
    }
    static  void verticalSumHelper(Node root,int hd,TreeMap<Integer,Integer> map){
        if(root == null){
            return;
        }
        verticalSumHelper(root.left, hd-1, map);
        int psum = (map.get(hd) == null)?0:map.get(hd);
        map.put(hd, psum+root.data);
        verticalSumHelper(root.right, hd+1, map);
    }
   
    static ArrayList <Integer> verticalOrder(Node root)
    {
        TreeMap<Integer,ArrayList<Integer>> mp = new TreeMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(root,0));
       while (q.isEmpty()==false) {
        Pair p=q.poll();
        Node curr=p.node;
        int hd=p.hd;
        ArrayList<Integer> arr ;
        if(mp.containsKey(hd)==false){
            arr = new ArrayList<Integer>();
        }else{
            arr = mp.get(hd);
        }
            arr.add(curr.data);
                mp.put(hd,arr);
            if(curr.left!=null)
                q.add(new Pair(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair(curr.right,hd+1));
            
       }
        for (Map.Entry sum : mp.entrySet()) {
        list.addAll((ArrayList<Integer>) sum.getValue());
        }
        return list;
    }
   
    static ArrayList<Integer> topView(Node root)
    {
        // add your code
        TreeMap<Integer,Integer> mp = new TreeMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(root,0));
       while (q.isEmpty()==false) {
        Pair p=q.poll();
        Node curr=p.node;
        int hd=p.hd;
        if(mp.containsKey(hd)==false)
                mp.put(hd,curr.data);
            if(curr.left!=null)
                q.add(new Pair(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair(curr.right,hd+1));
            
       }
        for (Map.Entry sum : mp.entrySet()) {
            list.add((int) sum.getValue());
        }
        return list;
    }
    public ArrayList <Integer> bottomView(Node root)
    {
        // Code here
        TreeMap<Integer,Integer> mp = new TreeMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(root,0));
       while (q.isEmpty()==false) {
        Pair p=q.poll();
        Node curr=p.node;
        int hd=p.hd;
                mp.put(hd,curr.data);
            if(curr.left!=null)
                q.add(new Pair(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair(curr.right,hd+1));
            
       }
        for (Map.Entry sum : mp.entrySet()) {
            list.add((int) sum.getValue());
        }
        return list;
    }
}
