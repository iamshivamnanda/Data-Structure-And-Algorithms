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
}

class Pair {
    boolean found;
    int value;

    public Pair(boolean found, int value) {
        this.found = found;
        this.value = value;
    }

}
