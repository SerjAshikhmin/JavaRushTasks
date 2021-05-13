package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("data", null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {}
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try {
            OutputStream outputStream = Files.newOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            for (Entry e = entry ; e != null ; e = e.next) {
                out.writeObject(e);
            }
        } catch (IOException e) {}
    }

    public Entry getEntry() {
        if (this.getFileSize() > 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
                return (Entry) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {}
    }
}
