package by.bsu.lesson06task01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterThread {
	
	private static final Logger logger = LogManager.getLogger(MasterThread.class);

	
  public static void main(String[] args) throws InterruptedException {
    int n = 100_000_000;
    {
    	//第一个输出，计算了从 2 到 n 的质数数量，并输出了执行时间和结果
      long startTime = System.currentTimeMillis();
      int result = WorkerThread.countPrimes(2,n);
      long elapsedTime = System.currentTimeMillis()-startTime;
      logger.info("Thread: " + (elapsedTime/1000.0)+ " seconds");
      
      logger.info("result =" + result);
    }
    int subTaskNumber = 1000;
    int threadNumber = 1; //线程
    int step = n /subTaskNumber;
    long startTime = System.currentTimeMillis();

    Semaphore semaphore = new Semaphore(threadNumber); //счетчик ресурсов
    List<WorkerThread> workers = new ArrayList<>();
    
    for(int i=0;i<subTaskNumber;i++) { 
     // Семафор.acquire
      semaphore.acquire();
      
      //创建线程
      WorkerThread worker = new WorkerThread(i*step,(i+1)*step,semaphore);
      worker.start();
      workers.add(worker);
    }
    int result = 0;
    for(WorkerThread worker : workers) {
      worker.join();
      result += worker.getResult();
    }
    long elapsedTime = System.currentTimeMillis()-startTime;
    logger.info("Thread: " + (elapsedTime/1000.0)+ " seconds");
    
    logger.info("result =" + result);  }//整体线程
}
