package by.bsu.lesson06task03__word;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MasterThread {
	
	private static final Logger logger = LogManager.getLogger(MasterThread.class);
	
  //private static String directory; // 目录
  private static String word; // 单词
  private static String directory = "D:\\JAVA\\Eclipse files\\DPSystems\\lesson06_task03_find_word_files_md1\\src\\by\\bsu\\lesson06task03__word";
  
  
  public static void main(String[] args) throws InterruptedException, IOException {
  
    word = "a";
    WorkerThread.setDirectory(directory);
    WorkerThread.setWord(word);
    int n = 100_000_000;
    {
      // 第一个…
    }
    int subTaskNumber = 1000; // 子任务数
    int threadNumber = 4; // 线程数
    int step = n / subTaskNumber; // 步长
    long startTime = System.currentTimeMillis();

    Semaphore semaphore = new Semaphore(threadNumber); // 信号量（资源计数器）
    List<WorkerThread> workers = new ArrayList<>(); // 线程列表

    for (int i = 0; i < subTaskNumber; i++) {
      // 信号量获取
      semaphore.acquire();

      // 创建线程
    // WorkerThread worker = new WorkerThread(i * step, (i + 1) * step);
      WorkerThread worker = new WorkerThread(i*step,(i+1)*step,semaphore);
      worker.start();
      workers.add(worker);
    }
    int result = 0;
    for (WorkerThread worker : workers) {
      worker.join(); // 等待线程完成
      result += worker.getResult(); // 累加结果
    }
    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("Thread: " + (elapsedTime / 1000.0) + " seconds");
    System.out.println("result =" + result);
  }
}





