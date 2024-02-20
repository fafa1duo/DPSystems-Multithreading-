package by.bsu.lesson01task05_1;
//
//import java.util.Scanner;
//
//public class ThreadTest1 {
//    
//    private final static int MAX = 10_000_000;
//
//    private static class CountPrimesThread extends Thread {
//        int id;  // An id number for this thread; specified in the constructor.
//        public CountPrimesThread(int id) {
//            this.id = id;
//        }
//        public void run() {
//            long startTime = System.currentTimeMillis();
//            int count = countPrimes(2,MAX);
//            long elapsedTime = System.currentTimeMillis() - startTime;
//            System.out.println("Thread " + id + " counted " + 
//                    count + " primes in " + (elapsedTime/1000.0) + " seconds.");
//        }
//    }
//
//
//    public static void main(String[] args) {
//        int numberOfThreads = 0;
//        while (numberOfThreads < 1 || numberOfThreads > 30) {
//            System.out.print("How many threads do you want to use  (from 1 to 30) ?  ");
//            //numberOfThreads = TextIO.getlnInt();
//            Scanner scan = new Scanner( System.in );
//            numberOfThreads= scan.nextInt();
//            if (numberOfThreads < 1 || numberOfThreads > 30)
//                System.out.println("Please enter a number between 1 and 30 !");
//        }
//        System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
//        CountPrimesThread[] worker = new CountPrimesThread[numberOfThreads];
//        for (int i = 0; i < numberOfThreads; i++)
//            worker[i] = new CountPrimesThread( i );
//        for (int i = 0; i < numberOfThreads; i++)
//            worker[i].start();
//        System.out.println("Threads have been created and started.");
//    }
//
//
//    private static int countPrimes(int min, int max) {
//        int count = 0;
//        for (int i = min; i <= max; i++)
//            if (isPrime(i))
//                count++;
//        return count;
//    }
//
//
//    private static boolean isPrime(int x) {
//        assert x > 1;
//        int top = (int)Math.sqrt(x);
//        for (int i = 2; i <= top; i++)
//            if ( x % i == 0 )
//                return false;
//        return true;
//    }
//
//
//} // end class ThreadTest1







import java.util.Scanner;

public class ThreadTest1 {

    private static class CountPrimesThread extends Thread {
        int id;  // An id number for this thread; specified in the constructor.
        private final static int MAX = 10_000_000;

        public CountPrimesThread(int id) {
            this.id = id;
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            int count = countPrimes(2, MAX);
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Thread " + id + " counted " +
                    count + " primes in " + (elapsedTime / 1000.0) + " seconds.");
        }

        private int countPrimes(int min, int max) {
            int count = 0;
            for (int i = min; i <= max; i++)
                if (isPrime(i))
                    count++;
            return count;
        }

        private boolean isPrime(int x) {
            assert x > 1;
            int top = (int) Math.sqrt(x);
            for (int i = 2; i <= top; i++)
                if (x % i == 0)
                    return false;
            return true;
        }
    }

    public static void main(String[] args) {
        int numberOfThreads = 0;
        while (numberOfThreads < 1 || numberOfThreads > 30) {
            System.out.print("How many threads do you want to use  (from 1 to 30) ?  ");
            Scanner scan = new Scanner(System.in);
            numberOfThreads = scan.nextInt();
            if (numberOfThreads < 1 || numberOfThreads > 30)
                System.out.println("Please enter a number between 1 and 30 !");
        }
        System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
        CountPrimesThread[] worker = new CountPrimesThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
            worker[i] = new CountPrimesThread(i);
        for (int i = 0; i < numberOfThreads; i++)
            worker[i].start();
        System.out.println("Threads have been created and started.");
    }
}

