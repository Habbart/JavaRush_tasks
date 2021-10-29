package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Please enter port of Server");
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
             serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Server is on");
            while(true){
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
    //отправляет сообщение всем пользователям
    public static void sendBroadcastMessage (Message message){
        connectionMap.forEach((k, v) -> {
            try {
                v.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Can't send message");
            }
        });
    }

    private static class Handler extends Thread{
        private Socket socket;

        private Handler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            String userName = "";
            ConsoleHelper.writeMessage("Connection successfull with" + socket.getRemoteSocketAddress());
            //добавляем пользователя и ждем пока он пообщается
            try(Connection connection = new Connection(socket)){
                userName = serverHandshake(connection); 
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch(IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("Got problem with exchange of data with remote socket.");
            }
            //удаляем пользователя и сообщаем об этом всем юзерам
            connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            ConsoleHelper.writeMessage("Connection has been closed.");
        }
        
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            Message requestForName = new Message(MessageType.NAME_REQUEST, "Please enter your name");
            Message replyName;
            while (true){
                connection.send(requestForName);
                replyName = connection.receive();
                String name = replyName.getData();
                if(name != ""
                        && replyName.getType() == MessageType.USER_NAME
                        && !connectionMap.containsKey(name)){
                    connectionMap.put(name, connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED, "Name was successfully added."));
                    return name;
                }
            }

        }
    
        private void notifyUsers(Connection connection, String userName) throws IOException{
            connectionMap.forEach((k, v) -> {
            if(k.equals(userName)) return;
            try{
                connection.send(new Message(MessageType.USER_ADDED, k));
            } catch(IOException e){
                e.printStackTrace();
            }
        });
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message messageFromClient = connection.receive();
                if(messageFromClient.getType()==(MessageType.TEXT)){
                    String data = userName + ": " + messageFromClient.getData();
                    Message messageToAll = new Message(MessageType.TEXT, data);
                    sendBroadcastMessage(messageToAll);
                } else{
                    ConsoleHelper.writeMessage("Please enter correct message");
                }
            }
        }
    }
}