package Tree;
import java.util.*;
class Distance{
    int val ;
    Distance(int value){
        val  = value;
    }
}
class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
public class Tree {
    static ArrayList<Integer> preorder(Node root)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(root!=null){
            arrayList.add(root.data);
            ArrayList<Integer> arrayListL =   preorder(root.left);
            arrayList.addAll(arrayListL);
            ArrayList<Integer> arrayListR = preorder(root.right);
            arrayList.addAll(arrayListR);
        }
        return arrayList;
    }
    ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(root!=null){
            Tree g = new Tree();
            ArrayList<Integer> arrayListL =   g.inOrder(root.left);
            arrayList.addAll(arrayListL);
            arrayList.add(root.data);
            ArrayList<Integer> arrayListR = g.inOrder(root.right);
            arrayList.addAll(arrayListR);
        }
        return arrayList;
    }
    ArrayList<Integer> postOrder(Node root)
    {
       // Your code goes here
       ArrayList<Integer> arrayList = new ArrayList<>();
       if(root!=null){
           Tree g = new Tree();
           ArrayList<Integer> arrayListL =   g.postOrder(root.left);
           arrayList.addAll(arrayListL);
           ArrayList<Integer> arrayListR = g.postOrder(root.right);
           arrayList.addAll(arrayListR);
           arrayList.add(root.data);
       }
       return arrayList;
    }
   static int height(Node node) 
    {
       if(node ==null){
           return 0;
       }else{
        return Math.max(height(node.left), height(node.right))+1;
       }
    }
    boolean isIdentical(Node root1, Node root2)
	{
	   if((root1 == null && root2!=null) || (root1!=null && root2==null)){
	       return false;
	   }else if(root1==null && root2==null){
	       return true;
	   }else{
	       return (root1.data==root2.data) && isIdentical(root1.left,root2.left) &&isIdentical(root1.right,root2.right);
	   }
	}
    public static int isSumProperty(Node root)
    {
        if(root==null){
            return 1;
        }
        else if(root.left == null && root.right ==null){
            return 1;
        }
        else{
            int left = isSumProperty(root.left);
            int right = isSumProperty(root.right);
            int sum ;
            if(root.left == null){
                sum = root.data - root.right.data;
            }else if(root.right == null){
                sum  = root.data - root.left.data;
            }else{
             sum = root.data -root.right.data -root.left.data;
            }
            if(sum==0 && left ==1 && right ==1){
                return 1;
            }else{
                return 0;
            }
        }
        
    }
    static ArrayList <Integer> levelOrder(Node node) 
    {
        ArrayList<Integer> mArrayList = new ArrayList<Integer>();
        ArrayDeque<Node> arrayDeque = new ArrayDeque<Node>();
        arrayDeque.add(node);
    
        while (arrayDeque.isEmpty() == false) {
            Node curNode = arrayDeque.remove();
            mArrayList.add(curNode.data);
            if(curNode.left !=null){
                arrayDeque.add(curNode.left);
            }
            if(curNode.right !=null){
                arrayDeque.add(curNode.right);
            }
        }
        return mArrayList;
    }
    static ArrayList<ArrayList<Integer>> levelOrderLine(Node node) 
    {
        ArrayList<ArrayList<Integer>>  arrayList= new ArrayList<ArrayList<Integer>>();
        ArrayDeque<Node> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(node);
        while(arrayDeque.isEmpty() == false){
            int count = arrayDeque.size();
            ArrayList<Integer> mArrayList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Node curNode = arrayDeque.remove();
                mArrayList.add(curNode.data);
                if(curNode.left !=null){
                    arrayDeque.add(curNode.left);
                }
                if(curNode.right !=null){
                    arrayDeque.add(curNode.right);
                }
            }
            arrayList.add(mArrayList);
        }
        return arrayList;
    }

    ArrayList<Integer> findSpiral(Node root) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        ArrayDeque<Node> q = new ArrayDeque<>();
        Stack<Node> stack =  new Stack<>();
        boolean flag = true;
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                Node n = q.remove();
                if(flag){
                    stack.push(n);
                }else{
                    list.add(n.data);
                }
                if(n.left !=null){
                    q.add(n.left);
                }
                if(n.right !=null){
                    q.add(n.right);
                }
            }
            if(flag){
                    while(!stack.isEmpty()){
                        list.add(stack.pop().data);
                    }
                }
            flag = !flag;
        }
        return list;
        
    }
    ArrayList<Integer> findSpiral2(Node root) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            while (!stack1.isEmpty()) {
                Node curr = stack1.pop();
                list.add(curr.data);
                if(curr.right != null){stack2.push(curr.right);}
                if(curr.left != null){stack2.push(curr.left);}
            }
            while (!stack2.isEmpty()) {
                Node curr = stack2.pop();
                list.add(curr.data);
                if(curr.left != null){stack1.push(curr.left);}
                if(curr.right != null){stack1.push(curr.right);}
            }
        }
        return list;
      
    }
    int minValue(Node node) {
        if(node==null){
            return Integer.MAX_VALUE;
        }
        return Math.min(node.data, Math.min(minValue(node.left), minValue(node.right)));
    }
    
    int getMaxWidth(Node root) {
        int size = 1;
        ArrayDeque<Node> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(root);
        while(arrayDeque.isEmpty() == false){
            int count = arrayDeque.size();
            ArrayList<Integer> mArrayList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Node curNode = arrayDeque.remove();
                mArrayList.add(curNode.data);
                if(curNode.left !=null){
                    arrayDeque.add(curNode.left);
                }
                if(curNode.right !=null){
                    arrayDeque.add(curNode.right);
                }
            }
           
            size = Math.max(size, mArrayList.size());
        }
        return size;
    }
    static int isbal(Node root){
        if(root ==null){
            return 0;
        }
        int lbal = isbal(root.left);
        if(lbal == -1){
            return -1;
        }
        
        int rbal = isbal(root.right);
        if(rbal == -1){
            return -1;
        }
        if(Math.abs(lbal-rbal)>1){return -1;}
        else{
            return (Math.max(lbal, rbal) +1);
        }
    }
    boolean isBalanced(Node root)
    {
       int res = isbal(root);
       if(res == -1){
           return false;
       }else{
           return true;
       }
    }
    ArrayList<Integer> leftView(Node root)
    {
      // Your code here
      ArrayDeque<Node> arrayDeque = new ArrayDeque<>();
      ArrayList<Integer> mArrayList = new ArrayList<>();
      if(root==null){
        return mArrayList;
    }
      arrayDeque.add(root);
      while(arrayDeque.isEmpty() == false){
          int count = arrayDeque.size();
          for (int i = 0; i < count; i++) {
              Node curNode = arrayDeque.remove();
              if(i==0){
                  mArrayList.add(curNode.data);
              }
              if(curNode.left !=null){
                  arrayDeque.add(curNode.left);
              }
              if(curNode.right !=null){
                  arrayDeque.add(curNode.right);
              }
          }
      }
      return mArrayList;
    }
    ArrayList<Integer> rightView(Node root) {
        //add code here.
         ArrayDeque<Node> arrayDeque = new ArrayDeque<>();
      ArrayList<Integer> mArrayList = new ArrayList<>();
      if(root==null){
        return mArrayList;
    }
      arrayDeque.add(root);
      while(arrayDeque.isEmpty() == false){
          int count = arrayDeque.size();
          for (int i = 0; i < count; i++) {
              Node curNode = arrayDeque.remove();
              if(i==count-1){
                  mArrayList.add(curNode.data);
              }
              if(curNode.left !=null){
                  arrayDeque.add(curNode.left);
              }
              if(curNode.right !=null){
                  arrayDeque.add(curNode.right);
              }
          }
      }
      return mArrayList;
    }
    Node prev;
    
    Node bToDLL(Node root)
    {
	if(root == null){
	    return root;
	}
	Node head = bToDLL(root.left);
    if(prev == null){
        head = root;
    }else{
        root.left = prev;
        prev.right = root;
    }
    prev = root;
    bToDLL(root.right);
    return head;
    }
    // public static Tree convert(Node head, Tree node) {
    //     ArrayDeque<Tree> arrayDeque = new ArrayDeque<>();
    //     ArrayList<Node> list = new ArrayList<>();
    //     int i =0;
    //     while (head!=null) {
    //         list.add(head);
    //         head = head.next;
    //     }
    //     Tree root = new Tree(list.get(0).data);
    //     arrayDeque.add(root);
    //     while(arrayDeque.isEmpty() == false){
    //        Tree cur = arrayDeque.remove();
    //         if((2*i+1)<list.size()){
    //             cur.left = new Tree(list.get(2*i+1).data);
    //             arrayDeque.add(cur.left);
    //         }
    //         if((2*i+2)<list.size()){
    //             cur.right = new Tree(list.get(2*i+2).data);
    //             arrayDeque.add(cur.right);
    //         }
    //         i++;
    //     }
    //     return root;
    // }
    int res = Integer.MIN_VALUE;
    int height1(Node root) {
        if(root == null){
            return 0;
        }
        int lh =height1(root.left);
        int rh =height1(root.right);
        res = Math.max(res, 1+lh+rh);
        return (1 + Math.max(lh,rh));
    }
    Node lca(Node root, int n1,int n2)
	{
		if(root == null){
            return null;
        }
        if(root.data == n1 || root.data == n2){
            return root;
        }
        Node llca = lca(root.left,n1,n2);
        Node rlca = lca(root.right,n1,n2);
        if(llca !=null && rlca !=null){
            return root;
        }
        if(llca !=null){
            return llca;
        }else{
            return rlca;
        }
	}
    
    public static int burnTime(Node root, int leaf, int res[]) 
    {
        if(root==null)return 0;
        if(root.data==leaf){
            res[0] = Math.max(res[0], height(root.right));
            res[0] = Math.max(res[0], height(root.left));
            return 1;
        }
        int lh=burnTime(root.left,leaf,res);
        int rh=burnTime(root.right,leaf,res);
        
        if(lh !=-0){
           res[0]=Math.max(res[0],lh + height(root.right));
           return lh +1;
        }
        if(rh !=-0){
           res[0]=Math.max(res[0],rh + height(root.left));
           return rh +1;

        }
        
        return 0;
    }
    public static int minTime(Node root, int target) 
    {
        int res[] = {0};
         burnTime(root, target, res);
         return res[0];
    }
    boolean isCompleteBT(Node root)
    {
        if(root == null){
            return true;
        }
        Node curNode = root;
        int lh = 0;
        while (curNode != null) {
            lh++;
            curNode = curNode.left;
        }
        curNode = root;
        int rh = 0;
        while (curNode != null) {
            rh++;
            curNode = curNode.right;
        }
        System.out.println("lh  = " + lh);
        System.out.println("rh  = " + rh);
        if(lh !=rh){
            return false;
        }else{
            return isCompleteBT(root.left) && isCompleteBT(root.right);
        }
	} 
    static final int EMPTY=-1;
    public static void serialize(Node root, ArrayList<Integer> arr){
       if(root==null){
           arr.add(EMPTY);
           return;
       }
        arr.add(root.data);
        serialize(root.left,arr);
        serialize(root.right,arr);
    }
    
    public static Node deSerialized(ArrayList<Integer> arr,int index[]){
        if(index[0]==arr.size())
            return null;
        int val=arr.get(index[0]);
        index[0]++;
        if(val==EMPTY)return null;
        Node root=new Node(val);
        root.left=deSerialized(arr,index);
        root.right=deSerialized(arr,index);
        return root;
    }
   
    public static Node deSerialize(ArrayList<Integer> arr){
        int ress[] ={0};
        return deSerialized(arr,ress);
    }
    static  int postIndex;
    public static Node cTree(int in[],int post[],int is,int ie){
      if(is>ie)return null;
      Node root= new Node(post[postIndex]);
      postIndex--;
      
      int inIndex=is;
      for(int i=is;i<=ie;i++){
          if(in[i]==root.data){
              inIndex=i;
              break;
          }
      }
      root.right=cTree(in, post, inIndex+1, ie);
      root.left=cTree(in, post, is, inIndex-1);
      return root;
  }
  Node buildTree(int in[], int post[], int n) {
      postIndex = n-1;
      
      return cTree(in,post,0,n-1);
  }

  //preorder and inorder to tree


