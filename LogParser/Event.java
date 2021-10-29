package com.javarush.task.task39.task3913;

public enum Event {
    LOGIN,
    DOWNLOAD_PLUGIN,
    WRITE_MESSAGE,
    SOLVE_TASK,
    DONE_TASK;

    public static Event getEvent (String event){
        switch (event){
            case "LOGIN" : return Event.LOGIN;
            case "DOWNLOAD_PLUGIN" : return Event.DOWNLOAD_PLUGIN;
            case "WRITE_MESSAGE" : return Event.WRITE_MESSAGE;
            case "SOLVE_TASK" : return Event.SOLVE_TASK;
            case "DONE_TASK" : return Event.DONE_TASK;

        }
        return Event.LOGIN;
    }
}