package org.example;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
public class FileProcess implements Runnable {
    private final File file;

    private final Map<Integer, byte[]> pngParts;

    public FileProcess(File file,  Map<Integer, byte[]> pngParts) {
        this.file = file;

        this.pngParts = pngParts;
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            int k = dis.readInt();
            byte[] stringBytes = new byte[k];
            dis.read(stringBytes);
            int d = dis.readInt();  // контрольное число четности
            int p = dis.readInt();  // номер фрагмента
            if (checkEven(stringBytes) == d) {
                synchronized (pngParts) {
                    pngParts.put(p, stringBytes);
                }
            } else{
                throw new Exception();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Не совпадают");;
        }
    }

    private int checkEven(byte[] data) {
        int even = 0;
        for (byte b : data) {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                count += (b >> i & 1);
            }
            if (count % 2 == 0) {
                even += 0;
            } else {
                even += 1;
            }
        }
        return even % 2;
    }

}



