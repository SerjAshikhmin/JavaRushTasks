package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message != null && message.contains(":")) {
                message.trim();
                String[] messageArray = message.split(": ");
                String name = messageArray[0];
                String text = messageArray[1];
                SimpleDateFormat format;
                switch (text) {
                    case "дата" :
                        format = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день" :
                        format = new SimpleDateFormat("d");
                        break;
                    case "месяц" :
                        format = new SimpleDateFormat("MMMM");
                        break;
                    case "год" :
                        format = new SimpleDateFormat("YYYY");
                        break;
                    case "время" :
                        format = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час" :
                        format = new SimpleDateFormat("H");
                        break;
                    case "минуты" :
                        format = new SimpleDateFormat("m");
                        break;
                    case "секунды" :
                        format = new SimpleDateFormat("s");
                        break;
                    default:
                        format = null;
                }
                Calendar date = new GregorianCalendar();
                if (format != null) sendTextMessage("Информация для " + name + ": " + format.format(date.getTime()));
            }
        }
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
