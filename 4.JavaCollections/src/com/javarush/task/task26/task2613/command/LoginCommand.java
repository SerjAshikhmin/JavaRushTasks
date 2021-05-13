package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.ResourceBundle;

class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            try {
                String dataLogin = ConsoleHelper.readString();
                String dataPin = ConsoleHelper.readString();
                long userLogin = Long.parseLong(dataLogin);
                int userPin = Integer.parseInt(dataPin);
                if (userLogin > 0 && dataLogin.length() == 12 && userPin > 0 && dataPin.length() == 4) {
                    if (validCreditCards.containsKey(dataLogin) && validCreditCards.getString(dataLogin).equals(dataPin)) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), dataLogin));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), dataLogin));
                    }
                } else {
                    throw new NumberFormatException();
                }
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                //ConsoleHelper.writeMessage("Data doesn't valid, repeat");
            }
        }
    }
}
