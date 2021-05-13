package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("Пеликанов", 653);
        map.put("Иванов", 134);
        map.put("Дронников", 666);
        map.put("Писанькина", 322);
        map.put("Эвкалиптов", 4444);
        map.put("Темникова", 111);
        map.put("Бобриков", 5634);
        map.put("Нильский", 34);
        map.put("Лежепеков", 54);
        map.put("Пыль", 1);

        return map;

    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            if (pair.getValue() < 500)
                iterator.remove();
        }

    }

    public static void main(String[] args) {
        removeItemFromMap(createMap());
    }
}