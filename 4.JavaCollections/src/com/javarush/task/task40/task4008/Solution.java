package com.javarush.task.task40.task4008;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) throws ParseException {
        //напишите тут ваш код
        DateTimeFormatter formatter;
        if (date.contains(" ")) {
            formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            String[] stringDate = date.split(" ");
            printDateDetails(LocalDate.parse(stringDate[0], formatter));
            formatter = DateTimeFormatter.ofPattern("H:mm:ss");
            printTimeDetails(LocalTime.parse(stringDate[1], formatter));
        } else {
            if (date.contains(".")) {
                formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                LocalDate lDate = LocalDate.parse(date, formatter);
                printDateDetails(lDate);
            } else {
                if (date.contains(":")) {
                    formatter = DateTimeFormatter.ofPattern("H:mm:ss");
                    LocalTime time = LocalTime.parse(date, formatter);
                    printTimeDetails(time);
                }
            }
        }
    }

    private static void printTimeDetails(LocalTime time) {
        System.out.println(String.format("AM или PM: %s", time.getHour() > 12 ? "PM" : "AM"));
        System.out.println(String.format("Часы: %s", time.getHour() > 12 ? time.getHour() - 12 : time.getHour()));
        System.out.println(String.format("Часы дня: %s", time.getHour()));
        System.out.println(String.format("Минуты: %s", time.getMinute()));
        System.out.println(String.format("Секунды: %s", time.getSecond()));
    }

    private static void printDateDetails(LocalDate date) {
        System.out.println(String.format("День: %s", date.getDayOfMonth()));
        System.out.println(String.format("День недели: %s", date.getDayOfWeek().getValue()));
        System.out.println(String.format("День месяца: %s", date.getDayOfMonth()));
        System.out.println(String.format("День года: %s", date.getDayOfYear()));
        System.out.println(String.format("Неделя месяца: %s", date.get(ChronoField.ALIGNED_WEEK_OF_MONTH)));
        System.out.println(String.format("Неделя года: %s", date.get(ChronoField.ALIGNED_WEEK_OF_YEAR)));
        System.out.println(String.format("Месяц: %s", date.getMonth().getValue()));
        System.out.println(String.format("Год: %s", date.getYear()));
    }

}

