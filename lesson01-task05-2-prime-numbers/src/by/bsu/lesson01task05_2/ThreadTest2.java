package by.bsu.lesson01task05_2;

import java.util.Scanner;

/**
 * This program counts the number of prime integers between 3000001 and 6000000.
 * The work is divided among one to six threads.  The number of threads is
 * chosen by the user.
 */
public class ThreadTest2 {

    private static final int START = 6000000;
    private static int total;

    synchronized private static void addToTotal(int x) {
        total = total + x;
        System.out.println(total + " primes found so far.");
    }

    private static class CountPrimesThread extends Thread {
        int count = 0;
        int min;
        int max;

        public CountPrimesThread(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public void run() {
            count = countPrimes(min, max);
            System.out.println("There are " + count +
                    " primes between " + min + " and " + max);
            addToTotal(count);
        }

        private int countPrimes(int min, int max) {
            int count = 0;
            for (int i = min; i <= max; i++)
                if (isPrime(i))
                    count++;
            return count;
        }

        private boolean isPrime(int x) {
            int top = (int) Math.sqrt(x);
            for (int i = 2; i <= top; i++)
                if (x % i == 0)
                    return false;
            return true;
        }
    }

    private static void countPrimesWithThreads(int numberOfThreads) {
        int increment = START / numberOfThreads;
        System.out.println("\nCounting primes between " + (START + 1) + " and "
                + (2 * START) + " using " + numberOfThreads + " threads...\n");
        long startTime = System.currentTimeMillis();
        CountPrimesThread[] worker = new CountPrimesThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
            worker[i] = new CountPrimesThread(START + i * increment + 1, START + (i + 1) * increment);
        total = 0;
        for (int i = 0; i < numberOfThreads; i++)
            worker[i].start();
        for (int i = 0; i < numberOfThreads; i++) {
            while (worker[i].isAlive()) {
                try {
                    worker[i].join();
                } catch (InterruptedException e) {
                }
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nThe number of primes is " + total + ".");
        System.out.println("\nTotal elapsed time:  " + (elapsedTime / 1000.0) + " seconds.\n");
    }

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        if (processors == 1)
            System.out.println("Your computer has only 1 available processor.\n");
        else
            System.out.println("Your computer has " + processors + " available processors.\n");
        int numberOfThreads = 0;
        while (numberOfThreads < 1 || numberOfThreads > 6) {
            System.out.print("How many threads do you want to use  (from 1 to 6) ?  ");
            Scanner scan = new Scanner(System.in);
            numberOfThreads = scan.nextInt();
            if (numberOfThreads < 1 || numberOfThreads > 6)
                System.out.println("Please enter 1, 2, 3, 4, 5, or 6 !");
        }
        countPrimesWithThreads(numberOfThreads);
    }

}
