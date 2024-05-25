package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Map<Integer, byte[]> pngParts = new HashMap<>();
            File folder = new File("/Users/pavelkalinin/Desktop/информатика/CW2/src/main/java/org/example/v13");
            File[] files = folder.listFiles();
            if (files != null) {
                List<Thread> threads = new ArrayList<>();
                for (File file : files) {
                    Thread thread = new Thread(new FileProcess(file, pngParts));
                    threads.add(thread);
                    thread.start();
                }
                for (Thread thread : threads) {
                    thread.join();
                }
            }
            List<Integer> keys = new ArrayList<>(pngParts.keySet());
            Collections.sort(keys);
            try (FileOutputStream writer = new FileOutputStream("/Users/pavelkalinin/Desktop/информатика/CW2/src/main/java/org/example/v13.png")) {
                for (int key : keys) {
                    writer.write(pngParts.get(key));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}