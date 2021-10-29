package com.javarush.task.task39.task3913.query;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.Status;

import java.util.Date;

public class LogObject {

    private String ip;
    private String user;
    private Date date;
    private Event event;
    private Status status;
    private int task;

    public LogObject(String ip, String user, Date date, Event event, Status status, int task) {
        this.setIp(ip);
        this.setUser(user);
        this.setDate(date);
        this.setEvent(event);
        this.setStatus(status);
        this.setTask(task);
    }

    @Override
    public String toString() {
        return "LogObject{" +
                "ip='" + getIp() + '\'' +
                ", user='" + getUser() + '\'' +
                ", date=" + getDate() +
                ", event=" + getEvent() +
                ", status=" + getStatus() +
                ", task=" + getTask() +
                '}';
    }
    
    public Object getFieldandValue(String field){

            switch(field){
            case "ip" : return this.getIp();
            case "user" : return this.getUser();
            case "event" :return this.getEvent();
            case "status" :return this.getStatus();
            case "date" : return this.getDate();
        } 
        return null;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }
}
