package Matrix;

import java.util.*;

import javax.management.MBeanTrustPermission;

public class MATRIXSDESHEET {
    static ArrayList<Integer> downwardDigonal(int n, int A[][]) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i < n + n - 1; i++) {
            ArrayList<Integer> list2 = new ArrayList<>();
            list.add(list2);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < A.length; j++) {
                list.get(i + j).add(A[i][j]);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            res.addAll(list.get(i));
        }

        return res;
    }

    public void setZeroes(int[][] matrix) {
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int row = 0;
        int col = 0;
        int nROW = matrix.length;
        int nCol = matrix[0].length;

        while (row < nROW && col < nCol) {
            // firt loop
            for (int i = col; i < nCol; i++) {
                res.add(matrix[row][i]);
            }
            row++;

            for (int i = row; i < nROW; i++) {
                res.add(matrix[i][nCol - 1]);
            }
            nCol--;

            if (nROW > row) {
                for (int i = nCol - 1; i >= col; i--) {
                    res.add(matrix[nROW - 1][i]);
                }
                nROW--;
            }
            if (nCol > col) {
                for (int i = nROW - 1; i >= row; i--) {
                    res.add(matrix[i][col]);
                }
                col++;
            }
        }
        return res;
    }

    static int[][] sumMatrix(int A[][], int B[][]) {
        if (A.length != B.length || A[0].length != B[0].length) {
            int[][] res = new int[0][0];
            return res;
        }
        int n = A.length > B.length ? A.length : B.length;
        int m = A[0].length > B[0].length ? A[0].length : B[0].length;
        int res[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = A[i][j] + B[i][j];
            }
        }

        return res;
    }

    // solution is to first take transponse and then do reverse of each row
    public static void rotate(int[][] matrix) {
        // do transpose
        int temp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        printmatrix(matrix);

        // do reverse of each row

        for (int i = 0; i < matrix.length; i++) {
            int j = 0;
            int k = matrix.length - 1;
            while (j < k) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
                j++;
                k--;
            }
        }
    }

    static void printmatrix(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }
    }

    static boolean existHelper(char[][] board, String word, int i, int j , int n, int m, int len){
        if(word.length() <= len) return true;
        if(word.length() == 0) return false;
        if(i<0 || j<0 || i>=n || j>= m || board[i][j] == '.' || word.charAt(len) != board[i][j]) return false;
        if( word.length() == 1 && word.charAt(0) == board[i][j]) return true;

        boolean temp = false;
        board[i][j] = '.';
        int x[] = {0,0,-1,1};
        int y[] = {-1,1,0,0};

        for (int k = 0; k < 4; k++) {
            temp = temp || existHelper(board, word, i + x[k], j + y[k], n, m, len +1);
        }
        board[i][j] = word.charAt(len);
        return temp;
    }

    public boolean exist(char[][] board, String word) {
        if(board.length == 0) return false;
        if( word.length() == 0) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(word.charAt(0) == board[i][j]){
                    if(existHelper(board, word, i, j, board.length, board[i].length, 0)) return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int N = 3;
        int[][] A = { { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 } };
        int a[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 } };

        // System.out.println(downwardDigonal(N, A));
        rotate(A);
        printmatrix(A);
    }
}
