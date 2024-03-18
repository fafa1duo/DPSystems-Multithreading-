package by.bsu.lesson05task1;

public class WorkerThread implements Runnable {
    private String fileName;

    public WorkerThread(String fileName) {
        super();
        this.fileName = fileName;
        System.out.println("Worker inits!!!");
    }

    @Override
    public void run() {
        System.out.println("Worker starts!!!");
        // result!!!
      
        Utils.countWords(fileName);
    }
}
