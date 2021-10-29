package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client{


    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public  class BotSocketThread extends SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if(!message.contains(":") || message.contains("Я бот.")) return;
            String[] arrayMessage = message.split(":");
            String name = arrayMessage[0].trim();
            String data = arrayMessage[1].trim();
            String answer = String.format("Информация для %s: ", name);
            SimpleDateFormat sdf = null;
            switch (data.toLowerCase()){
                case "дата":
                    sdf = new SimpleDateFormat("d.MM.YYYY");
                    break;
                case "день": sdf = new SimpleDateFormat("d");
                    break;
                case "месяц": sdf = new SimpleDateFormat("MMMM");
                    break;
                case  "год": sdf = new SimpleDateFormat("YYYY");
                    break;
                case "время": sdf = new SimpleDateFormat("H:mm:ss");
                    break;
                case "час": sdf = new SimpleDateFormat("H");
                    break;
                case "минуты": sdf = new SimpleDateFormat("m");
                    break;
                case "секунды": sdf = new SimpleDateFormat("s");
                    break;
                default: return;
            }
            answer = answer + sdf.format(Calendar.getInstance().getTime());
            BotClient.this.sendTextMessage(answer);
        }
    }


    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int)(Math.random() * 100));
    }
}
