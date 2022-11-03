/*
 * Project#1 Matrix Multiplication
 * Test and Analysis of Classical-O(n^3)(Brute Force Method), Divide and Conquer Methods-O(n^3) and Strassen Algorithm O(n^2.8)
 * Myo Aung & Jihun Choi
 */
public class Main {
    static Matrix tester = new Matrix();

   public static void main(String[] args) {

           //Matrices
           int[][] test1 = {{2,0, -1, 6}, {3,7, 8, 0}, {-5, 1, 6, -2}, {8, 0, 1, 7}};
           int[][] test2 = {{0,1, 6, 3}, {-2,8, 7, 1}, {2, 0, -1, 0}, {9, 1, 6, -2}};
           int[][] test3 = {{2, 1, 1, 5, 6}, {3, 7, 3, 8, 3}, {4, 7, -3, 6, -9}, {2, 7, -3, 6, -9}, {4, -7, -3, 6, -9}};
           int[][] test4 = {{2, 1, 1, 0, 6}, {3, -7, 3, -8, 3}, {1, 5, -3, 6, -9}, {-2, 7, -3, 6, -9}, {4, -7, -3, 6, -9}};
           //int[][] test1 = {{1,0, 1, 1 }, {1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}};
           //int[][] test2 = {{1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}};

       //Sanity Check for regular setup
       /*
           System.out.println("Test1 Matrix: ");
           tester.printMat(test1);
           System.out.println("Test2 Matrix: ");
           tester.printMat(test2);

           //Checking for classical method
           int[][] result1 = tester.classical(test1,test2,test1.length);
           System.out.println("Result of Classical method: ");
           tester.printMat(result1);

           //Checking for Divide & Conquer method
           int[][] result2 = tester.divideAndConquer(test1,test2, test1.length);
           System.out.println("Result of Divide and Conquer method: ");
           tester.printMat(result2);

           //Checking for classical method
           int[][] result3 = tester.strassen(test1,test2, test1.length);
           System.out.println("Result of Strassen method: ");
           tester.printMat(result3);
        */
       
       
       //time comparison(Correctly comment and uncomment line 106,107,108 depending on which is being tested)
       /*
           data(test1, 0);
       */

       //Sanity Check for Extra credit
       System.out.println("Test3 Matrix: ");
       tester.printMat(test3);
       System.out.println("Test4 Matrix: ");
       tester.printMat(test4);

       if (chekPowerOfTwo(test3)) {
           //Checking for classical method
           int[][] result1 = tester.classical(test3, test4, test3.length);
           System.out.println("Result of Classical method: ");
           tester.printMat(result1);;

           //Checking for Divide & Conquer method
           int[][] result2 = tester.divideAndConquer(test3, test4, test3.length);
           System.out.println("Result of Divide and Conquer method: ");
           tester.printMat(result2);

           //Checking for strassen method
           int[][] result3 = tester.strassen(test3, test4, test3.length);
           System.out.println("Result of Strassen method: ");
           tester.printMat(result3);

       } else {
           System.out.println("The matrix is NOT power of 2!!");
           System.out.println("The Matrix Length is: " + test1.length);
           System.out.println();
           int[][] newtest1 = padArray(test3, 1, 3);
           int[][] newtest2 = padArray(test4, 1, 3);
           int[][] result1 = tester.classical(newtest1, newtest2, newtest2.length);
           System.out.println("Result of Classical method: ");
           tester.printMat(result1);

           //Checking for Divide & Conquer method
           int[][] result2 = tester.divideAndConquer(newtest1, newtest2, newtest2.length);
           System.out.println("Result of Divide and Conquer method: ");
           tester.printMat(result2);

           //Checking for strassen method
           int[][] result3 = tester.strassen(newtest1, newtest2, newtest2.length);
           System.out.println("Result of Strassen method: ");
           tester.printMat(result3);
       }

   } // end main


    public static void data(int[][] m, int fill) {
        int l = 2;
        int[][] size = tester.resize(m, l, fill);
        for (int i = 0; i < 16; i++) {
            timeCounter(size);
            l *= 2;
            size = tester.resize(m, l, fill);
        } // end for
    } // end data
    public static void timeCounter(int[][] A) {
        long start, run, avg = 0;
        start = System.currentTimeMillis();
        testClassical(A);
        //testDivConquer(A);
        //testStrassen(A);
        run = System.currentTimeMillis() - start;

        int l = A.length;
        System.out.println(l + "*" + l + " runtime:" + run);
    } // end timeCounter


    public static void testClassical(int[][] a) {tester.classical(a, a, a.length);}      // end testClassical
    public static void testDivConquer(int[][] a) {tester.divideAndConquer(a, a, a.length);}    // end testDivConquer
    public static void testStrassen(int[][] a) {tester.strassen(a, a, a.length);}        // end testStrassen


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

} // end Main
