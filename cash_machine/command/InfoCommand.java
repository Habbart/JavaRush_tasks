package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.writeMessage;


class InfoCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+ "info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> set = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (set.size() == 0) writeMessage(res.getString("no.money"));
        for (CurrencyManipulator cm :
                set) {
            if (cm.hasMoney()) writeMessage(cm.getCurrencyCode() + " - " + cm.getTotalAmount());
            else writeMessage(res.getString("no.money"));
        }
    }
}
