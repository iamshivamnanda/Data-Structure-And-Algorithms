package Backtracking;

import java.util.*;

public class BackTrackingSDESheet {
    public static boolean isSafe(int[][] m, int n, int i, int j, boolean[][] visited) {
        if (i >= 0 && i < n && j >= 0 && j < n && m[i][j] == 1 && !visited[i][j]) {
            return true;
        }

        return false;

    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> res = new ArrayList<>();
        boolean visited[][] = new boolean[n][n];

        String str = "";
        visited[0][0] = true;
        if (m[0][0] == 0) {
            return res;
        }
        util(m, n, str, 0, 0, visited, res);
        return res;

    }

    public static void util(int[][] m, int n, String res, int i, int j, boolean visited[][], ArrayList<String> list) {

        if (i == n - 1 && j == n - 1) {
            if (list.contains(res)) {
                return;
            }

            list.add(res);
            return;
        }
        int x[] = { 0, 0, 1, -1 };
        int y[] = { 1, -1, 0, 0 };
        String dire[] = { "R", "L", "D", "U" };

        for (int k = 0; k < 4; k++) {
            if (isSafe(m, n, i + x[k], j + y[k], visited)) {

                visited[i + x[k]][j + y[k]] = true;
                util(m, n, res + dire[k], i + x[k], j + y[k], visited, list);
                visited[i + x[k]][j + y[k]] = false;
            }
        }

    }

    public static void findSum(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp, ArrayList<Integer> A,
            int sum, int index) {
        if (sum == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < A.size(); i++) {
            if (sum - A.get(i) >= 0) {
                temp.add(A.get(i));
                findSum(res, temp, A, sum - A.get(i), i);
                temp.remove(A.get(i));
            }
        }
    }

    static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>(A);
        A.clear();
        A.addAll(set);
        Collections.sort(A);

