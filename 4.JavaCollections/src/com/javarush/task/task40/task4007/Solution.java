package com.javarush.task.task40.task4007;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        printDate("20.1.2019 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) throws ParseException {
        //напишите тут ваш код
        DateFormat formatter;
        Calendar cal = Calendar.getInstance();
        if (date.contains(" ")) {
            formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            cal.setTime(formatter.parse(date));
            printDateDetails(cal);
            printTimeDetails(cal);
        } else {
            if (date.contains(".")) {
                formatter = new SimpleDateFormat("dd.MM.yyyy");
                cal.setTime(formatter.parse(date));
                printDateDetails(cal);
            } else {
                if (date.contains(":")) {
                    formatter = new SimpleDateFormat("HH:mm:ss");
                    cal.setTime(formatter.parse(date));
                    printTimeDetails(cal);
                }
            }
        }
    }

    private static void printTimeDetails(Calendar cal) {
        System.out.println(String.format("AM или PM: %s", cal.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.ENGLISH)));
        System.out.println(String.format("Часы: %s", cal.get(Calendar.HOUR)));
        System.out.println(String.format("Часы дня: %s", cal.get(Calendar.HOUR_OF_DAY)));
        System.out.println(String.format("Минуты: %s", cal.get(Calendar.MINUTE)));
        System.out.println(String.format("Секунды: %s", cal.get(Calendar.SECOND)));
    }

    private static void printDateDetails(Calendar cal) {
        System.out.println(String.format("День: %s", cal.get(Calendar.DAY_OF_MONTH)));
        System.out.println(String.format("День недели: %s", cal.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : cal.get(Calendar.DAY_OF_WEEK) - 1));
        System.out.println(String.format("День месяца: %s", cal.get(Calendar.DAY_OF_MONTH)));
        System.out.println(String.format("День года: %s", cal.get(Calendar.DAY_OF_YEAR)));
        System.out.println(String.format("Неделя месяца: %s", cal.get(Calendar.WEEK_OF_MONTH)));
        System.out.println(String.format("Неделя года: %s", cal.get(Calendar.WEEK_OF_YEAR)));
        System.out.println(String.format("Месяц: %s", cal.get(Calendar.MONTH) + 1));
        System.out.println(String.format("Год: %s", cal.get(Calendar.YEAR)));
    }

}
