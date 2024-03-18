package by.bsu.lesson03task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java WordCounter <directory> <word>");
            return;
        }

        String directoryPath = args[0];
        String wordToFind = args[1];

        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Invalid directory path.");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("Empty directory.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                int count = countWordInFile(file, wordToFind);
                System.out.println(file.getName() + " содержит " + count + " вхождений");
            }
        }
    }

    private static int countWordInFile(File file, String word) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String w : words) {
                    if (w.equalsIgnoreCase(word)) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

