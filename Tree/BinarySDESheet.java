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

    // boolean check(Node root) {
    // // using level order treversal
    // ArrayDeque<Node> qDeque = new ArrayDeque<>();
    // boolean flag = false;

    // qDeque.add(root);

    // while (!qDeque.isEmpty()) {
    // int size = qDeque.size();
    // for (int i = 0; i < size; i++) {
    // Node cur = qDeque.remove();
    // if (cur.left == null && cur.right == null) {
    // flag = true;
    // }
    // if (cur.left != null)
    // qDeque.add(cur.left);
    // if (cur.right != null)
    // qDeque.add(cur.right);
    // }

    // if (flag && qDeque.size() != 0) {
    // return false;
    // }
    // }
    // return true;
    // }

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

    public void toSumTree(Node root) {
        toSumTreeUtil(root);
    }

    public static Node toSumTreeUtil(Node root) {
        if (root == null) {
            return root;
        }

        int left = 0;
        int right = 0;
        if (root.left != null)
            left = root.left.data;
        if (root.right != null)
            right = root.right.data;

        int leftNode = 0;
        if (root.left != null)
            leftNode = toSumTreeUtil(root.left).data;
        int rightNode = 0;
        if (root.right != null)
            rightNode = toSumTreeUtil(root.right).data;

        root.data = left + right + leftNode + rightNode;
        return root;
    }

    // Return True if the given trees are isomotphic. Else return False.
    boolean isIsomorphic(Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        if (root1.data != root2.data)
            return false;

        return (isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right)) ||
                (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left));

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        if (p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static Node buildTree(int inorder[], int preorder[], int n) {
        hMap = new HashMap<>();
        curIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            hMap.put(inorder[i], i);
        }

        return buildTreeUtil(inorder, preorder, 0, inorder.length - 1);
    }

    static int curIndex = 0;
    static HashMap<Integer, Integer> hMap;

    public static Node buildTreeUtil(int inorder[], int preorder[], int start, int end) {
        System.out.printf("%d %d \n", start, end);
        if (start > end)
            return null;

        Node tNode = new Node(preorder[curIndex++]);
        if (start == end) {
            return tNode;
        }

        int index = hMap.get(tNode.data);
        tNode.left = buildTreeUtil(inorder, preorder, start, index - 1);
        tNode.right = buildTreeUtil(inorder, preorder, index + 1, end);

        return tNode;

    }

    // public static TreeNode buildTreeUtil(int inorder[], int preorder[], int
    // start, int end) {
    // if (start > end)
    // return null;

    // TreeNode tNode = new TreeNode(preorder[curIndex++]);
    // if (start == end) {
    // return tNode;
    // }

    // int index = hMap.get(tNode.val);
    // tNode.left = buildTreeUtil(inorder, preorder, start, index - 1);
    // tNode.right = buildTreeUtil(inorder, preorder, index + 1, end);

    // return tNode;

    // }

    // // Function to find the height of a binary tree.
    // int height(Node node) {
    // if (node == null)
    // return 0;

    // int left = height(node.left);
    // int right = height(node.right);

    // return Math.max(left, right) + 1;
    // }

    int diameter(Node root) {
        res = 0;
        height(root);
        return res;
    }

    static int res = 0;

    int height(Node node) {
        if (node == null)
            return 0;

        int left = height(node.left);
        int right = height(node.right);

        res = Math.max(res, 1 + left + right);

        return Math.max(left, right) + 1;
    }

    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Map<Integer, Node> hMap = new TreeMap<>();

        Queue<Pair> qDeque = new LinkedList<>();
        qDeque.add(new Pair(root, 0));

        while (!qDeque.isEmpty()) {
            Pair cur = qDeque.remove();

            if (!hMap.containsKey(cur.vertix)) {
                hMap.put(cur.vertix, cur.node);
            }

            if (cur.node.left != null) {
                qDeque.add(new Pair(cur.node.left, cur.vertix - 1));
            }

            if (cur.node.right != null) {
                qDeque.add(new Pair(cur.node.right, cur.vertix + 1));
            }

        }

        for (Map.Entry<Integer, Node> pair : hMap.entrySet()) {
            res.add(pair.getValue().data);
        }

        return res;
    }

    // Function to return a list containing the bottom view of the given tree.
    public ArrayList<Integer> bottomView(Node root) {
        // Code here
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Map<Integer, Node> hMap = new TreeMap<>();

        Queue<Pair> qDeque = new LinkedList<>();
        qDeque.add(new Pair(root, 0));

        while (!qDeque.isEmpty()) {
            Pair cur = qDeque.remove();

            // if(!hMap.containsKey(cur.vertix)){
            hMap.put(cur.vertix, cur.node);
            // }

            if (cur.node.left != null) {
                qDeque.add(new Pair(cur.node.left, cur.vertix - 1));
            }

            if (cur.node.right != null) {
                qDeque.add(new Pair(cur.node.right, cur.vertix + 1));
            }

        }

        for (Map.Entry<Integer, Node> pair : hMap.entrySet()) {
            res.add(pair.getValue().data);
        }

        return res;
    }

    public ArrayList<Integer> diagonal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        diagonalUtil(root, 0, map);

        for (Map.Entry<Integer, ArrayList<Integer>> mEntry : map.entrySet()) {
            res.addAll(mEntry.getValue());
        }

        return res;
    }

    public static void diagonalUtil(Node root, int d, TreeMap<Integer, ArrayList<Integer>> map) {
        if (root == null)
            return;

        ArrayList<Integer> list = map.get(d);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(root.data);
        map.put(d, list);
        diagonalUtil(root.left, d + 1, map);
        diagonalUtil(root.right, d, map);
    }

    /*
     * First will print all the left boundary node
     * Will print all the leaf nodes
     * Print all the right boundary node
     */
    static ArrayList<Integer> resList;

    ArrayList<Integer> boundary(Node node) {
        resList = new ArrayList<>();
        resList.add(node.data);
        printAllBoundaryLeft(node.left);

        printLeaves(node.left);
        printLeaves(node.right);

        printAllBoundaryRight(node.right);

        return resList;
    }

    public static void printAllBoundaryLeft(Node node) {
        if (node == null)
            return;

        if (node.left != null) {
            resList.add(node.data);
            printAllBoundaryLeft(node.left);
        } else if (node.right != null) {
            resList.add(node.data);
            printAllBoundaryLeft(node.right);
        }
    }

    public static void printAllBoundaryRight(Node node) {
        if (node == null)
            return;

        if (node.right != null) {
            printAllBoundaryRight(node.right);
            resList.add(node.data);
        } else if (node.left != null) {
            printAllBoundaryRight(node.left);
            resList.add(node.data);
        }
    }

    public static void printLeaves(Node node) {
        if (node == null) {
            return;
        }

        printLeaves(node.left);
        if (node.left == null && node.right == null)
            resList.add(node.data);
        printLeaves(node.right);
    }

    public static int findIndex(String str, int start, int end) {
        if (start > end)
            return -1;

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = start; i <= end; i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')') {
                if (stack.peek() == '(') {
                    stack.pop();

                    if (stack.isEmpty()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static Node constructTreeFromString(String str, int start, int end) {
        if (start > end)
            return null;

        int num = 0;
        while (start <= end && str.charAt(start) >= '0' && str.charAt(end) < '9') {
            num *= 10;
            num += str.charAt(start) - '0';
            start++;
        }
        start--;
        Node root = new Node(num);
        int index = -1;

        if (start + 1 <= end && str.charAt(start + 1) == '(')
            index = findIndex(str, start, end);

        if (index != -1) {
            root.left = constructTreeFromString(str, start + 2, index - 1);
            root.right = constructTreeFromString(str, index + 2, end - 1);
        }

        return root;
    }

    /* This function is here just to test */
    static void preOrder(Node node) {
        if (node == null)
            return;
        System.out.printf("%d ", node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static int minSwapsUtil(ArrayList<Integer> inorderList) {
        int n = inorderList.size();
        // pair first element is value and second is its original index
        ArrayList<Pair2> pArrayList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            pArrayList.add(new Pair2(inorderList.get(i), i));

        pArrayList.sort(new Comparator<Pair2>() {

            @Override
            public int compare(Pair2 o1, Pair2 o2) {
                return o1.value - o2.value;
            }
        });

        boolean vis[] = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (vis[i] || pArrayList.get(i).value == i) // or if(vis[i] || pArrayList.get(i).index == i)
                continue;

            int cycle_size = 0;
            int j = i;
            while (!vis[j]) {
                vis[j] = true;
                j = pArrayList.get(j).index;
                cycle_size++;
            }

            if (cycle_size > 0) {
                ans += cycle_size - 1;
            }
        }
        return ans;
    }

    public static int minSwaps(int n, int[] A) {
        ArrayList<Integer> inordIntegers = new ArrayList<>();
        inorder(n, A, 0, inordIntegers);
        return minSwapsUtil(inordIntegers);

    }

    public static void inorder(int n, int[] A, int index, ArrayList<Integer> list) {
        if (index >= n)
            return;

        inorder(n, A, 2 * index + 1, list);
        list.add(A[index]);
        inorder(n, A, 2 * index + 2, list);

    }

    // we will using hashing to find the duplicates
    int dupSub(Node root) {
        HashSet<String> hSet = new HashSet<>();
        String res = dupSubUtil(root, hSet);
        if (res.equals("")) {
            return 1;
        } else {
            return 0;
        }
    }

    static String MARKER = "$";

    public static String dupSubUtil(Node root, HashSet<String> hSet) {
        String s = "";
        if (root == null)
            return s + MARKER;

        String leftS = dupSubUtil(root.left, hSet);
        if (leftS.equals(s))
            return s;

        String rightS = dupSubUtil(root.right, hSet);
        if (rightS.equals(s))
            return s;

        s = s + root.data + leftS + rightS;

        if (s.length() > 3 && hSet.contains(s))
            return "";

        hSet.add(s);
        return s;
    }

    // Function to return the lowest common ancestor in a Binary Tree.
    Node lca(Node root, int n1, int n2) {
        if (root == null || root.data == n1 || root.data == n2)
            return root;

        Node lNode = lca(root.left, n1, n2);
        Node rNode = lca(root.right, n1, n2);

        if (lNode == null) {
            return rNode;
        } else if (rNode == null) {
            return lNode;
        } else {
            return root;
        }
    }

    int LCA_DISTANCE(Node root, int a) {
        if (root == null)
            return 0;

        if (root.data == a)
            return 1;

        int leftValue = LCA_DISTANCE(root.left, a);
        int rightValue = LCA_DISTANCE(root.right, a);

        if (leftValue == 0 && rightValue == 0)
            return 0;

        return leftValue + rightValue + 1;
    }

    int findDist(Node root, int a, int b) {
        Node lca = lca(root, a, b);

        int LeftDistance = LCA_DISTANCE(lca, a);
        int rightDistance = LCA_DISTANCE(lca, b);

        return LeftDistance + rightDistance - 2;
    }

    static String printAllDupsUtil(Node root, ArrayList<Node> list, HashMap<String, Integer> hashMap) {
        if (root == null)
            return "N";

        String s = root.data + "_" + printAllDupsUtil(root.left, list, hashMap) + "_"
                + printAllDupsUtil(root.right, list, hashMap);
        if (hashMap.getOrDefault(s, 0) == 1) {
            list.add(root);
        }
        hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);

        return s;
    }

    public List<Node> printAllDups(Node root) {
        ArrayList<Node> list = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        printAllDupsUtil(root, list, hashMap);
        Collections.sort(list, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                // TODO Auto-generated method stub
                return o1.data - o2.data;
            }

        });
        return list;
    }

    static int k = 3;

    public static Node kthAnchester(Node root, int node) {
        if (root == null)
            return null;

        if (root.data == node || kthAnchester(root.left, node) != null || kthAnchester(root.right, node) != null) {
            if (k > 0) {
                k--;
            } else if (k == 0) {
                System.out.println("Kth Anchester is " + root.data);
                return null;
            }
            return root;
        }
        return null;
    }

    static int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return result;
    }

    static int maxPathSumUtil(TreeNode root) {
        if (root == null)
            return 0;

        int l = maxPathSumUtil(root.left);
        int r = maxPathSumUtil(root.right);

        int max_single = Math.max(Math.max(l, r) + root.val, root.val);

        int max_top = Math.max(max_single, l + r + root.val);

        result = Math.max(result, max_top);

        return max_single;
    }

    int findMaxSum(Node node) {
        result = Integer.MIN_VALUE;
        findMaxSumUtil(node);
        return result;
    }

    static int findMaxSumUtil(Node root) {
        if (root == null)
            return 0;

        int l = findMaxSumUtil(root.left);
        int r = findMaxSumUtil(root.right);

        int max_single = Math.max(Math.max(l, r) + root.data, root.data);

        int max_top = Math.max(max_single, l + r + root.data);

        result = Math.max(result, max_top);

        return max_single;
    }

    public static void main(String[] args) {
        // String str = "4(2(3)(1))(6(5))";
        // Node root = constructTreeFromString(str, 0, str.length() - 1);
        // preOrder(root);

        // Graph g1 = new Graph(5);
        // g1.addEdge(1, 0);
        // g1.addEdge(0, 2);
        // g1.addEdge(0, 3);
        // g1.addEdge(3, 4);
        // if (g1.isTree())
        // System.out.println("Graph is Tree");
        // else
        // System.out.println("Graph is not Tree");

        // Graph g2 = new Graph(5);
        // g2.addEdge(1, 0);
        // g2.addEdge(0, 2);
        // g2.addEdge(2, 1);
        // g2.addEdge(0, 3);
        // g2.addEdge(3, 4);
        // if (g2.isTree())
        // System.out.println("Graph is Tree");
        // else
        // System.out.println("Graph is not Tree");
    }
}

