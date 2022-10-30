/*
 * Project#1 Matrix Multiplication
 * Implementations of Classical-O(n^3)(Brute Force Method), Divide and Conquer Methods-O(n^3) and Strassen Algorithm O(n^2.8)
 * Myo Aung & Jihun Choi
 */
public class Matrix {
    // Iteration Matrix Multiplication
    public static int[][] classical(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Simple Divide-and-Conquer Matrix Multiplication
    public static int[][] divideAndConquer(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] A11 = partitionMat(A, 0, n / 2, 0, n / 2);
            int[][] A12 = partitionMat(A, 0, n / 2, n / 2, n);
            int[][] A21 = partitionMat(A, n / 2, n, 0, n / 2);
            int[][] A22 = partitionMat(A, n / 2, n, n / 2, n);

            int[][] B11 = partitionMat(B, 0, n / 2, 0, n / 2);
            int[][] B12 = partitionMat(B, 0, n / 2, n / 2, n);
            int[][] B21 = partitionMat(B, n / 2, n, 0, n / 2);
            int[][] B22 = partitionMat(B, n / 2, n, n / 2, n);

            int[][] C11 = addMat(divideAndConquer(A11, B11, n / 2), divideAndConquer(A12, B21, n / 2));
            int[][] C12 = addMat(divideAndConquer(A11, B12, n / 2), divideAndConquer(A12, B22, n / 2));
            int[][] C21 = addMat(divideAndConquer(A21, B11, n / 2), divideAndConquer(A22, B21, n / 2));
            int[][] C22 = addMat(divideAndConquer(A21, B12, n / 2), divideAndConquer(A22, B22, n / 2));

            C = combineMat(C11, C12, C21, C22);
        }
        return C;
    }

    // Strassen's Method Matrix Multiplication
    public static int[][] strassen(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] A11 = partitionMat(A, 0, n / 2, 0, n / 2);
            int[][] A12 = partitionMat(A, 0, n / 2, n / 2, n);
            int[][] A21 = partitionMat(A, n / 2, n, 0, n / 2);
            int[][] A22 = partitionMat(A, n / 2, n, n / 2, n);

            int[][] B11 = partitionMat(B, 0, n / 2, 0, n / 2);
            int[][] B12 = partitionMat(B, 0, n / 2, n / 2, n);
            int[][] B21 = partitionMat(B, n / 2, n, 0, n / 2);
            int[][] B22 = partitionMat(B, n / 2, n, n / 2, n);

            int[][] S1 = subtractMat(B12, B22);
            int[][] S2 = addMat(A11, A12);
            int[][] S3 = addMat(A21, A22);
            int[][] S4 = subtractMat(B21, B11);
            int[][] S5 = addMat(A11, A22);
            int[][] S6 = addMat(B11, B22);
            int[][] S7 = subtractMat(A12, A22);
            int[][] S8 = addMat(B21, B22);
            int[][] S9 = subtractMat(A11, A21);
            int[][] S10 = addMat(B11, B12);

            int[][] P1 = strassen(A11, S1, n / 2);
            int[][] P2 = strassen(S2, B22, n / 2);
            int[][] P3 = strassen(S3, B11, n / 2);
            int[][] P4 = strassen(A22, S4, n / 2);
            int[][] P5 = strassen(S5, S6, n / 2);
            int[][] P6 = strassen(S7, S8, n / 2);
            int[][] P7 = strassen(S9, S10, n / 2);

            int[][] C11 = subtractMat(addMat(P5, P4), subtractMat(P2, P6));
            int[][] C12 = addMat(P1, P2);
            int[][] C21 = addMat(P3, P4);
            int[][] C22 = subtractMat(addMat(P5, P1), addMat(P3, P7));

            C = combineMat(C11, C12, C21, C22);
        }
        return C;
    }

    // Combine Sub-Matrices
    public static int[][] combineMat(int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
        int n = C11.length;
        int[][] C = new int[n * 2][n * 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = C11[i][j];
                C[i][j + n] = C12[i][j];
                C[i + n][j] = C21[i][j];
                C[i + n][j + n] = C22[i][j];
            }
        }
        return C;
    }

    // Split and Copy Matrices
    public static int[][] partitionMat(int[][] matrix, int rLow, int rHigh, int cLow, int cHigh) {
        int[][] partitionedMat = new int[rHigh - rLow][cHigh - cLow];

        for (int i = 0, n = rLow; n < rHigh; i++, n++) {
            for (int j = 0, m = cLow; m < cHigh; j++, m++) {
                partitionedMat[i][j] = matrix[n][m];
            }
        }
        return partitionedMat;
    }

    // Matrix Addition
    public static int[][] addMat(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // Matrix Subtraction
    public static int[][] subtractMat(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    // Print Matrix
    public static void printMat(int[][] arr) {
        for (int[] i : arr) {
            for (int j : i) {
                System.out.printf("%3d ", j);
            }
            System.out.println();
        }
        System.out.println();
    }

    // change size of matrices
    public int[][] resize(int[][] m, int newL, int fill) {
        int l = m.length;
        int[][] A = new int[newL][newL];
        for (int i = 0; i < newL; i++) {
            for (int j = 0; j < newL; j++) {
                if (i < l && j < l) {
                    A[i][j] = m[i][j];
                } else {
                    A[i][j] = fill;
                }
            }
        }
        return A;
    }
}