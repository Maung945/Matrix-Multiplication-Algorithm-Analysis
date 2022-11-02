/*
 * Project#1 Matrix Multiplication
 * Test and Analysis of Classical-O(n^3)(Brute Force/Classical Method), Divide and Conquer Methods-O(n^3) and Strassen Algorithm O(n^2.8)
 * Myo Aung & Jihun Choi
 */

public class Main {
    static Matrix tester = new Matrix();
    public static void main(String[] args) {

        //Sanity check
        //int[][] test1 = {{3, 5, 1, 3}, {1, 2, 3, 4}, {4, 5, 6, 8}, {7, 8, 9, 3}};
        //int[][] test2 = {{4, 1, 2, 3}, {1, 2, 1, 6}, {2, 4, 6, 2}, {6, 2, 5, 4}};
        int[][] test1 = {{2, 1, 1, 5, 6}, {3, 7, 3, 8, 3}, {4, 7, -3, 6, -9}, {2, 7, -3, 6, -9}, {4, -7, -3, 6, -9}};
        int[][] test2 = {{2, 1, 1, 0, 6}, {3, -7, 3, -8, 3}, {1, 5, -3, 6, -9}, {-2, 7, -3, 6, -9}, {4, -7, -3, 6, -9}};
        //int[][] test1 = {{1,0, 1, 1 }, {1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}};
        //int[][] test2 = {{1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}};

        printMatrix(test1);
        printMatrix(test2);

        if (chekPowerOfTwo(test1)) {
            //Checking for classical method
            int[][] result1 = tester.classical(test1, test2, test1.length);
            System.out.println("Result of Classical method: ");
            printMatrix(result1);

            //Checking for Divide & Conquer method
            int[][] result2 = tester.divideAndConquer(test1, test2, test1.length);
            System.out.println("Result of Divide and Conquer method: ");
            printMatrix(result2);

            //Checking for strassen method
            int[][] result3 = tester.strassen(test1, test2, test1.length);
            System.out.println("Result of Strassen method: ");
            printMatrix(result3);

        } else {
            System.out.println("The matrix is NOT power of 2!!");
            System.out.println("The Matrix Length is: " + test1.length);
            System.out.println();
            int[][] newtest1 = padArray(test1, 1, 3);
            int[][] newtest2 = padArray(test2, 1, 3);
            int[][] result1 = tester.classical(newtest1, newtest2, newtest2.length);
            //print2D(newtest1);
            System.out.println("Result of Classical method: ");
            printMatrix(result1);

            //Checking for Divide & Conquer method
            int[][] result2 = tester.divideAndConquer(newtest1, newtest2, newtest2.length);
            System.out.println("Result of Divide and Conquer method: ");
            printMatrix(result2);

            //Checking for strassen method
            int[][] result3 = tester.strassen(newtest1, newtest2, newtest2.length);
            System.out.println("Result of Strassen method: ");
            printMatrix(result3);
        }

    }
    // Check The matrix is the size of power of TWO
    public static boolean chekPowerOfTwo(int[][] A) {
        int i = 2;
        while(i <= 1024) {
            if(A.length % i == 0) {
                return true;
            }
            i = 2 * i;
        }
        return false;
    }

    // This function pad arrays with zeros to get the size of 2^n
    public static int[][] padArray(int[][] arr, int padWith, int numOfPads) {
        int[][] temp = new int[arr.length + numOfPads][arr.length + numOfPads];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                //temp[i+numOfPads][j+numOfPads] = arr[i][j];
                //if(i < arr.length  || j < arr.length)
                //temp[i][j] = padWith;
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }

    public static void printMatrix(int[][] arr) {
        for (int[] i : arr) {
            for (int j : i) {
                System.out.printf("%3d ", j);
            }
            System.out.println();
        }
        System.out.println();
    }
    /*

    public static void main(String[] args) {
        int[][] test0 = {{0}};
        //int[][] test = {{2,0, -1, 6}, {3,7, 8, 0}, {-5, 1, 6, -2}, {8, 0, 2, 7}};
        int[][] test = {{2, 1, 1, 5, 6}, {3, 7, 3, 8, 3}, {4, 7, -3, 6, -9}, {2, 7, -3, 6, -9}, {4, -7, -3, 6, -9}};
        //int[][] testN = new int[n][n];
        data(test, 0);
    }

    public static void data(int[][] m, int fill) {
        int l = 2;
        int[][] size = tester.resize(m, l, fill);
        for (int i = 0; i < 16; i++) {
            timeCounter(size);
            l *= 2;
            size = tester.resize(m, l, fill);
        }
    }

    public static void timeCounter(int[][] A) {
        long start, run, avg = 0;
        start = System.currentTimeMillis();
        //testClassical(A);
        //testDivConquer(A);
        testStrassen(A);
        run = System.currentTimeMillis() - start;
        int l = A.length;
        System.out.println(l + "*" + l + " runtime:" + run);
    }
    public static void testClassical(int[][] a) {tester.classical(a, a, a.length);}             // testClassical
    public static void testDivConquer(int[][] a) {tester.divideAndConquer(a, a, a.length);}    // testDivConquer
    public static void testStrassen(int[][] a) {tester.strassen(a, a, a.length);}              // testStrassen

     */
}