class Pair {
    Node node;
    int vertix;

    public Pair(Node node, int vertix) {
        this.node = node;
        this.vertix = vertix;
    }
}

class Pair2 {
    int value;
    int index;

    public Pair2(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

class Graph {
    public static int V; // No. of vertices
    public static int E; // No. of edges
    public static ArrayList<ArrayList<Integer>> adj; // Pointer to an array for adjacency lists

    // Constructor
    public Graph(int V) {
        E = 0;
        this.V = V;
        adj = new ArrayList<ArrayList<Integer>>(V);
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());
    }

    // A recursive dfs function that uses visited[] and
    // parent to traverse the graph and mark visited[v] to
    // true for visited nodes
    static void dfsTraversal(int v, boolean[] visited, int parent) {
        visited[v] = true;

        for (Integer b : adj.get(v)) {
            if (!visited[b]) {
                dfsTraversal(b, visited, v);
            }
        }

    }

    // to add an edge to graph
    public static void addEdge(int v, int w) {
        E++; // increase the number of edges
        adj.get(w).add(v); // Add w to v list.
        adj.get(v).add(w); // Add v to w list.
    }

    // Returns true if the graph is connected, else false.
    public static boolean isConnected() {
        boolean visited[] = new boolean[V];

        dfsTraversal(0, visited, -1);

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    public static boolean isTree() {
        return E == V - 1 && isConnected();
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String str = "";
        ArrayList<String> list = new ArrayList<>();
        traverse(root, list);
        for (String s : list) {
            str += s;
        }
        return str;

    }

    static void traverse(TreeNode root, ArrayList<String> list) {
        if (root == null) {
            list.add("#" + ",");
            return;
        }
        list.add(root.val + ",");
        traverse(root.left, list);
        traverse(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        ArrayDeque<String> q = new ArrayDeque<>();
        for (String s : arr) {
            q.add(s);
        }
        TreeNode root = helper(q);
        return root;
    }

    static TreeNode helper(ArrayDeque<String> q) {
        String str = q.poll();
        if (str.equals("#"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = helper(q);
        root.right = helper(q);
        return root;
    }
}