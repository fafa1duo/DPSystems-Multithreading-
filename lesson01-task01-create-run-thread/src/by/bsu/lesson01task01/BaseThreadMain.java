package by.bsu.lesson01task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseThreadMain {
	
	 private static final Logger logger = LogManager.getLogger(BaseThreadMain.class);
	
public static void main(String[] args) {
	
	logger.info("Main app atarted");
WalkThread walk = new WalkThread(); // new thread object
Thread talk = new Thread(new TalkThread()); // new thread object
talk.start(); // start of thread
walk.start(); // start of thread
// TalkThread t = new TalkThread(); just an object, not a thread
// t.run(); or talk.run();
// method will execute, but thread will not start!
System.out.println("Thread tslk has start:"+ talk.getState());

//try {
//	Thread.currentThread().sleep(10);
//}catch(InterruptedExcepException e)

logger.info("Main app completes");
}
}