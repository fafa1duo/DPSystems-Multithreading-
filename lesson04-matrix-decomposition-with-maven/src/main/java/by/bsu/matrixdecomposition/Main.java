package by.bsu.matrixdecomposition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private final static int N = 1_000_000;

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        int[] threadCounts = {1, 2, 4};

        for (int numberOfThreads : threadCounts) {
            System.out.println("\nCreating " + numberOfThreads + " threads...");
            MatrixProcessingTask[] workers = new MatrixProcessingTask[numberOfThreads];
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < numberOfThreads; i++) {
                workers[i] = new MatrixProcessingTask(i);
                workers[i].start();
            }
            System.out.println("Threads have been created and started.");

            for (int i = 0; i < numberOfThreads; i++) {
                try {
                    workers[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            double result = 0;
            for (int i = 0; i < numberOfThreads; i++) {
                result += workers[i].getResult();
                System.out.println("Result from Thread " + i + ": " + workers[i].getResult());
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time = " + (elapsedTime / 1000.0) + " seconds.");
            System.out.println("Total result = " + result);
            System.out.println(numberOfThreads + " threads have completed.");
        }
    }
    static class MatrixProcessingTask extends Thread {

        private int threadCount;
        private Matrix matrix1;
        private Matrix matrix2;
        private Matrix resultMatrix;

        public MatrixProcessingTask(int threadCount) {
            this.threadCount = threadCount;
        }

        @Override
        public void run() {
            try {
                MatrixCreator creator = new MatrixCreator();
                matrix1 = creator.create(2, 3);
                creator.fillRandomized(matrix1, 2, 8);
                System.out.println("Matrix first is: " + matrix1);
                matrix2 = new Matrix(3, 4);
                creator.fillRandomized(matrix2, 2, 7);
                System.out.println("Matrix second is: " + matrix2);

                // Check for multiplication errors
                if (!matrix1.isCompatibleForMultiplication(matrix2)) {
                    throw new MatrixException("Incompatible matrices for multiplication");
                }

                // Multiply matrices
                resultMatrix = matrix1.multiply(matrix2);

            } catch (MatrixException e) {
                logger.error("Error in thread " + threadCount + ": " + e.getMessage());
            }
        }
        public double getResult() {
            if (resultMatrix == null) {
                return 0.0;
            }
            double result = 0;
            for (int i = 0; i < resultMatrix.getRows(); i++) {
                for (int j = 0; j < resultMatrix.getColumns(); j++) {
                    result += resultMatrix.get(i, j);
                }
            }
            return result;
        }

    }

    public static void foo() {
        int a = 5;
        logger.trace("we are in the foo()");

        logger.debug("a = " + a);
        logger.trace("we are leaving the foo()");
    }
}

