package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.Helper;

import java.io.*;
import java.lang.reflect.Parameter;
import java.nio.file.*;
import java.util.jar.Attributes;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {

            path = Files.createTempFile(Helper.generateRandomString(), ".tmp");
            path.toFile().deleteOnExit();
            Files.deleteIfExists(path);
            Files.createFile(path);

        } catch (IOException e){
            System.out.println("IOException was caught");
        }
    }

    public long getFileSize(){
        long result = 0;
        try {
            result = Files.size(path);
        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public void putEntry(Entry entry){

        try(OutputStream outputStream = Files.newOutputStream(path);
            ObjectOutputStream  objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(entry);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public Entry getEntry(){
        Entry result = null;
        if(getFileSize() == 0) return result;
        try(InputStream inputStream = Files.newInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            result = (Entry)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
