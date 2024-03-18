package by.bsu.lesson05task1;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterThread {
	
	private static final Logger logger = LogManager.getLogger(MasterThread.class);
	
    public static void main(String[] args) {
    	
    	logger.info("Main app started");
    	
        List<String> fileNames = new ArrayList<>();
        //添加文件名字
        //...
        fileNames.add("1.txt");
        fileNames.add("2.txt");
        fileNames.add("3.txt");

        List<Thread> workers = new ArrayList<>();

        for (String fileName : fileNames) {
            Thread worker = new Thread(new WorkerThread(fileName));
            workers.add(worker);
            worker.start();
        }
        
        System.out.println("Main app completes");
    }
}
