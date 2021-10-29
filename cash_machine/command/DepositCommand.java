package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+ "deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        String[] money = ConsoleHelper.getValidTwoDigits(currency);
        int nominal = Integer.parseInt(money[0]);
        int value = Integer.parseInt(money[1]);
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        currencyManipulator.addAmount(nominal, value);
        int total = value * nominal;
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), total, currency));
    }
}
