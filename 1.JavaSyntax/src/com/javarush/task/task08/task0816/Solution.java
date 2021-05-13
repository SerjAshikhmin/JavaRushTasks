package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Map<String, Date> map = new HashMap<>();
        map.put("Сталлоне", dateFormat.parse("MAY 1 2012"));
        map.put("Петькин", dateFormat.parse("JUNE 23 2013"));
        map.put("Эклипскин", dateFormat.parse("DECEMBER 1 2012"));
        map.put("Федечко", dateFormat.parse("OCTOBER 1 2012"));
        map.put("Касторкина", dateFormat.parse("JULY 1 2012"));
        map.put("Файду", dateFormat.parse("MARCH 1 2012"));
        map.put("Вейналду", dateFormat.parse("APRIL 1 2012"));
        map.put("Сигурдаррсон", dateFormat.parse("NOVEMBER 1 2012"));
        map.put("Фтапки", dateFormat.parse("JANUARY 1 2012"));
        map.put("Вишневский", dateFormat.parse("AUGUST 1 2012"));

        return map;
        //напишите тут ваш код
    }

    @SuppressWarnings("deprecation")
    public static void removeAllSummerPeople(Map<String, Date> map) {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Date> pair = iterator.next();
            if (pair.getValue().getMonth() == 5 || pair.getValue().getMonth() == 6 || pair.getValue().getMonth() == 7)
                iterator.remove();
        }
    }

    public static void main(String[] args) throws ParseException{
        removeAllSummerPeople(createMap());
    }
}
