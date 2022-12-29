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

}
