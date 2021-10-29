package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.*;
import com.javarush.task.task31.task3110.exception.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ZipCreateCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        try{
            ConsoleHelper.writeMessage("Создание архива.");
            ZipFileManager zipFileManager = getZipFileManager();
            ConsoleHelper.writeMessage("Пожалуйста, введите полное имя файла или директории для архивации.");
            Path fullPath = Paths.get(ConsoleHelper.readString());
            zipFileManager.createZip(fullPath);
            ConsoleHelper.writeMessage("Архив создан.");
        } catch (PathIsNotFoundException e){
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
