package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        int rigthAmount;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            rigthAmount = getValidAmountForWithdraw();
            if (!currencyManipulator.isAmountAvailable(rigthAmount)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                break;
            }
            try {
                Map<Integer, Integer> result = currencyManipulator.withdrawAmount(rigthAmount);
                result.forEach((k, v) -> ConsoleHelper.writeMessage(k + " - " + v));
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), rigthAmount, currency));
                break;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                break;
            } catch (ConcurrentModificationException e) {
                break;
            }
        }
    }
    public int getValidAmountForWithdraw() {

        int result = 0;
        while (true) {
            try {
                String amount = ConsoleHelper.readString();
                result = Integer.parseInt(amount);
                if (result <= 0 ) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
            } catch (Exception e) {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                continue;
            }
            return result;
        }
    }
}
