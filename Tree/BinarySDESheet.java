package Tree;

import java.util.*;

//  Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinarySDESheet {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    public ArrayList<Integer> reverseLevelOrder(Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayDeque<Node> q = new ArrayDeque<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.remove();
            stack.push(n.data);
            if (n.right != null)
                q.add(n.right);
            if (n.left != null)
                q.add(n.left);
        }

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        if (isIdentical(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isIdentical(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }

        if (root.val == subRoot.val) {
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }

        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayDeque<TreeNode> q = new ArrayDeque<>();

        q.add(root);
        while (!q.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.remove();
                list.add(cur.val);
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
            res.add(list);
        }

        return res;
    }

    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        leftViewUtil(root, res, 0);
        return res;

    }

    public static void leftViewUtil(Node node, ArrayList<Integer> list, int level) {
        if (node == null) {
            return;
        }

        if (level == list.size()) {
            list.add(node.data);
        }

        leftViewUtil(node.left, list, level + 1);
        leftViewUtil(node.right, list, level + 1);
    }

    ArrayList<Integer> rightView(Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        rightViewUtil(node, res, 0);
        return res;
    }

    public static void rightViewUtil(Node node, ArrayList<Integer> res, int level) {
        if (node == null) {
            return;
        }
        if (level == res.size()) {
            res.add(node.data);
        }
        rightViewUtil(node.right, res, level + 1);
        rightViewUtil(node.left, res, level + 1);
    }

    // Function to store the zig zag order traversal of tree in a list.
    ArrayList<Integer> zigZagTraversal(Node root) {
        // it is a level order treversal with reverss lot alternative
        ArrayDeque<Node> queue = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean flag = false;
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);

                if (flag) {
                    stack.push(cur.data);
                } else {
                    res.add(cur.data);
                }
            }
            while (!stack.isEmpty()) {
                res.add(stack.pop());
            }
            flag = !flag;
        }
        return res;
    }

    boolean check(Node root) {
        return checkUtil(root, 0);
    }

    static int maxLevel = Integer.MAX_VALUE;

    boolean checkUtil(Node root, int level) {
        if (root == null) {
            if (level > maxLevel) {
                return false;
            }
            maxLevel = level;
        }
        return checkUtil(root.left, level + 1) && checkUtil(root.right, level + 1);
    }

    boolean check(Node root) {
        // using level order treversal
        ArrayDeque<Node> qDeque = new ArrayDeque<>();
        boolean flag = false;

        qDeque.add(root);

        while (!qDeque.isEmpty()) {
            int size = qDeque.size();
            for (int i = 0; i < size; i++) {
                Node cur = qDeque.remove();
                if (cur.left == null && cur.right == null) {
                    flag = true;
                }
                if (cur.left != null)
                    qDeque.add(cur.left);
                if (cur.right != null)
                    qDeque.add(cur.right);
            }

            if (flag && qDeque.size() != 0) {
                return false;
            }
        }
        return true;
    }

    boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }
        int left = isBalancedUtil(root.left);
        int right = isBalancedUtil(root.right);
        return (Math.abs(right - left) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    int isBalancedUtil(Node root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedUtil(root.left) + 1;
        int right = isBalancedUtil(root.right) + 1;
        return Math.max(left, right);
    }

}