//   static  int preIndex;
//   public static Node cTree(int in[],int pre[],int is,int ie){
//     if(is>ie)return null;
//     Node root= new Node(pre[preIndex++]);
    
    
//     int inIndex=is;
//     for(int i=is;i<=ie;i++){
//         if(in[i]==root.data){
//             inIndex=i;
//             break;
//         }
//     }
//     root.left=cTree(in, pre, is, inIndex-1);
//     root.right=cTree(in, pre, inIndex+1, ie);
//     return root;
// }
// Node buildTree(int in[], int preorder[], int n) {
//     preIndex = 0;
    
//     return cTree(in,preorder,0,n-1);
// }

ArrayList<Integer> inOrderIterative(Node root)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node cur = root;
        while(cur !=null || !stack.isEmpty()){
            while (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            }
             cur = stack.pop();
             arrayList.add(cur.data);
             cur = cur.right;

        }
        return arrayList;
    }
    ArrayList<Integer> preOrderIterative(Node root)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        
        while( !stack.isEmpty()){

            Node cur = stack.pop();
             arrayList.add(cur.data);
             if(cur.right != null){
                 stack.push(cur.right);
             }
             if(cur.left != null){
                 stack.push(cur.left);
             }
             
        }
        return arrayList;
    }

    
}
  


