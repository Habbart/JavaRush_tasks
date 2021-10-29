package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {


    static List<LogObject> listOfObjects = new ArrayList<>();

    public LogParser(Path path) {
        List<Path> listOfPath = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry: stream) {
                if(entry.toString().endsWith(".log")) listOfPath.add(entry);
            }
        } catch (DirectoryIteratorException e) {
            e.getCause();
        } catch (IOException e){
            e.printStackTrace();
        }
        listOfObjects = getListOfObjects(listOfPath);


    }
    //возвращает лист объектов распарсенный из логов
    public List<LogObject> getListOfObjects(List<Path> list){
        List<LogObject> result = new ArrayList<>();
        List<String> logs = new ArrayList<>();
        for (Path path :
                list) {
            try(BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())))){
                while(rd.ready()){
                    logs.add(rd.readLine());
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        for (String s :
                logs) {
            String[] parseStringFromLog = s.split("\t");
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date date = null;
            String ipTemp = parseStringFromLog[0];
            String userTemp = parseStringFromLog[1];
            Event event = Event.getEvent(parseStringFromLog[3].replaceAll("\\s+\\d+", ""));
            int taskTemp;
            try{ taskTemp = Integer.parseInt(parseStringFromLog[3].replaceAll("\\D+", ""));
            } catch (Exception e){
                taskTemp = 0;
            }

            Status status = Status.getStatus(parseStringFromLog[4]);
            try{
                date = formatter.parse(parseStringFromLog[2]);

            } catch (ParseException e){
                e.printStackTrace();
            }
            result.add(new LogObject(ipTemp, userTemp, date, event, status, taskTemp));
        }
//        for (LogObject l :
//                result) {
//            System.out.println(l);
//        }
        return result;
    }


    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {

        return listOfObjects.stream().
                filter(s -> dateBetweenDates(s.getDate(), after, before))
                .map(s -> s.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.equals(user))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(event))
                .map(s -> s.getIp()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getStatus().equals(status))
                .map(s -> s.getIp())
                .collect(Collectors.toSet());
    }

    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    @Override
    public Set<String> getAllUsers() {
        return  listOfObjects.stream().map(s -> s.getUser()).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {

        return listOfObjects.stream()
                            .filter(s -> dateBetweenDates(s.getDate(), after, before))
                            .map(s -> s.getUser()).collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {

        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getUser().equals(user))
                .map(s -> s.getEvent())
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getIp().equals(ip))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.LOGIN))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.WRITE_MESSAGE))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.SOLVE_TASK))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.SOLVE_TASK))
                .filter(s -> s.getTask() == task )
                .map(s -> s.getUser()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.DONE_TASK))
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getEvent().equals(Event.DONE_TASK))
                .filter(s -> s.getTask() == task)
                .map(s -> s.getUser())
                .collect(Collectors.toSet());
    }
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getUser().equals(user))
                .filter(s -> s.getEvent().equals(event))
                .map(s -> s.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getStatus().equals(Status.FAILED))
                .map(s -> s.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s -> s.getStatus().equals(Status.ERROR))
                .map(s -> s.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        try{return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getUser().equals(user))
                .filter(s -> s.getEvent().equals(Event.LOGIN))
                .map(s -> s.getDate())
                .sorted()
                .findFirst()
                .get();}
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        try{return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getUser().equals(user))
                .filter(s -> s.getTask() == task)
                .filter(s -> s.getEvent().equals(Event.SOLVE_TASK))
                .map(s -> s.getDate())
                .sorted()
                .findFirst()
                .get();}
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        try {
            return listOfObjects.stream()
                    .filter(s -> dateBetweenDates(s.getDate(), after, before))
                    .filter(s -> s.getUser().equals(user))
                    .filter(s -> s.getTask() == task)
                    .filter(s -> s.getEvent().equals(Event.DONE_TASK))
                    .map(s -> s.getDate())
                    .sorted()
                    .findFirst()
                    .get();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getUser().equals(user))
                .filter(s -> s.getEvent().equals(Event.WRITE_MESSAGE))
                .map(s -> s.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getUser().equals(user))
                .filter(s -> s.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(s -> s.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .map(s -> s.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getIp().equals(ip))
                .map(s -> s.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getUser().equals(user))
                .map(s -> s.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getStatus().equals(Status.FAILED))
                .map(s -> s.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getStatus().equals(Status.ERROR))
                .map(s -> s.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int)listOfObjects.stream()
                .filter(logObject ->  logObject.getTask() == task)
                .filter(Objects :: nonNull)
                .filter(logObject -> dateBetweenDates(logObject.getDate(), after, before))
                .filter(logObject ->  logObject.getEvent().equals(Event.SOLVE_TASK))
                .count();

    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int)listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getEvent().equals(Event.DONE_TASK))
                .filter(s ->  s.getTask() == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        Set<Integer> setOfTask = listOfObjects.stream()
                                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                                .filter(s ->  s.getEvent().equals(Event.SOLVE_TASK))
                                .map(s -> s.getTask())
                                .collect(Collectors.toSet());
        for (Integer i :
                setOfTask) {
            result.put(i, getNumberOfAttemptToSolveTask(i, after, before));
        }

        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        Set<Integer> setOfTask = listOfObjects.stream()
                .filter(s -> dateBetweenDates(s.getDate(), after, before))
                .filter(s ->  s.getEvent().equals(Event.DONE_TASK))
                .map(s -> s.getTask())
                .collect(Collectors.toSet());
        for (Integer i :
                setOfTask) {
            result.put(i, getNumberOfSuccessfulAttemptToSolveTask(i, after, before));
        }

        return result;
    }

    public Set<Date> getAllDates(){
        return listOfObjects.stream()
                .map(s -> s.getDate())
                .collect(Collectors.toSet());
    }
    public Set<Status> getAllStatus(){
        return listOfObjects.stream()
                .map(s -> s.getStatus())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Object> execute(String query) {


        switch (query) {
            case "get ip":
                return new HashSet<>(getUniqueIPs(null, null));
            case "get user":
                return new HashSet<>(getAllUsers());
            case "get event":
                return new HashSet<>(getAllEvents(null, null));
            case "get status":
                return new HashSet<>(getAllStatus());
            case "get date":
                return new HashSet<>(getAllDates());

        }
        String filed1AndFiled2 = query.substring(0, query.indexOf("=")).trim();
        String[] listOfRequest = filed1AndFiled2.split(" ");
        String field1 = listOfRequest[1];
        String field2 = listOfRequest[3];
        String value1 = query.substring(query.indexOf("=")+1).replaceAll("\"", "").trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Object> result = new HashSet<>();
        for (LogObject lo :
                listOfObjects) {
            switch (field2){
                case "ip" : if (lo.getIp().equals(value1)) result.add(lo.getFieldandValue(field1));
                break;
                case "user" : if(lo.getUser().equals(value1)) result.add(lo.getFieldandValue(field1));
                break;
                case "event" : if(lo.getEvent().equals(Event.getEvent(value1))) result.add(lo.getFieldandValue(field1));
                break;
                case "status" : if(lo.getStatus().equals(Status.getStatus(value1))) result.add(lo.getFieldandValue(field1));
                break;
                case "date" : try{
                    Date date = formatter.parse(value1);
                    if(lo.getDate().equals(date)) result.add(lo.getFieldandValue(field1));
                } catch (ParseException e){
                    //e.printStackTrace();
                }
                break;
            }
        }
        return result;
    }
}