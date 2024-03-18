package by.bsulesson04task01;

import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParallelProgram {
	
	private static final Logger logger = LogManager.getLogger(ParallelProgram.class);
	
	
  private static final int N=100_000_000; 
  public static void main(String[] args) {
	  
	  logger.info("Main app started");
//    int numberOfThreads = 1;
//   int numberOfThreads = 2;
    int numberOfThreads = 4;
    System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
    IntegralThread[] worker = new IntegralThread[numberOfThreads];

    long startTime = System.currentTimeMillis();
    double a=0;
    double b =1.;
    Function<Double,Double> func = (Double x) -> 4./(1+x*x);
    for(int i=0;i<numberOfThreads;i++) {
      worker[i] = new IntegralThread(N, numberOfThreads, i,a,b,func);
    }
//    worker[0] = new PiThread(N, numberOfThreads, 0);
//    worker[1] = new PiThread(N, numberOfThreads, 1);
    for (int i = 0; i < numberOfThreads; i++)
      worker[i].start();
    System.out.println("Threads have been created and started.");

    for (int i = 0; i < numberOfThreads; i++) {
      try {
        worker[i].join(); // Wait until worker[i] finishes, if it hasn't already.
      } catch (InterruptedException e) {
     } }
    double result = 0;
    for (int i = 0; i < numberOfThreads; i++) {
      result += worker[i].getResult();
      System.out.println(worker[i].getResult());
    }   
    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("elapsedTime = " + (elapsedTime / 1000.0) + " seconds.");

    System.out.println("result = " + result);
    System.out.println("Main thread has completed.");
    System.out.println("Main app completes");
  }
  
  private static class IntegralThread extends Thread{
    int N;
    int numberOfThreads;
    int start;
    double a;
    double b;
    Function<Double,Double> func;
    double result;
    
    public IntegralThread(int N,int numberOfThreads,int start,double a,double b,Function<Double,Double> func) {
      this.a=a;
      this.b=b;
      this.func=func;
      this.start=start;
      this.numberOfThreads=numberOfThreads;
      this.N=N;
    }
    public double getResult() {
      return result;
    }
    @Override
    public void run() {
      result = calculate(N,numberOfThreads,start,a,b,func);
    }
    private double calculate(int n,int numberOfThreads,int start,double a,double b,Function<Double,Double> func) { 
      double h =(b-a)/n;
      double sum=0;
      double x;
      for(int i=start;i<n;i+=numberOfThreads) {
        x= i* h;
        //sum = sum +func.apply(x) * h;
        sum = sum +func.apply(x);
      }
      sum = sum*h;
      return sum;
    }
  }
}
