package com.javarush.task.task08.task0827;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MAY 1 2013"));
    }

    public static boolean isDateOdd(String date) {
        long sinceSOYm = 0;
        try {
            Date date1 = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH).parse(date);
            String start = "JANUARY 0 " + date.charAt(date.length()-4) + date.charAt(date.length()-3) +
                    date.charAt(date.length()-2) +  date.charAt(date.length()-1);

            Date startOfYear = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH).parse(start);
            sinceSOYm = date1.getTime() - startOfYear.getTime();
        } catch (Exception ex) {ex.printStackTrace();}

        if ( sinceSOYm / 86400000 % 2 == 1) return true;
        else return false;

    }
}
