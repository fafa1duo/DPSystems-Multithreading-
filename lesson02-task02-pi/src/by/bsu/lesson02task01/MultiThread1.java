//public class MultiThread1 {
//  private final static int N = 1_000_000;
//  
//  private static class PiThread extends Thread{
//        private int n;//task size
//    private int serialNumber;//序列号
//    private int numberOfThreads;
//        private double result;
//        
//
//        public double getResult(){
//          return result;
//        }
//        public PiThread(int n,int numberOfThreads,int serialNumber) {
//          super();
//          this.n=n;
//          this.serialNumber=serialNumber;
//          this.numberOfThreads=numberOfThreads;
//        }
//        public void run() {
//            result = calculatePi(n,serialNumber,numberOfThreads);
//            
//        }
//  }
//  private static double calculatePi(int n,int serialNumber,int numberOfThreads) {
//    int i;
//    double h, sum, x;
//
//    h = 1. / n;
//    sum = 0.;
//
//    for (i = serialNumber; i < n; i += numberOfThreads)
//    {
//      x = h * i;
//      sum += 4. / (1. + x*x);
//    }
//    sum = h*sum;
//
//    return sum;
//  }
//  public static void main(String[] arga) {
//
//      int numberOfThreads = 2;
//  
//        System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
//        PiThread[] worker = new PiThread[numberOfThreads];
//  //      for (int i = 0; i < numberOfThreads; i++)
//  //          worker[i] = new CountPrimesThread( i );
//        long startTime = System.currentTimeMillis();  
//        
//        worker[0] = new PiThread(N,numberOfThreads,0);
//        worker[1] = new PiThread(N,numberOfThreads,1);
//  
//        for (int i = 0; i < numberOfThreads; i++)
//            worker[i].start();
//        System.out.println("Threads have been created and started.");
//        
//        for (int i = 0; i < numberOfThreads; i++) {
//             try {
//                worker[i].join();  // Wait until worker[i] finishes, if it hasn't already.
//             }
//             catch (InterruptedException e) {
//             }
//          }
//        double result=0;
//        for (int i = 0; i < numberOfThreads; i++) {
//            result += worker[i].getResult();
//            System.out.println(worker[i].getResult());
//        }
//        long elapsedTime = System.currentTimeMillis() - startTime;
//        System.out.println("elapsedTime = "+ (elapsedTime/1000.0) + " seconds.");
//   
//        System.out.println("result = " + result);
//        System.out.println("Main thread has completed.");
//  }
//}
package by.bsu.lesson02task01;

public class MultiThread1 {
  private final static int N = 1_000_000_000;
  
  private static class PiThread extends Thread{
    private int n; // 任务大小
    private int serialNumber; // 序列号
    private int numberOfThreads;
    private double result;
    
    public double getResult(){
      return result;
    }
    
    public PiThread(int n, int numberOfThreads, int serialNumber) {
      super();
      this.n = n;
      this.serialNumber = serialNumber;
      this.numberOfThreads = numberOfThreads;
    }
    
    public void run() {
      result = calculatePi(n, serialNumber, numberOfThreads);
    }
  }
  
  private static double calculatePi(int n, int serialNumber, int numberOfThreads) {
    int i;
    double h;
    double sum; 
    double x;


    h = 1. / n;
    sum = 0.;

    // 循环分割
    for (i = serialNumber; i < n; i += numberOfThreads) {
      x = h * i;
      sum += 4. / (1. + x*x);
    }
    sum = h * sum;

    return sum;
  }
  
  public static void main(String[] arga) {
    int[] threadCounts = {1, 2, 4};
    
    for (int numberOfThreads : threadCounts) {
      System.out.println("\nCreating " + numberOfThreads + " threads...");
      PiThread[] worker = new PiThread[numberOfThreads];
      long startTime = System.currentTimeMillis();  
      
      for (int i = 0; i < numberOfThreads; i++) {
        worker[i] = new PiThread(N, numberOfThreads, i);
        worker[i].start();
      }
      System.out.println("Threads have been created and started.");
      
      for (int i = 0; i < numberOfThreads; i++) {
        try {
          worker[i].join();  
        }
        catch (InterruptedException e) {
        }
      }
      
      double result = 0;
      for (int i = 0; i < numberOfThreads; i++) {
        result += worker[i].getResult();
        System.out.println(worker[i].getResult());
      }
      
      long elapsedTime = System.currentTimeMillis() - startTime;
      System.out.println("elapsedTime = " + (elapsedTime / 1000.0) + " seconds.");
      System.out.println("result = " + result);
      System.out.println(numberOfThreads + " threads have completed.");
    }
  }
  class ParallelProgram1 {
	  private final static int N = 1_000_000_000;
	  
	  private static class PiThread extends Thread{
	    private int n; // 任务大小
	    private int serialNumber; // 序列号
	    private int numberOfThreads;
	    private double result;
	    
	    public double getResult(){
	      return result;
	    }
	    
	    public PiThread(int n, int numberOfThreads, int serialNumber) {
	      super();
	      this.n = n;
	      this.serialNumber = serialNumber;
	      this.numberOfThreads = numberOfThreads;
	    }
	    
	    public void run() {
	      result = calculatePi(n, serialNumber, numberOfThreads);
	    }
	  }
	  
	  private static double calculatePi(int n, int serialNumber, int numberOfThreads) {
	    int i;
	    double h;
	    double sum; 
	    double x;


	    h = 1. / n;
	    sum = 0.;

	    // 循环分割
	    for (i = serialNumber; i < n; i += numberOfThreads) {
	      x = h * i;
	      sum += 4. / (1. + x*x);
	    }
	    sum = h * sum;

	    return sum;
	  }
	}
  
  
  
  class ParallelProgram2 {
	  private final static int N = 1_000_000_000;
	  
	  private static class PiThread extends Thread{
	    private int n; // 任务大小
	    private int start;
	    private int end;
	    private double result;
	    
	    public double getResult(){
	      return result;
	    }
	    
	    public PiThread(int n, int start, int end) {
	      super();
	      this.n = n;
	      this.start = start;
	      this.end = end;
	    }
	    
	    public void run() {
	      result = calculatePi(n, start, end);
	    }
	  }
	  
	  private static double calculatePi(int n, int start, int end) {
	    double h;
	    double sum;
	    double x;

	    h = 1. / n;
	    sum = 0.;

	    // 连续分割
	    for (int i = start; i < end; i++) {
	      x = h * i;
	      sum += 4. / (1. + x*x);
	    }
	    sum = h * sum;

	    return sum;
	  }
	}

}
