/*
 * Project#1 Matrix Multiplication
 * Test and Analysis of Classical-O(n^3)(Brute Force Method), Divide and Conquer Methods-O(n^3) and Strassen Algorithm O(n^2.8)
 * Myo Aung & Jihun Choi
 */
public class Main {
    static Matrix tester = new Matrix();

    public static void main(String[] args) {

            //Sanity check
            int[][] test1 = {{2, 0, -1, 6}, {3,7, 8, 0}, {-5, 1, 6, -2}, {8, 0, 1, 7}};
            int[][] test2 = {{0, 1, 6, 3}, {-2, 8, 7, 1}, {2, 0, -1, 0}, {9, 1, 6, -2}};
            //int[][] test1 = {{1,0, 1, 1 }, {1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}};
            //int[][] test2 = {{1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}, {1,0, 1, 1}};

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

           data(test1, 0);

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



} // end Main
