package Matrix;

import java.util.*;

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

    public static void main(String[] args) {
        int N = 3;
        int[][] A = { { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 } };
        int a[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 } };

        // System.out.println(downwardDigonal(N, A));
        System.out.println(spiralOrder(a));
    }
}
