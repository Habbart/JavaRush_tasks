package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private TreeMap<Integer, Integer> denominations = new TreeMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.get(denomination) == null) {
            denominations.put(denomination, count);
        } else {
            int temp = denominations.get(denomination);
            temp += count;
            denominations.put(denomination, temp);
        }
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Integer denomination : denominations.keySet()) {
            totalAmount += denomination * denominations.get(denomination);
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    /* выбираем сумму для снятия из существующих банкнот в банкомате и убираем их из общего списка
     *  исользован жадный алгоритм
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException, ConcurrentModificationException {
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (Map.Entry<Integer, Integer> pairs : denominations.descendingMap().entrySet()) {
            int nominal = pairs.getKey();
            int amount = pairs.getValue();
            for (int i = amount; i > 0; i--) {
                if (i * nominal <= expectedAmount) {
                    result.put(nominal, i);
                    expectedAmount = expectedAmount - i * nominal;
                    break;
                }
            }
        }
        removeBancknotesFromMap(result);
        if (expectedAmount != 0) throw new NotEnoughMoneyException();
        return result.descendingMap();
    }

    private void removeBancknotesFromMap(Map<Integer, Integer> source) {
        source.forEach((k, v) -> {
            int temp = denominations.get(k);
            temp -= v;
            if (temp != 0) {
                denominations.put(k, temp);
            } else {
                denominations.remove(k);
            }
        });
    }
}
