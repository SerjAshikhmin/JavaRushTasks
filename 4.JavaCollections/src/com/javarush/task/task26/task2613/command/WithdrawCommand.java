package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int amount = Integer.parseInt(ConsoleHelper.readString());
                if (manipulator.isAmountAvailable(amount)) {
                    Map<Integer, Integer> withdrawMap = manipulator.withdrawAmount(amount);
                    for (Map.Entry<Integer, Integer> denominationEntry : withdrawMap.entrySet()) {
                        //ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denominationEntry.getKey(), denominationEntry.getValue()));
                        //ConsoleHelper.writeMessage(String.format("    %s - %s", denominationEntry.getKey(), denominationEntry.getValue()));
                    }
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currency));
                    break;
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));

                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }

    }
}
