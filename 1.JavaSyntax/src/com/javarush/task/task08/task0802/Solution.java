package com.javarush.task.task08.task0802;

/* 
Map из 10 пар
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Map<String, String> harvest = new HashMap();
        harvest.put("арбуз", "ягода");
        harvest.put("банан", "трава");
        harvest.put("вишня", "ягода");
        harvest.put("груша", "фрукт");
        harvest.put("дыня", "овощ");
        harvest.put("ежевика", "куст");
        harvest.put("жень-шень", "корень");
        harvest.put("земляника", "ягода");
        harvest.put("ирис", "цветок");
        harvest.put("картофель", "клубень");

        for (Map.Entry<String, String> pair : harvest.entrySet()){
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }

    }
}
