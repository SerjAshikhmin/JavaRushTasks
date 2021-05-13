package com.javarush.task.task09.task0910;

import java.util.ArrayList;
import java.util.List;

/* 
Исключение при работе с коллекциями List
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            List<String> list = new ArrayList<String>();
            String s = list.get(18);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        }
    }
}