package Backtracking;

import java.util.Arrays;


class Res {
    static String max = "";
}
public class Backtracking {

    // Permutate all combination of strings except the string with "AB"
    static boolean isSafe(String str, int i, int l, int r) {
        if (l != 0 && str.charAt(l - 1) == 'A' && str.charAt(i) == 'B') {
            return false;
        }
        if (r == l + 1 && str.charAt(i) == 'A' && str.charAt(l) == 'B') {
            return false;
        }
        return true;
    }

    static char[] swap(String str, int i, int j) {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return ch;
    }

    void permute(String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
            return;
        } else {
            for (int i = l; i <= r; i++) {
                if (isSafe(str, i, l, r)) {
                    str = new String(swap(str, i, l));
                    permute(str, l + 1, r);
                    str = new String(swap(str, i, l));

                }
            }
        }
    }

    // RatNMace
    void ratMace(int[][] arr, int N) {
        int[][] sol = new int[N][N];

        if (ratMaceRec(arr, 0, 0, sol) == true) {
            System.out.println(Arrays.toString(sol));
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    static boolean isSafeRat(int[][] arr, int i, int j) {
        return (i < arr.length && j < arr.length && arr[i][j] == 1);
    }

    static boolean ratMaceRec(int[][] arr, int i, int j, int[][] sol) {
        if (i == arr.length - 1 && j == arr.length - 1 && arr[i][j] == 1) {
            return true;
        }
        if (isSafeRat(arr, i, j)) {
            sol[i][j] = 1;
            if (ratMaceRec(arr, i + 1, j, sol) == true) {
                return true;
            }
            if (ratMaceRec(arr, i, j + 1, sol) == true) {
                return true;
            }
            sol[i][j] = 0;
        }
        return false;
    }

    // N-Queen Problem

    static boolean isSafequeen(int row, int col, int[][] board) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    static boolean nqueenRec(int col, int[][] board) {
        if (col == board.length) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            if (isSafequeen(i, col, board)) {
                board[i][col] = 1;
                if (nqueenRec(col + 1, board)) {
                    return true;
                }
                board[i][col] = 0;
            }
        }
        return false;
    }

    void nqueen(int N) {
        int[][] board = new int[N][N];
        if (nqueenRec(0, board) == true) {
            System.out.println(Arrays.toString(board));
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    // sudoku
    static boolean isSafeSudoko(int[][] grid, int i, int j, int n) {

        for (int k = 0; k < grid.length; k++) {
            if (grid[i][k] == n || grid[k][j] == n) {
                return false;
            }
        }
        int s = (int) Math.sqrt(grid.length);
        int rs = i - i % s;
        int cs = j - j % s;
        for (int l = 0; l < s; l++) {
            for (int m = 0; m < s; m++) {
                if (grid[rs + l][cs + m] == n) {
                    return false;
                }
            }
        }
        return true;

    }

    boolean sudokuSolve(int grid[][]) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int n = 1; n <= grid.length; n++) {
            if (isSafeSudoko(grid, row, col, n)) {
                grid[row][col] = n;
                if (sudokuSolve(grid)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }
        return false;
    }

    static void printGrid (int grid[][])
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void findMaximumNumUtil(char ar[], int k, Res r)
    {
        if (k == 0) return;
        int n = ar.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                //if digit at position i is less than digit at position j, 
                //we swap them and check for maximum number so far.
                if (ar[j] > ar[i])
                {
                    char temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;

                    String st = new String(ar);
                    
                    //if current number is more than maximum so far
                    if (Res.max.compareTo(st) < 0)
                    {
                        Res.max = st;
                    }
                    //calling recursive function to set the next digit.
                    findMaximumNumUtil(ar, k - 1, r);

                    // backtracking
                    temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;
                }
            }
        }
    }
    
    //Function to find the largest number after k swaps.
    public static String findMaximumNum(String str, int k)
    {
        Res r = new Res();
        Res.max = str;
        findMaximumNumUtil(str.toCharArray(), k, r);
        //returning the result.
        return Res.max;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumNum("61892795431", 5));
    }
}

