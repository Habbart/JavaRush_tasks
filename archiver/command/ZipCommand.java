package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ZipCommand implements Command{
    
    public ZipFileManager getZipFileManager() throws Exception{
        ConsoleHelper.writeMessage("Введите путь до архива. В случае создания нового архива укажите название файла:");
        Path fullPath = Paths.get(ConsoleHelper.readString());
        return new ZipFileManager(fullPath);
    }

}
