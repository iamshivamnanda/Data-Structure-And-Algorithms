package Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class BSTSDESHEET {
    // as it is a binary tree so we will use binary search
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    Node insert(Node root, int Key) {
        if (root == null) {
            Node node = new Node(Key);
            return node;
        }

        if (root.data < Key) {
            root.right = insert(root.right, Key);
        } else if (root.data > Key) {
            root.left = insert(root.left, Key);
        }

        return root;
    }

    int minValue(Node node) {
        if (node == null) {
            return -1;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    static HashSet<Integer> allNodes;
    static HashSet<Integer> leafs;

    public static boolean isDeadEnd(Node root) {
        allNodes = new HashSet<>();
        allNodes.add(0);
        leafs = new HashSet<>();

        addAllNodes(root);

        for (Integer leaf : leafs) {
            if (allNodes.contains(leaf - 1) && allNodes.contains(leaf + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void addAllNodes(Node root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leafs.add(root.data);
        }
        allNodes.add(root.data);
        addAllNodes(root.left);
        addAllNodes(root.right);
    }

    // public static boolean isDeadEnd(Node root) {
    // return isDeadEndUtil(root, 1, Integer.MAX_VALUE);
    // }

    public static boolean isDeadEndUtil(Node root, int start, int end) {
        if (root == null)
            return false;

        if (start == end)
            return true;

        return isDeadEndUtil(root.left, start, root.data - 1) || isDeadEndUtil(root, root.data + 1, end);
    }

    Node binaryTreeToBST(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        preorder(root, list);
        Collections.sort(list);
        Node head = solve(list, 0, list.size() - 1);
    }

    public static void preorder(Node root, ArrayList<Integer> list) {
        if (root == null)
            return;

        list.add(root.data);
        preorder(root.left, list);
        preorder(root.right, list);

    }

    public static Node solve(ArrayList<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node head = new Node(list.get(mid));

        head.left = solve(list, start, mid - 1);
        head.right = solve(list, mid + 1, end);

        return head;
    }

    // return the Kth largest element in the given BST rooted at 'root'
    public int kthLargest(Node root, int K) {
        // we know that in bst inorder traveseal is always sorted
        // so get the inorder traversal and return size - k element as the kth largest

        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);

        if (K > list.size()) {
            return -1;
        }
        return list.get(list.size() - K);
    }

    public static void inorder(Node root, ArrayList<Integer> list) {
        if (root == null)
            return;

        inorder(root.left, list);
        list.add(root.data);
        inorder(root.right, list);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        if (root.left != null && maxValue(root.left) >= root.val)
            return false;

        if (root.right != null && minValue(root.right) <= root.val)
            return false;

        if (!isValidBST(root.left) || !isValidBST(root.right))
            return false;

        return true;
    }

    public static int maxValue(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        int value = root.val;
        int leftMax = maxValue(root.left);
        int rightMax = maxValue(root.right);

        return Math.max(value, Math.max(leftMax, rightMax));
    }

    public static int minValue(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;

        int value = root.val;
        int leftMin = minValue(root.left);
        int rightMin = minValue(root.right);

        return Math.min(value, Math.min(leftMin, rightMin));
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderUtil(root, list);
        if (k > list.size())
            return -1;

        return list.get(k - 1);
    }

    public static void inorderUtil(TreeNode root, ArrayList<Integer> list) {
        if (root == null)
            return;

        inorderUtil(root.left, list);
        list.add(root.val);
        inorderUtil(root.right, list);
    }

    public void flatten(TreeNode root) {
        TreeNode duNode = new TreeNode(-1);
        prev = duNode;
        flattenInorderUtil(root);
    }

    static TreeNode prev;

    public static void flattenInorderUtil(TreeNode root) {
        if (root == null)
            return;

        flattenInorderUtil(root.left);
        prev.left = null;
        prev.right = root;
        prev = root;
        flattenInorderUtil(root.right);
    }

    class Res {
        Node pre = null;
        Node succ = null;
    }

    public static void findPreSuc(Node root, Res p, Res s, int key) {
        if (root == null)
            return;

        if (root.data == key) {
            if (root.left != null) {
                Node temp = root.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                p.pre = temp;
            }
            if (root.right != null) {
                Node temp = root.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                s.succ = temp;
                return;
            }
        }

        if (root.data > key) {
            s.succ = root;
            findPreSuc(root.left, p, s, key);
        } else if (root.data < key) {
            p.pre = root;
            findPreSuc(root.right, p, s, key);
        }
    }

    public static Node deleteNode(Node root, int X) {
        if (root == null)
            return null;

        // if X is not equal to root.data then recurively call for root.left or
        // root.right
        if (root.data > X) {
            root.left = deleteNode(root.left, X);
            return root;
        } else if (root.data < X) {
            root.right = deleteNode(root.right, X);
            return root;
        }

        // if root.data == X i.e. we are at the root to delete

        if (root.left == null) { // if root.left is null then succ will be root.right
            return root.right;
        } else if (root.right == null) {// if root.right is null then succ will be root.left
            return root.left;
        } else {
            Node sucParNode = root;
            Node suc = root.right;

            while (suc.left != null) {
                sucParNode = suc;
                suc = suc.left;
            }

            if (sucParNode.data != root.data) {
                sucParNode.left = suc.right;
            } else {
                sucParNode.right = suc.right;
            }

            root.data = suc.data;
            return root;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        // if X is not equal to root.data then recurively call for root.left or
        // root.right
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        // if root.data == X i.e. we are at the root to delete

        if (root.left == null) { // if root.left is null then succ will be root.right
            return root.right;
        } else if (root.right == null) {// if root.right is null then succ will be root.left
            return root.left;
        } else {
            TreeNode sucParNode = root;
            TreeNode suc = root.right;

            while (suc.left != null) {
                sucParNode = suc;
                suc = suc.left;
            }

            if (sucParNode.val != root.val) {
                sucParNode.left = suc.right;
            } else {
                sucParNode.right = suc.right;
            }

            root.val = suc.val;
            return root;
        }
    }
}
