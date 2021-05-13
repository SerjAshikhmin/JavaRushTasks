package com.javarush.task.task08.task0801;

/* 
Set из растений
*/

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Set<String> harvest = new HashSet();
        harvest.add("арбуз");
        harvest.add("банан");
        harvest.add("вишня");
        harvest.add("груша");
        harvest.add("дыня");
        harvest.add("ежевика");
        harvest.add("женьшень");
        harvest.add("земляника");
        harvest.add("ирис");
        harvest.add("картофель");

        for (String text : harvest) {
            System.out.println(text);
        }

    }
}
