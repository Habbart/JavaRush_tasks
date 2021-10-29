package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    
    protected Connection connection;
    private volatile boolean clientConnected = false;
    
    
    public void run(){
            Thread thread = getSocketThread();
            thread.setDaemon(true);
            thread.start();
            synchronized(this){
                try{
                    this.wait();
                } catch (InterruptedException e){
                    ConsoleHelper.writeMessage("Exception has been occurred.");
                    return;
                }
            }
            String messageForClient = clientConnected ? "Connection is successful, please enter 'exit' for exit" : "Exception has been occurred during work";
            ConsoleHelper.writeMessage(messageForClient);
            while(clientConnected){
                String message = ConsoleHelper.readString();
                if(message.equalsIgnoreCase("exit")){
                    break;
                }
                if(shouldSendTextFromConsole()){
                    sendTextMessage(message);
                }
            }
    }
    public static void main(String[] args){
        new Client().run();
    }
    
    
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Please enter server address");
        return ConsoleHelper.readString();
    }
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Please enter server port");
        return ConsoleHelper.readInt();
    }
    
    protected String getUserName(){
        ConsoleHelper.writeMessage("Please enter your name");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    protected void sendTextMessage(String text){
        try{
            connection.send(new Message(MessageType.TEXT, text));
        }catch(IOException e){
            ConsoleHelper.writeMessage("Can't send message.");
            clientConnected = false;
        }
    }
    
    public class SocketThread extends Thread{
        @Override
        public void run(){
            try{
                String address = getServerAddress();
                int port = getServerPort();
                Socket socket = new Socket(address, port);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch(IOException | ClassNotFoundException e){
                notifyConnectionStatusChanged(false);
            }
        }
        
        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if(message.getType() == MessageType.NAME_REQUEST){
                     String name = getUserName();
                     Message reply = new Message(MessageType.USER_NAME, name);
                     connection.send(reply);
                } else if(message.getType() == MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                    return;
                } else{
                    throw new IOException("Unexpected MessageType");
                }
                
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                MessageType type = message.getType();
                if(type == null){
                    throw new IOException("Unexpected MessageType");
                }
                String data = message.getData();
                switch(type){
                    case TEXT : processIncomingMessage(data);
                    break;
                    case USER_ADDED: informAboutAddingNewUser(data);
                    break;
                    case USER_REMOVED : informAboutDeletingNewUser(data);
                    break;
                    default : throw new IOException("Unexpected MessageType");
                }
            }
           
        }
        
        
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " has join the chat.");
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " has left the chat.");
        }
        protected  void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized(Client.this){
                Client.this.clientConnected = clientConnected;
                Client.this.notifyAll();
            }
        }
        
    }
}