        findSum(result, temp, A, B, 0);
        return result;
    }

    public static Pair longestPathUtil(int[][] mat, int n, int m, int xs, int ys, int xd, int yd, boolean visited[][]) {
        if (xs < 0 || xs >= n || ys < 0 || ys >= m || mat[xs][ys] == 0 || visited[xs][ys]) {
            return new Pair(false, Integer.MAX_VALUE);
        }
        if (xs == xd && ys == yd) {
            return new Pair(true, 0);
        }

        visited[xs][ys] = true;
        int res = Integer.MIN_VALUE;

        int x[] = { 0, 0, 1, -1 };
        int y[] = { 1, -1, 0, 0 };

        for (int i = 0; i < 4; i++) {
            Pair pair = longestPathUtil(mat, n, m, xs + x[i], ys + y[i], xd, yd, visited);
            if (pair.found) {
                res = Math.max(res, pair.value);
            }
        }
        visited[xs][ys] = false;
        if (res != Integer.MIN_VALUE) {
            return new Pair(true, res + 1);
        } else {
            return new Pair(false, Integer.MAX_VALUE);
        }

    }

    public static int longestPath(int[][] mat, int n, int m, int xs, int ys, int xd, int yd) {
        boolean visited[][] = new boolean[n][m];

        Pair res = longestPathUtil(mat, n, m, xs, ys, xd, yd, visited);
        if (res.found) {
            return res.value;
        } else {
            return -1;
        }
    }

    public static void nQueenUtil(ArrayList<ArrayList<Integer>> result, int n, int row, boolean cols[], boolean leftD[],
            boolean rightD[], ArrayList<Integer> comb) {
        if (row == n) {
            result.add(new ArrayList<>(comb));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols[col] || leftD[col + row] || rightD[row - col + n]) {
                continue;
            }
            cols[col] = leftD[col + row] = rightD[row - col + n] = true;
            comb.set(row, col + 1);
            nQueenUtil(result, n, row + 1, cols, leftD, rightD, comb);
            cols[col] = leftD[col + row] = rightD[row - col + n] = false;

        }
    }

    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean cols[] = new boolean[n];
        boolean leftD[] = new boolean[2 * n];
        boolean rightD[] = new boolean[2 * n];
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temp.add(0);
        }
        nQueenUtil(result, n, 0, cols, leftD, rightD, temp);
        return result;

    }

    public static List<String> checkVertically(int x, int y, List<String> original, String word) {
        int n = word.length();
        List<String> crossword = new ArrayList<>(original);

        for (int i = 0; i < n; i++) {
            char ch[] = crossword.get(x + i).toCharArray();
            if (ch[y] == '-' || ch[y] == word.charAt(i)) {
                ch[y] = word.charAt(i);
                crossword.set(x + i, new String(ch));
            } else {
                char chr[] = crossword.get(0).toCharArray();
                chr[0] = '@';
                crossword.set(0, new String(chr));
            }
        }
        return crossword;
    }

    public static List<String> checkHorizontal(int x, int y, List<String> original, String word) {
        int n = word.length();
        List<String> crossword = new ArrayList<>(original);
        char ch[] = crossword.get(x).toCharArray();
        for (int i = 0; i < n; i++) {
            if (ch[y + i] == '-' || ch[y + i] == word.charAt(i)) {
                ch[y + i] = word.charAt(i);
            } else {
                char chr[] = crossword.get(0).toCharArray();
                chr[0] = '@';
                crossword.set(0, new String(chr));
            }
        }
        crossword.set(x, new String(ch));
        return crossword;
    }

    public static List<String> crosswordPuzzle(List<String> crossword, String words) {
        return crosswordPuzzleutil(crossword, words.split(";"), 0);
    }

    public static List<String> crosswordPuzzleutil(List<String> crossword, String words[], int index) {
        if (index < words.length) {
            String word = words[index];
            int maxlen = crossword.size() - word.length();

            // check for vertical posibilty
            for (int i = 0; i < crossword.size(); i++) {
                for (int j = 0; j <= maxlen; j++) {
                    List<String> temp = checkVertically(j, i, crossword, word);
                    if (temp.get(0).charAt(0) != '@') {
                        return crosswordPuzzleutil(temp, words, index + 1);
                    }
                }
            }

            // check for horixontal posibilty
            for (int i = 0; i < crossword.size(); i++) {
                for (int j = 0; j <= maxlen; j++) {
                    List<String> temp = checkHorizontal(i, j, crossword, word);
                    if (temp.get(0).charAt(0) != '@') {
                        return crosswordPuzzleutil(temp, words, index + 1);
                    }
                }
            }
        }
        return crossword;
    }

    public static boolean isSafeSudoku(int[][] grid, int row, int col, int num) {
        // check in row
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // check in col
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // check in submatrix
        int sqrt = (int) Math.sqrt(grid.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int i = rowStart; i < rowStart + sqrt; i++) {
            for (int j = colStart; j < colStart + sqrt; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;

    }

    public static boolean SolveSudokuUtil(int grid[][], int n, int row, int col) {
        if (row == n - 1 && col == n)
            return true;

        if (col == n) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0) {
            SolveSudokuUtil(grid, n, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {
            if (isSafeSudoku(grid, row, col, num)) {
                grid[row][col] = num;
                if (SolveSudokuUtil(grid, n, row, col + 1)) {
                    return true;
                }
                grid[row][col] = 0;

            }
        }

        return false;

    }

    // Function to find a solved Sudoku.
    static boolean SolveSudoku(int grid[][]) {
        return SolveSudokuUtil(grid, grid.length, 0, 0);
    }

    // Function to print grids of the Sudoku.
    static void printGrid(int grid[][]) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid));
        }
    }

    static int equalPartition(int N, int arr[]) {
        int sum = 0;
        for (int no : arr) {
            sum += no;
        }

        if (sum % 2 == 1) {
            return 0;
        }

        return isPartition(arr, N, sum / 2) ? 1 : 0;
    }

    public static boolean isPartition(int arr[], int N, int sum) {
        if (sum == 0) {
            return true;
        }

        if (N == 0 && sum != 0) {
            return false;
        }

        if (arr[N - 1] > sum) {
            return isPartition(arr, N - 1, sum);
        }

        return isPartition(arr, N - 1, sum) || isPartition(arr, N - 1, sum - arr[N - 1]);
    }

    public static boolean graphColoringisPossible(boolean graph[][], int n, int index, int col, int color[]) {
        for (int i = 0; i < n; i++) {
            if (graph[index][i] && color[i] == col) {
                return false;
            }
        }

        return true;
    }

    // m-colouring problem
    public boolean graphColoring(boolean graph[][], int m, int n) {
        int color[] = new int[n];
        return graphColoringUtil(graph, m, n, 0, color);
    }

    public static boolean graphColoringUtil(boolean graph[][], int m, int n, int index, int color[]) {
        if (index == n) {
            return true;
        }

        for (int i = 1; i <= m; i++) {
            if (graphColoringisPossible(graph, n, index, i, color)) {
                color[index] = i;
                if (graphColoringUtil(graph, m, n, index + 1, color)) {
                    return true;
                }
                color[index] = 0;
            }
        }
        return false;
    }

    public static boolean isParenthese(char ch) {
        if (ch == '(' || ch == ')')
            return true;
        return false;
    }

    public static boolean isValid(String str) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count++;
            } else if (str.charAt(i) == ')') {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }
        if (count < 0) {
            return false;
        }
        return true;

    }

    // remove Invalid Parantheses
    public static ArrayList<String> removeInvalidParentheses(String s) {
        ArrayList<String> res = new ArrayList<>();

        ArrayDeque<String> queue = new ArrayDeque<>();
        HashSet<String> visit = new HashSet<>();

        queue.add(s);
        visit.add(s);

        boolean lvl = false;
        while (!queue.isEmpty()) {
            String string = queue.poll();
            if (isValid(string)) {
                res.add(string);
                lvl = true;
            }
            if (lvl) {
                continue;
            }

            for (int i = 0; i < string.length(); i++) {
                if (!isParenthese(string.charAt(i))) {
                    continue;
                }
                String newStr = string.substring(0, i) + string.substring(i + 1);
                if (!visit.contains(newStr)) {
                    queue.add(newStr);
                    visit.add(newStr);
                }
            }
        }
        return res;
    }

    // Word Break
    static List<String> wordBreak(int n, List<String> dict, String s) {
        String ans = "";
        ArrayList<String> res = new ArrayList<>();
        wordBreakUtil(s.length(), dict, s, res, ans);
        return res;
    }

    public static void wordBreakUtil(int n, List<String> dict, String s, List<String> res, String ans) {
        for (int i = 1; i <= n; i++) {
            String str = s.substring(0, i);

            if (dict.contains(str)) {
                if (i == n) {
                    ans += str;
                    res.add(ans);
                    return;
                }

                wordBreakUtil(n - i, dict, s.substring(i, n), res, ans + str + " ");
            }
        }
    }

    static boolean checkPalindrome(String str) {
        int len = str.length();
        len--;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str.charAt(len))
                return false;
            len--;
        }
        return true;
    }

    static ArrayList<ArrayList<String>> allPalindromicPerms(String S) {
        // code here
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        allPalindromicPermsUtil(S, 0, res, temp);
        return res;
    }

    public static void allPalindromicPermsUtil(String S, int index, ArrayList<ArrayList<String>> res,
            ArrayList<String> temp) {
        String str = "";
        ArrayList<String> curr = new ArrayList<>(temp);

        if (index == 0) {
            temp.clear();
        }
        for (int i = index; i < S.length(); i++) {
            str = str + S.charAt(i);

            if (checkPalindrome(str)) {
                temp.add(str);
                if (i + 1 < S.length()) {
                    allPalindromicPermsUtil(S, i + 1, res, temp);
                } else {
                    res.add(temp);
                }
            }

            temp = new ArrayList<>(curr);
        }
    }

    public static boolean isKPartitionPossibleUtil(int a[], int n, int k, int subsetSum[], boolean visited[], int sum,
            int curIndex, int limIndex) {
        if (subsetSum[curIndex] == sum) {

            if (curIndex == k - 2) {
                return true;
            }

            return isKPartitionPossibleUtil(a, n, k, subsetSum, visited, sum, curIndex + 1, n - 1);
        }

        for (int i = limIndex; i >= 0; i--) {
            if (visited[i])
                continue;

            int tem = subsetSum[curIndex] + a[i];
            if (tem <= sum) {
                visited[i] = true;
                subsetSum[curIndex] = tem;
                if (isKPartitionPossibleUtil(a, n, k, subsetSum, visited, sum, curIndex, i - 1)) {
                    return true;
                }
                visited[i] = false;
                subsetSum[curIndex] = tem - a[i];
            }
        }
        return false;
    }

    public boolean isKPartitionPossible(int a[], int n, int k) {
        if (k == 1)
            return true;
        if (n < k)
            return false;

        int sum = 0;
        for (int no : a)
            sum += no;

        if (sum % k != 0)
            return false;

        int subsetSum[] = new int[k];
        boolean visited[] = new boolean[n];
        subsetSum[0] = a[n - 1];
        visited[n - 1] = true;
        int subSum = sum / k;

        return isKPartitionPossibleUtil(a, n, k, subsetSum, visited, subSum, 0, n - 1);
    }

    public static void markadjacent(int[][] mat) {
        int x[] = { 0, 0, 1, -1 };
        int y[] = { 1, -1, 0, 0 };
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // if it is a mine
                if (mat[i][j] == 0) {
                    for (int j2 = 0; j2 < 4; j2++) {
                        if (isValidFSP2(mat, i + x[j2], j + y[j2]))
                            mat[i + x[j2]][j + y[j2]] = 0;
                    }
                }
            }
        }
    }

    public static boolean isValidFSP(int mat[][], boolean[][] visited, int i, int j) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length || visited[i][j] || mat[i][j] == 0) {
            return false;
        }
        return true;
    }

    public static boolean isValidFSP2(int mat[][], int i, int j) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) {
            return false;
        }
        return true;
    }

    public static void findShortestPathUtil(int[][] mat, boolean[][] visited, int i, int j, int dist) {
        if (j == mat[0].length - 1) {
            min_dist = Math.min(min_dist, dist);
            return;
        }

        if (dist > min_dist)
            return;

        int x[] = { 0, 0, 1, -1 };
        int y[] = { 1, -1, 0, 0 };
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            if (isValidFSP(mat, visited, i + x[k], j + y[k])) {
                findShortestPathUtil(mat, visited, i + x[k], j + y[k], dist + 1);
            }
        }
        visited[i][j] = false;

    }

    // static int min_dist;

    // find shortest path
    public static int findShortestPath(int[][] mat) {
        // mark all adjacent elements as unsafe
        markadjacent(mat);

        min_dist = Integer.MAX_VALUE;

        // check for all rows
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][0] == 1) {
                boolean visited[][] = new boolean[mat.length][mat[0].length];
                findShortestPathUtil(mat, visited, i, 0, 0);

            }
        }

        return min_dist == Integer.MAX_VALUE ? -1 : min_dist;
    }

    boolean dfs(ArrayList<ArrayList<Integer>> adj, int u, int cnt, int N) {
        if (cnt == N)
            return true;
        vis[u] = true;
        for (int i : adj.get(u)) {
            if (!vis[i] && dfs(adj, i, cnt + 1, N))
                return true;
        }
        vis[u] = false;
        return false;
    }

    boolean[] vis;

    boolean check(int N, int M, ArrayList<ArrayList<Integer>> Edges) {
        vis = new boolean[N + 1];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
        for (ArrayList<Integer> i : Edges) {
            int u = i.get(0);
            int v = i.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        for (int i = 1; i < N; i++) {
            if (dfs(adj, i, 1, N))
                return true;
        }
        return false;
    }

    public static void tugOfWarUtil(ArrayList<Integer> arr, int n, boolean[] visited, int sum, int curSum, int seleted,
            int curIndex) {
        if (curIndex == n)
            return;

        if ((n / 2 - seleted) > (n - curIndex))
            return;

        // not including cur element
        tugOfWarUtil(arr, n, visited, sum, curSum, seleted, curIndex + 1);

        seleted++;
        visited[curIndex] = true;
        curSum += arr.get(curIndex);

        if (seleted == n / 2) {
            if (Math.abs( (sum-curSum)  - curSum) < min_dist) {
                min_dist = Math.abs((sum-curSum) - curSum);
            }
        } else {
            tugOfWarUtil(arr, n, visited, sum, curSum, seleted, curIndex + 1);
        }
        visited[curIndex] = false;

    }

    static int min_dist;

    // find the min diff by making two arrays of one arr
    public static int tugOfWar(ArrayList<Integer> arr, int n) {
        // need to use backtracking for it
        // two cases 1. include the cur element 2. not include the cur element
        // base case when size of selected arr is n/2 then check the min sum diff
        // base case curr posiiton == n i,e it is last element out of range return
        // base case check no of elementes left are not less than elements req to form
        // one part of arr

        boolean visited[] = new boolean[n];
        int sum = 0;
        for (int i = 0; i < arr.size(); i++)
            sum += arr.get(i);
        System.out.println(sum);
        min_dist = Integer.MAX_VALUE;
        tugOfWarUtil(arr, n, visited, sum, 0, 0, 0);

        return min_dist;

    }

    public static void findMaximumNumUtil(char ch[], int k) {
        if (k ==0)
            return;
        for (int i = 0; i < ch.length; i++) {
            for (int j = i; j < ch.length; j++) {
                if(ch[j] > ch[i]){
                    char temp = ch[j];                    
                    ch[j] = ch[i];
                    ch[i] = temp;                    

                    if(String.valueOf(new String(ch)).compareTo(max_val) > 0){
                        max_val = new String(ch);
                    }
                    findMaximumNumUtil(ch, k-1);

                    temp = ch[j];                    
                    ch[j] = ch[i];
                    ch[i] = temp;  

                }
            }
        }
    }

    static String max_val;
    public static String findMaximumNum(String str, int k)
    {
        max_val = str;
        char ch[] = str.toCharArray();
        findMaximumNumUtil(ch, k);

        return max_val;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(10);
        list.add(10);

        System.out.println(tugOfWar(list, list.size()));
    }
}

class Pair {
    boolean found;
    int value;

    public Pair(boolean found, int value) {
        this.found = found;
        this.value = value;
    }

}
