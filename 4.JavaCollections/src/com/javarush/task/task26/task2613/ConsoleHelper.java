package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String st = null;
        try {
            st = bis.readLine();
            if (st.toLowerCase().equals("exit")) {
                writeMessage(res.getString("operation.EXIT"));
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException e) {}
        return st;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String currency = readString();
            if (currency.length() == 3) {
                currency = currency.toUpperCase();
                return currency;
            } else {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            try {
                writeMessage(res.getString("operation.DEPOSIT"));
                writeMessage(res.getString("choose.denomination.and.count.format"));
                String data[] = readString().split(" ");
                if (Integer.parseInt(data[0]) > 0 && Integer.parseInt(data[1]) > 0 && data.length == 2) {
                    return data;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            try {
                writeMessage(res.getString("choose.operation"));
                int i = Integer.parseInt(readString());
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage("Bye, interrupt");
    }
}
