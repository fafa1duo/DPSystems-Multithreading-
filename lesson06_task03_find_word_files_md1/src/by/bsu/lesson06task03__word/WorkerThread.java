package by.bsu.lesson06task03__word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class WorkerThread extends Thread {
    private int start;
    private int end;
    private static String directory; // 文件目录
    private static String word; // 要搜索的单词
    private Semaphore semaphore;
    private int result = 0; // 此线程找到的单词计数

    public WorkerThread(int start, int end, Semaphore semaphore) {
        this.start = start;
        this.end = end;
        this.semaphore = semaphore;
    }

    public static void setDirectory(String dir) {
        directory = dir;
    }

    public static void setWord(String w) {
        word = w;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            processFiles();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void processFiles() throws IOException {
        // 假设每个"子任务"实际上是处理一个文件，而不是基于开始和结束索引的范围
        // 这里我们将简化逻辑，直接遍历目录下的所有文件，实际应用中可能需要其他方法来分配文件给线程
        Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .forEach(filePath -> {
                    if (filePath.toString().endsWith(".txt")) { // 假设只处理.txt文件
                        try {
                            long count = Files.lines(filePath)
                                    .flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
                                    .filter(word::equalsIgnoreCase)
                                    .count();
                            result += count;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
