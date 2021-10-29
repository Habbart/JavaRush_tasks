package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String userCard = "";
            String userPin = "";
            try {
                userCard = ConsoleHelper.readString();
                userPin = ConsoleHelper.readString();
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
            if(validCreditCards.containsKey(userCard) && validCreditCards.getString(userCard).equals(userPin)){
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), userCard));
                return;
            } else{
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCard));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
