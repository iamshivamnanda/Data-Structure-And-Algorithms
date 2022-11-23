package Tree;

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
}
