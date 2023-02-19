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
    // visited[original.val] = copy;

    // for(Node n : original.neighbors){
    // if(visited[n.val] == null){
    // Node newNode = new Node(n.val);
    // copy.neighbors.add(newNode);
    // cloneGraphUtil(n, newNode, visited);
    // }else{
    // copy.neighbors.add(visited[n.val]);
    // }
    // }

    // }

    // public Node cloneGraph(Node node) {
    // if(node == null) return node;
    // Node copy = new Node(node.val);

    // Node visited[] = new Node[101];
    // Arrays.fill(visited, null);
    // cloneGraphUtil(node, copy, visited);
    // return copy;
    // }

    public static boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;

        return false;
    }

    public static boolean halvesAreAlike(String s) {
        if (s.length() == 1)
            return false;

        s = s.toLowerCase();
        int mid = s.length() / 2;

        int firstCOunt = 0;
        int secondCount = 0;

        for (int i = 0; i < mid; i++) {
            if (isVowel(s.charAt(i))) {
                firstCOunt++;
            }
        }

        for (int i = mid; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                secondCount++;
            }
        }

        return firstCOunt == secondCount;

    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        int freq1[] = new int[26];
        int freq2[] = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            freq1[word1.charAt(i) - 'a']++;
            freq2[word2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < freq2.length; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq2[i] == 0 && freq1[i] != 0))
                return false;
        }

        Arrays.sort(freq1);
        Arrays.sort(freq2);

        for (int i = 0; i < freq2.length; i++) {
            if (freq1[i] != freq2[i])
                return false;
        }

        return true;

    }

    public static void makeConnectedUtil(int V, int index, ArrayList<ArrayList<Integer>> adj, boolean visited[]) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(index);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (visited[curr]) {
                continue;
            }

            visited[curr] = true;
            ArrayList<Integer> list = adj.get(curr);
            for (int i = list.size() - 1; i >= 0; i--) {
                if (!visited[list.get(i)]) {
                    stack.push(list.get(i));
                }
            }
        }
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    public int makeConnected(int n, int[][] connections) {
        boolean visited[] = new boolean[n];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        if (connections.length < n - 1) { // if there are n nodes we need atleast n-1 connections so that all of them
                                          // are connected
            return -1;
        }

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < connections.length; i++) {
            addEdge(adj, connections[i][0], connections[i][1]);
        }

        int count = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                makeConnectedUtil(n, i, adj, visited);
            }
        }

        return count;
    }

    public static int pivotIndex(int[] nums) {
        int leftSum[] = new int[nums.length];
        int rightSum[] = new int[nums.length];

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum[i] = sum;
            sum += nums[i];
        }

        sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            rightSum[i] = sum;
            sum += nums[i];
        }
        System.out.println(Arrays.toString(leftSum));
        System.out.println(Arrays.toString(rightSum));

        for (int i = 0; i < nums.length; i++) {
            if (leftSum[i] == rightSum[i])
                return i;
        }
        return -1;
    }

    public static ArrayList<ArrayList<Integer>> make_graph(int N, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            paths.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            paths.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        return paths;
    }

    public static boolean dfs_cycle(int node, ArrayList<ArrayList<Integer>> paths, boolean onPath[],
            boolean visited[]) {
        if (visited[node]) {
            return false;
        }

        onPath[node] = visited[node] = true;

        for (Integer edj : paths.get(node)) {
            if (onPath[edj] || dfs_cycle(edj, paths, onPath, visited)) {
                return true;
            }
        }
        onPath[node] = false;

        return false;
    }

    public boolean isPossible(int N, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> paths = make_graph(N, prerequisites);

        boolean onPath[] = new boolean[N];
        boolean visited[] = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!visited[i] && dfs_cycle(i, paths, onPath, visited))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // String str = "tree";
        // System.out.println(frequencySort(str));

        int arr[] = { 1, 7, 3, 6, 5, 6 };
        System.out.println(pivotIndex(arr));

    }

    public int isNegativeWeightCycle(int n, int[][] edges) {
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        for (int j = 0; j < edges.length; j++) {
            int u = edges[j][0];
            int v = edges[j][1];
            int weight = edges[j][2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                return 1;
            }
        }

        return 0;
    }

    public void shortest_distance(int[][] matrix) {
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int k = 0; k < matrix.length; k++) {
                    if (matrix[i][j] == -1 || matrix[j][k] == -1)
                        continue;
                    if (matrix[i][k] == -1 || (matrix[i][j] + matrix[j][k] < matrix[i][k])) {
                        matrix[i][k] = matrix[i][j] + matrix[j][k];
                    }
                }
            }
        }
    }

    public static ArrayList<ArrayList<Pair>> makeAdjEdged(int V, int edges[][]) {
        ArrayList<ArrayList<Pair>> adList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            adList.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adList.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }

        return adList;
    }

    static int spanningTree(int V, int E, int edges[][]) {
        ArrayList<ArrayList<Pair>> adList = makeAdjEdged(V, edges);
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((Pair p1, Pair p2) -> p1.y - p2.y);
        boolean visited[] = new boolean[V];
        int ans = 0;
        minHeap.add(new Pair(0, 0));

        while (!minHeap.isEmpty()) {
            Pair curr = minHeap.remove();
            // System.out.println(curr);
            if (visited[curr.x])
                continue;
            ans += curr.y;
            visited[curr.x] = true;

            for (Pair pair : adList.get(curr.x)) {
                if (!visited[pair.x]) {
                    minHeap.add(new Pair(pair.x, pair.y));
                }
            }
        }
        return ans;
    }

    public static int getCoordinateValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - r - 1;

        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        return board[x][y];
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num])
                    continue;
                visited[num] = true;

                if (num == n * n)
                    return move;

                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getCoordinateValue(board, next);
                    if (value > 0)
                        next = value;
                    if (!visited[next])
                        queue.offer(next);
                }
            }
        }
        return -1;
    }

    public static void dfs(int V, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[V] = true;
        for (Integer u : adj.get(V)) {
            if (!visited[u])
                dfs(u, adj, visited, stack);
        }

        stack.push(V);
    }

    public static void revdfs(int V, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[V] = true;
        for (Integer u : adj.get(V)) {
            if (!visited[u])
                revdfs(, adj, visited);
        }
    }

    // Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        // first we need to find sorting execution using topological sort dfs ( in a
        // stack)
        // then we will do transpose of edges
        // then we will traverse dfs in topological sort and increment the count and
        // return the count

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                dfs(i, adj, visited, stack);
        }
        ArrayList<ArrayList<Integer>> transpose_adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            transpose_adj.add(new ArrayList<>());
        }

        for (int i = 0; i < adj.size(); i++) {
            visited[i] = false;
            for (Integer u : adj.get(i)) {
                transpose_adj.get(u).add(i);
            }
        }

        int count = 0;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!visited[u]) {
                count++;
                revdfs(u, transpose_adj, visited);
            }
        }

        return count;
    }

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        int res = 0;
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                res++;
                revdfs(i, adj, visited);
            }
        }

        return res;
    }

    public static boolean isSafe(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
            return false;

        return true;
    }

    public static void numIslandsBfs(char[][] grid, int r, int c) {
        grid[r][c] = '0';
        int rows[] = {-1,1,0,0,-1,-1,1,1};
        int cols[] = {0,0,-1,1,-1,1,1,-1};
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(r, c));

        while (!queue.isEmpty()) {
            Pair pair =  queue.pop();

            for (int k = 0; k < rows.length; k++) {
                int row  = pair.x + rows[k];
                int col  = pair.y + cols[k];
                isSafe(grid, row, col){
                    grid[row][col] = '0';
                    queue.add(new Pair(row, col));
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numIslandsBfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public static boolean isSafeFloodFill(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
            return false;

        return true;
    }

    public static int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        int rows[] = { 0, 0, -1, 1 };
        int cols[] = { -1, 1, 0, 0 };
        ArrayDeque<Pair> qDeque = new ArrayDeque<>();
        if (oldColor != newColor) {
            qDeque.add(new Pair(sr, sc));
        }

        while (!qDeque.isEmpty()) {
            Pair par = qDeque.remove();
            if (image[par.x][par.y] != oldColor) {
                continue;
            }
            image[par.x][par.y] = newColor;

            for (int i = 0; i < 4; i++) {
                int r = par.x + rows[i];
                int c = par.y + cols[i];

                if (isSafeFloodFill(image, r, c)) {
                    qDeque.add(new Pair(r, c));
                }
            }
        }

        return image;
    }

    public static boolean isDelm(Pair pair) {
        if (pair.x == -1 && pair.y == -1)
            return true;

        return false;
    }

    public static boolean isSafeOrangeRotting(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1)
            return false;

        return true;
    }

    public static boolean isCheck(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    // Function to find minimum time required to rot all oranges.
    public int orangesRotting(int[][] grid) {
        int time = 0;
        ArrayDeque<Pair> qDeque = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    qDeque.add(new Pair(i, j));
                }
            }
        }

        // add delemeter
        qDeque.add(new Pair(-1, -1));

        System.out.println(qDeque.toString());

        int rows[] = { 0, 0, -1, 1 };
        int cols[] = { -1, 1, 0, 0 };

        while (!qDeque.isEmpty()) {
            boolean flag = false;

            while (!isDelm(qDeque.peek())) {
                Pair curr = qDeque.remove();

                for (int i = 0; i < 4; i++) {
                    if (isSafeOrangeRotting(grid, curr.x + rows[i], curr.y + cols[i])) {
                        grid[curr.x + rows[i]][curr.y + cols[i]] = 2;
                        if (!flag) {
                            flag = true;
                            time++;
                        }
                        qDeque.add(new Pair(curr.x + rows[i], curr.y + cols[i]));
                    }
                }
            }

            // remove del
            qDeque.remove();

            if (!qDeque.isEmpty() && flag) {
                qDeque.add(new Pair(-1, -1));
            }
        }

        return isCheck(grid) ? time : -1;
    }

    public static boolean isSafeNearest(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 0)
            return false;

        return true;
    }

    // Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid) {
        ArrayDeque<Pair> qDeque = new ArrayDeque<>();
        int count = 0;
        int rows[] = { -1, 1, 0, 0 };
        int cols[] = { 0, 0, -1, 1 };

        // put in a queue all the cell having 1 initially
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    qDeque.add(new Pair(i, j));
                    grid[i][j] = -1;
                }
            }
        }

        // put delima
        qDeque.add(new Pair(-1, -1));

        // run the while loop till queue is not empty
        while (!qDeque.isEmpty()) {
            count++;
            boolean flag = false;
            while (isDelm(qDeque.peekFirst())) {
                Pair curr = qDeque.remove();

                for (int i = 0; i < cols.length; i++) {
                    flag = true;
                    int row = curr.x + rows[i];
                    int col = curr.y + cols[i];
                    if (isSafeNearest(grid, row, col)) {
                        grid[row][col] = count;
                        qDeque.add(new Pair(row, col));
                    }
                }

            }

            // remove delima
            qDeque.remove();

            if (flag) {
                qDeque.add(new Pair(-1, -1));
            }
        }

        // replace -1 with -
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == -1) {
                    grid[i][j] = 0;
                }
            }
        }
        return grid;
    }

    public static boolean isSafeFill(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
            return false;

        return true;
    }

    public static void fillUtil(char a[][], int n, int m, char prev, char newChar) {
        int rows[] = { -1, 1, 0, 0 };
        int cols[] = { 0, 0, -1, 1 };

        if (prev != a[n][m])
            return;

        a[n][m] = newChar;

        for (int i = 0; i < cols.length; i++) {
            if (isSafe(a, n + rows[i], m + cols[i])) {
                fillUtil(a, n + rows[i], m + cols[i], prev, newChar);
            }
        }

    }

    static char[][] fill(int n, int m, char a[][]) {
        // replace all 'O' with '-'
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 'O') {
                    a[i][j] = '-';
                }
            }
        }

        // left edge
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] == '-') {
                fillUtil(a, i, 0, '-', 'O');
            }
        }

        // right edge
        for (int i = 0; i < a.length; i++) {
            if (a[i][m - 1] == '-') {
                fillUtil(a, i, 0, '-', 'O');
            }
        }

        // top edge
        for (int i = 0; i < m; i++) {
            if (a[0][i] == '-') {
                fillUtil(a, i, 0, '-', 'O');
            }
        }

        // bottom edge
        for (int i = 0; i < m; i++) {
            if (a[n - 1][i] == '-') {
                fillUtil(a, i, 0, '-', 'O');
            }
        }

        // replace all '-' with 'X'
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == '-') {
                    a[i][j] = 'X';
                }
            }
        }

        return a;
    }

    public static void numberOfEnclavesBfsUtil(int[][] grid, int i, int j) {
        grid[i][j] = 0;

        int rows[] = { 0, 0, -1, 1 };
        int cols[] = { -1, 1, 0, 0 };

        for (int k = 0; k < cols.length; k++) {
            int row = i + rows[k];
            int col = j + cols[k];

            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
                numberOfEnclavesBfsUtil(grid, row, col);
            }
        }
    }

    int numberOfEnclaves(int[][] grid) {
        /*
         * we will traverse all the land cells on the boundary
         * and all land cells connected to the boundary land cells will be eleminated
         * sot he remaning land cells will be our answer
         */

        // traverse all boundary cells

        // top and bottom boundary
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                numberOfEnclavesBfsUtil(grid, 0, i);
            }

            if (grid[grid.length - 1][i] == 1) {
                numberOfEnclavesBfsUtil(grid, grid.length - 1, i);
            }
        }

        // left and right boundary
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 1) {
                numberOfEnclavesBfsUtil(grid, i, 0);
            }

            if (grid[i][grid[0].length - 1] == 1) {
                numberOfEnclavesBfsUtil(grid, i, grid[0].length);
            }
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static String toStringUti(int r, int c) {
        return Integer.toString(r) + " " + Integer.toString(c);
    }

    public static void countDistinctIslandsDfsUtil(int[][] grid, int i, int j, int rowP, int colP,
            ArrayList<String> list) {
        grid[i][j] = 0;
        list.add(toStringUti(i - rowP, j - colP));
        int rows[] = { 0, 0, -1, 1 };
        int cols[] = { -1, 1, 0, 0 };

        for (int k = 0; k < cols.length; k++) {
            int r = i + rows[k];
            int c = j + cols[k];

            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                countDistinctIslandsDfsUtil(grid, r, c, rowP, colP, list);
            }
        }
    }

    // approch is simple we will traverse throught the grid is it is 1 then we will
    // do dfs traversal
    // for unique island we will store it in hashlist , that is arraylist of string

    int countDistinctIslands(int[][] grid) {
        HashSet<ArrayList<String>> hashSet = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ArrayList<String> list = new ArrayList<>();
                    countDistinctIslandsDfsUtil(grid, i, j, i, j, list);
                    hashSet.add(list);
                }
            }
        }

        return hashSet.size();
    }

    public static boolean fillColor(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {
        color[v] = 0;
        ArrayDeque<Integer> qDeque = new ArrayDeque<>();
        qDeque.add(v);

        while (!qDeque.isEmpty()) {
            int vertex = qDeque.remove();
            int vertexColor = color[vertex];

            for (Integer edgV : adj.get(vertex)) {
                if (color[edgV] == -1) {
                    color[edgV] = 1 - vertexColor;
                    qDeque.add(edgV);
                } else if (color[edgV] == vertexColor) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!fillColor(i, adj, color))
                    return false;
            }
        }
        return true;
    }

    // check if there is cycle in using dfs in directed graph
    public static boolean eventualSafeNodesDfsUtil(int node, List<List<Integer>> adj, boolean[] visited,
            boolean[] recursion, boolean[] check) {
        visited[node] = true;
        recursion[node] = true;
        check[node] = false;

        for (Integer edjNode : adj.get(node)) {
            if (!visited[edjNode]) {
                if (eventualSafeNodesDfsUtil(edjNode, adj, visited, recursion, check))
                    return true;
            } else if (recursion[edjNode]) {
                return true;
            }
        }
        check[node] = true;
        recursion[node] = false;
        return false;
    }

    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] recursion = new boolean[V];
        boolean[] check = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                eventualSafeNodesDfsUtil(i, adj, visited, recursion, check);
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                list.add(i);
            }
        }

        return list;
    }

    public static void topoSortDfsUtil(int node, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack,
            boolean[] visited) {
        visited[node] = true;

        for (Integer adjNode : adj.get(node)) {
            if (!visited[adjNode]) {
                topoSortDfsUtil(adjNode, adj, stack, visited);
            }
        }

        stack.push(node);
    }

    // Function to return list containing vertices in Topological order. simple dfs
    // based approach
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSortDfsUtil(i, adj, stack, visited);    
            }
        }
        int res[] = new int[V];
        int i = 0 ;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }

        return res;
    }
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

// class Pair {
// char ch;
// int count;

// public Pair(char ch, int count) {
// this.ch = ch;
// this.count = count;
// }

// }

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
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
