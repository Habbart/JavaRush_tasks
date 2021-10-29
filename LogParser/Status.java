package com.javarush.task.task39.task3913;

public enum Status {
    OK,
    FAILED,
    ERROR;

    public static Status getStatus(String status){
        switch (status){
            case "OK" : return Status.OK;
            case "FAILED" : return Status.FAILED;
            case "ERROR" : return Status.ERROR;
        }
        return Status.OK;
    }
}