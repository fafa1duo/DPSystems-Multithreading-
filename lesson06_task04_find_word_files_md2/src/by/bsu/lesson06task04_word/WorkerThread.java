package by.bsu.lesson06task04_word;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

class WorkerThread extends Thread {
	  private List<File> files;
	  private String word;
	  private Semaphore semaphore;
	  private int result;

	  public WorkerThread(List<File> files, String word, Semaphore semaphore) {
	    this.files = files;
	    this.word = word;
	    this.semaphore = semaphore;
	  }

	  public int getResult() {
	    return result;
	  }

	  @Override
	  public void run() {
	    // Обработать подзадачу
	    try {
	      semaphore.acquire();
	      result = countOccurrences(files, word);
	      semaphore.release();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }

	  private int countOccurrences(List<File> files, String word) {
	    int count = 0;
	    for (File file : files) {
	      try {
	        String content = new String(Files.readAllBytes(file.toPath()));
	        count += countOccurrences(content, word);
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return count;
	  }

	  private int countOccurrences(String content, String word) {
	    int count = 0;
	    int index = content.indexOf(word);
	    while (index != -1) {
	      count++;
	      index = content.indexOf(word, index + word.length());
	    }
	    return count;
	  }

	public static int countPrimes(int i, int n) {
		// TODO Auto-generated method stub
		return 0;
	}
	}