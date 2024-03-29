package com.javarush.task.task30.task3008;

import java.io.*;

public class Message implements Serializable {
    private final MessageType type;
    private final String data;
    
    public MessageType getType(){
        return this.type;
    }
    public String getData(){
        return this.data;
    }
    public Message(MessageType type){
        this.type =type;
        data = null;
    }
    public Message(MessageType type, String data){
        this.type =type;
        this.data = data;
    }
}