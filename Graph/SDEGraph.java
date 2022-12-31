package Graph;

import java.util.*;

public class SDEGraph {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        boolean visited[] = new boolean[V];
        aDeque.add(0);
        visited[0] = true;

        while (!aDeque.isEmpty()) {
            int val = aDeque.remove();
            res.add(val);

            for (Integer edge : adj.get(val)) {
                if (!visited[edge]) {
                    aDeque.add(edge);
                    visited[edge] = true;
                }
            }
        }
        return res;
    }

    public static void DFSUtil(int V, int index, ArrayList<ArrayList<Integer>> adj, boolean visited[],
            ArrayList<Integer> res) {
        visited[index] = true;
        res.add(index);

        for (Integer edInteger : adj.get(index)) {
            if (!visited[edInteger]) {
                DFSUtil(V, edInteger, adj, visited, res);
            }
        }
    }

    public static void DFSUtilIter(int V, int index, ArrayList<ArrayList<Integer>> adj, boolean visited[],
            ArrayList<Integer> res) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(index);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (visited[curr]) {
                continue;
            }

            res.add(curr);
            visited[curr] = true;
            ArrayList<Integer> list = adj.get(curr);
            for (int i = list.size() - 1; i >= 0; i--) {
                if (!visited[list.get(i)]) {
                    stack.push(list.get(i));
                }
            }
            System.out.println();
        }

    }

    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean visited[] = new boolean[V];
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFSUtilIter(V, i, adj, visited, res);
            }
        }

        return res;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        int prevColor = image[sr][sc];
        if (color != prevColor) {
            stack.push(new Node(sr, sc));
        }

        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (image[n.index1][n.index2] == prevColor) {
                image[n.index1][n.index2] = color;
                if (n.index1 >= 1)
                    stack.push(new Node(n.index1 - 1, n.index2));
                if (n.index2 >= 1)
                    stack.push(new Node(n.index1, n.index2 - 1));
                if (n.index1 + 1 < image.length)
                    stack.push(new Node(n.index1 + 1, n.index2));
                if (n.index2 + 1 < image[n.index1].length)
                    stack.push(new Node(n.index1, n.index2 + 1));
            }
        }
        return image;
    }

    public static boolean isCycleUtil(ArrayList<ArrayList<Integer>> adj, boolean visited[],
            int curr, int parent) {
        visited[curr] = true;

        for (Integer edj : adj.get(curr)) {
            if (!visited[edj]) {
                if (isCycleUtil(adj, visited, edj, curr))
                    return true;
            } else if (edj != parent) {
                return true;
            }
        }

        return false;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCycleUtil(adj, visited, i, -1))
                    return true;
            }
        }

        return false;
    }

    // Function to find out minimum steps Knight needs to reach target position.
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
        boolean visited[][] = new boolean[N + 1][N + 1];
        int step = 0;

        ArrayDeque<Node> qDeque = new ArrayDeque<>();

        int x[] = { 2, -2, 2, -2, -1, 1, 1, -1 };
        int y[] = { -1, 1, 1, -1, 2, -2, 2, -2 };

        visited[KnightPos[0]][KnightPos[1]] = true;
        qDeque.add(new Node(KnightPos[0], KnightPos[1], step));

        while (!qDeque.isEmpty()) {
            Node pop = qDeque.remove();
            step = pop.step;

            if (pop.index1 == TargetPos[0] && pop.index2 == TargetPos[1]) {
                return step;
            }

            for (int i = 0; i < x.length; i++) {
                int newX = pop.index1 + x[i];
                int newY = pop.index2 + y[i];
                if (newX > 0 && newY > 0 && newX <= N && newY <= N && !visited[newX][newY]) {
                    visited[newX][newY] = true;

                    qDeque.add(new Node(newX, newY, step + 1));
                }
            }
        }

        return -1;
    }

    // public static void cloneGraphUtil(Node original, Node copy, Node visited[]) {
    //     visited[original.val] = copy;

    //     for(Node n : original.neighbors){
    //         if(visited[n.val] == null){
    //             Node newNode = new Node(n.val);
    //             copy.neighbors.add(newNode);
    //             cloneGraphUtil(n, newNode, visited);
    //         }else{
    //             copy.neighbors.add(visited[n.val]);
    //         }
    //     }

    // }

    // public Node cloneGraph(Node node) {
    //     if(node == null) return node;
    //     Node copy = new Node(node.val);

    //     Node visited[] = new Node[101];
    //     Arrays.fill(visited, null);
    //     cloneGraphUtil(node, copy, visited);
    //     return copy;
    // }

}

class Node2 {
    public int val;
    public List<Node2> neighbors;

    public Node2() {
        val = 0;
        neighbors = new ArrayList<Node2>();
    }
    public Node2(int _val) {
        val = _val;
        neighbors = new ArrayList<Node2>();
    }

    public Node2(int _val, ArrayList<Node2> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Node {
    int index1;
    int index2;
    int step;

    public Node(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    public Node(int index1, int index2, int step) {
        this.index1 = index1;
        this.index2 = index2;
        this.step = step;
    }

}
