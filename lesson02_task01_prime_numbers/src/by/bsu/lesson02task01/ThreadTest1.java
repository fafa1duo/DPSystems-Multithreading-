package by.bsu.lesson02task01;

//import java.math.*;
//import java.util.Scanner;
//
//public class ThreadTest1 {
//  
//  private final static int MAX = 100_000_000;
//  private static class CountPrimesThread extends Thread {
//      int id;  // An id number for this thread; specified in the constructor.
//      int min,max;
//      private int result;
//      
//      public int getResult() {
//			return result;
//		}
//		public void setResult(int result) {
//			this.result = result;
//		}
//		public CountPrimesThread(int id,int min,int max) {
//          this.id = id;
//          this.min = min;
//          this.max = max;
//          System.out.println("id="+id+";min="+min+";max="+max);
//      }
//      public void run() {
//          long startTime = System.currentTimeMillis();
//          int count = countPrimes(min,max);
//          result = count;
////          long elapsedTime = System.currentTimeMillis() - startTime;
////          System.out.println("Thread " + id + " counted " + 
////                  count + " primes in " + (elapsedTime/1000.0) + " seconds.");
//    }
//  }
//
//  public static void main(String[] args) {
////      int numberOfThreads = 0;
////      while (numberOfThreads < 1 || numberOfThreads > 30) {
////          System.out.print("How many threads do you want to use  (from 1 to 30) ?  ");
////          //numberOfThreads = TextIO.getlnInt();
////          Scanner scan = new Scanner( System.in );
////          numberOfThreads= scan.nextInt();
////          if (numberOfThreads < 1 || numberOfThreads > 30)
////              System.out.println("Please enter a number between 1 and 30 !");
////      }
//  	
////    int numberOfThreads = 1;
//    int numberOfThreads = 2;
////  	int numberOfThreads = 4;
//      System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
//      CountPrimesThread[] worker = new CountPrimesThread[numberOfThreads];
////      for (int i = 0; i < numberOfThreads; i++)
//      	long startTime = System.currentTimeMillis();
////      	worker[0] = new CountPrimesThread( 0, 2, MAX ); 
//        worker[0] = new CountPrimesThread( 0, 2, MAX/2 );                      
//        worker[1] = new CountPrimesThread( 1, MAX/2+1, MAX );
////          worker[0] = new CountPrimesThread( 0, 2, MAX/4 );
////          worker[1] = new CountPrimesThread( 1, MAX/4+1, MAX );
////          worker[2] = new CountPrimesThread( 2, MAX/2+1, 3*MAX/4*3 );
////          worker[3] = new CountPrimesThread( 3, MAX/4*3+1, MAX );
//      for (int i = 0; i < numberOfThreads; i++)
//          worker[i].start();
//      System.out.println("Threads have been created and started.");
//      
//      
//      for (int i = 0; i < numberOfThreads; i++) {
//           try {
//              worker[i].join();  // Wait until worker[i] finishes, if it hasn't already.
//           }
//           catch (InterruptedException e) {
//           }
//        }
//      
//      int result = 0;
//      for (int i = 0; i < numberOfThreads ;i++) {
//         result += worker[i].getResult();
//      } 
//      long elapsedTime = System.currentTimeMillis() - startTime;
//      System.out.println("elapsedTime = " + (elapsedTime/1000.0 ) + "seconds.");
//      
//      System.out.println("result =" + result);
//      System.out.println("Main thread has completed.");
//  }
//
//  private static int countPrimes(int min, int max) {
//      int count = 0;
//      if 
//      for (int i = min; i <= max; i++)
//          if (isPrime(i))
//              count++;
//      return count;
//  }
//
//
//  private static boolean isPrime(int x) {
//      assert x > 1;
//      int top = (int)Math.sqrt(x);
//      for (int i = 2; i <= top; i++)
//          if ( x % i == 0 )
//              return false;
//      return true;
//  }
//
//
//} // end class ThreadTest1


public class ThreadTest1 {
  
  private final static int MAX = 1_000_000;
  private static class CountPrimesThread extends Thread {
      int id;  // An id number for this thread; specified in the constructor.
      int min;
      int max;
      private int result;
      
      public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public CountPrimesThread(int id,int min,int max) {
          this.id = id;
          this.min = min;
          this.max = max;
          System.out.println("id="+id+";min="+min+";max="+max);
      }
      public void run() {
          long startTime = System.currentTimeMillis();
          int count = countPrimes(min,max);
          result = count;
      }
  }

  public static void main(String[] args) {
      int[] threadCounts = {1, 2, 4};
      
      for (int numberOfThreads : threadCounts) {
          System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
          CountPrimesThread[] worker = new CountPrimesThread[numberOfThreads];
          long startTime = System.currentTimeMillis();  
          
          for (int i = 0; i < numberOfThreads; i++) {
              int min = i * (MAX / numberOfThreads) + 1;
              int max = (i + 1) * (MAX / numberOfThreads);
              worker[i] = new CountPrimesThread(i, min, max);
              worker[i].start();
          }
          System.out.println("Threads have been created and started.");
          
          for (int i = 0; i < numberOfThreads; i++) {
               try {
                  worker[i].join();  // Wait until worker[i] finishes, if it hasn't already.
               }
               catch (InterruptedException e) {
               }
          }
          int result = 0;
          for (int i = 0; i < numberOfThreads; i++) {
              result += worker[i].getResult();
          }
          long elapsedTime = System.currentTimeMillis() - startTime;
          System.out.println("elapsedTime = " + (elapsedTime/1000.0 ) + " seconds.");
          
          System.out.println("result = " + result);
          System.out.println(numberOfThreads + " threads have completed.");
      }
  }

  private static int countPrimes(int min, int max) {
      int count = 0;
      for (int i = min; i <= max; i++)
          if (isPrime(i))
              count++;
      return count;
  }


  private static boolean isPrime(int x) {
      assert x > 1;
      int top = (int)Math.sqrt(x);
      for (int i = 2; i <= top; i++)
          if ( x % i == 0 )
              return false;
      return true;
  }


} // end class ThreadTest1

