package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> denomination : denominations.entrySet()) {
            totalAmount += denomination.getKey() * denomination.getValue();
        }
        return totalAmount;
    }

    public int getTotalAmount(Map<Integer, Integer> map) {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> denomination : map.entrySet()) {
            totalAmount += denomination.getKey() * denomination.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());
        List<Integer> denominationList = new ArrayList<>(denominations.keySet());
        Collections.sort(denominationList, Comparator.reverseOrder());
        for (int i = 0; i < denominationList.size(); i++) {
            int currDenominationAmount = 0;
            int currentAmount = getTotalAmount(result);
            while (currentAmount <= expectedAmount && currDenominationAmount <= denominations.get(denominationList.get(i))) {
                currentAmount += denominationList.get(i);
                currDenominationAmount++;
                if (currentAmount <= expectedAmount && currDenominationAmount <= denominations.get(denominationList.get(i))) {
                    if (result.containsKey(denominationList.get(i))) {
                        result.put(denominationList.get(i), result.get(denominationList.get(i)) + 1);
                    } else {
                        result.put(denominationList.get(i), 1);
                    }
                }
            }
            currDenominationAmount--;
            if (currDenominationAmount != 0) {
                if (currDenominationAmount >= denominations.get(denominationList.get(i))) {
                    denominations.remove(denominationList.get(i));
                } else {
                    denominations.put(denominationList.get(i), denominations.get(denominationList.get(i)) - currDenominationAmount);
                }
            }
        }
        if (getTotalAmount(result) < expectedAmount) {
            throw new NotEnoughMoneyException();
        }
        return result;
    }
}
