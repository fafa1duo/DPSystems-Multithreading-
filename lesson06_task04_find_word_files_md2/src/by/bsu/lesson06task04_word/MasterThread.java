package by.bsu.lesson06task04_word;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// 主线程类
public class MasterThread {
	
	private static final Logger logger = LogManager.getLogger(MasterThread.class);
	
    public static void main(String[] args) throws IOException, InterruptedException {
    
        int n = 100_000_000;
        
        long startTime = System.currentTimeMillis();

        {
        	//第一个输出，计算了从 2 到 n 的质数数量，并输出了执行时间和结果
          long startTime1 = System.currentTimeMillis();
          int result = WorkerThread.countPrimes(2,n);
          long elapsedTime = System.currentTimeMillis()-startTime1;
          logger.info("Thread: " + (elapsedTime/1000.0)+ " seconds");
          
          logger.info("result =" + result);
        }

        String directory = "D:\\JAVA\\Eclipse files\\DPSystems\\lesson06_task04_find_word_files_md2\\src\\by\\bsu\\lesson06task04_word\\";

        // 要查找的单词
        String word = "good word ！";

        int subTaskNumber = 1000;
        int threadNumber = 4;

        // 获取目录下的所有文件
        List<File> files = new ArrayList<>();
        Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .forEach(path -> files.add(path.toFile()));
     // 创建信号量，限制同时运行的线程数量
        Semaphore semaphore = new Semaphore(threadNumber); // 将 threadNumber 设置为 2 或 4

        // 创建线程列表
        List<WorkerThread> workers = new ArrayList<>();
     // 将文件列表划分为子任务
        int start = 0;
        int end = subTaskNumber;
        for (int i = 0; i < threadNumber; i++) {
            // 获取子任务的文件列表
            int actualEnd = Math.min(end, files.size()); // 确保不超出文件列表的大小
            if (start < actualEnd) { // 添加条件判断避免空的子任务列表
                List<File> subTaskFiles = files.subList(start, actualEnd);

                // 创建线程
                WorkerThread worker = new WorkerThread(subTaskFiles, word, semaphore);
                worker.start();
                workers.add(worker);
            }

            // 更新子任务的范围
            start = end;
            end = Math.min(end + subTaskNumber, files.size());
        }

        // 等待所有线程完成
        for (WorkerThread worker : workers) {
            worker.join();
        }
        // 汇总线程的结果
        int totalOccurrences = 0;
        for (WorkerThread worker : workers) {
            totalOccurrences += worker.getResult();
        }
        System.out.println("word： '" + word + "' Total number of occurrences in all files：" + totalOccurrences);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Thread: " + (elapsedTime / 1000.0) + " seconds");
    }
}


