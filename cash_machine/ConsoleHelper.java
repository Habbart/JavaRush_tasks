package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+ "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result = new String();
        writeMessage(res.getString("choose.currency.code"));
        while (result.length() != 3) {
            if (result.length() != 0) writeMessage(res.getString("invalid.data"));
            result = readString().trim();
        }
        return result.toUpperCase(Locale.ROOT);
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String temp = readString();
            if (temp == null || temp.split(" ").length != 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            } else {
                try {
                    String[] result = temp.split(" ");
                    if (Integer.parseInt(result[0]) <= 0 || Integer.parseInt(result[1]) <= 0) {
                        writeMessage(res.getString("invalid.data"));
                        continue;
                    }
                    return result;
                } catch (NumberFormatException e) {
                    writeMessage(res.getString("invalid.data"));
                    continue;
                }
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage(res.getString("operation.INFO") + " - 1");
            writeMessage(res.getString("operation.DEPOSIT") + " - 2");
            writeMessage(res.getString("operation.WITHDRAW") + " - 3");
            writeMessage(res.getString("operation.EXIT") + " - 4");
            String answer = readString();
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(answer));
            } catch (Exception e) {
                continue;
            }
        }
    }

    public static void printExitMessage(){
        System.out.println(res.getString("the.end"));
    }


}
