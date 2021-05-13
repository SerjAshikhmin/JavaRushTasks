package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap();
        map.put("Пеликанов", "Акакий");
        map.put("Иванов", "Акакий");
        map.put("Дронников", "Демид");
        map.put("Полеева", "Элеонора");
        map.put("Эвкалиптов", "Акакий");
        map.put("Темникова", "Тереза");
        map.put("Бобриков", "Евпатий");
        map.put("Нильский", "Анатолий");
        map.put("Лежепеков", "Евгений");
        map.put("Пыль", "Афанасий");

        return map;
    }

    public static int getCountTheSameFirstName(Map<String, String> map, String name) {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet()){
            if (pair.getValue() == name) count++;
        }

        return count;
    }

    public static int getCountTheSameLastName(Map<String, String> map, String lastName) {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet()){
            if (pair.getKey() == lastName) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Map<String, String> map = createMap();
        getCountTheSameFirstName(map, "Акакий");
        getCountTheSameLastName(map, "Бобриков");
    }
}
