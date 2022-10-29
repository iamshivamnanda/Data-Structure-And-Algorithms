import java.util.Arrays;

/**
 * Matrix
 */
public class Matrix {

    // create a matrix with 'X' and 'O' alternatively
    public static char[][] makeAlternateArray(int n) {
        char arr[][] = new char[n][n];
        int row = 0, col = 0, rowN = n, colN = n;
        char ch = 'O';
        while (row < rowN && col < colN) {
            ch = (ch == 'O') ? 'X' : 'O';
            // top diagonal
            for (int i = col; i < colN; i++) {
                arr[row][i] = ch;
            }
            row++;

            // end diagonal
            for (int i = row; i < rowN; i++) {
                arr[i][colN - 1] = ch;
            }
            colN--;

            if (col < colN) {
                // bottom diagonal
                for (int i = colN - 1; i >= col; i--) {
                    arr[rowN - 1][i] = ch;
                }
                rowN--;
            }

            if (row < rowN) {
                // left diagonal
                for (int i = rowN - 1; i >= row; i--) {
                    arr[i][col] = ch;
                }
                col++;
            }
        }

        return arr;
    }

    public static void printMatrix(char arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static void main(String[] args) {
        char matrix[][] = makeAlternateArray(7);
        printMatrix(matrix);
    }
}