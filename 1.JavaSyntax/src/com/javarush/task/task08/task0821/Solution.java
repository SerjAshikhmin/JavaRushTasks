package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleMap();

        printPeopleMap(map);
    }

    public static Map<String, String> createPeopleMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<String, String>();

        map.put("Пеликанов", "Акакий");
        map.put("Иванов", "Акакий");
        map.put("Дронников", "Демид");
        map.put("Писанькина", "Элеонора");
        map.put("Эвкалиптов", "Акакий");
        map.put("Темникова", "Тереза");
        map.put("Дронников", "Евпатий");
        map.put("Нильский", "Анатолий");
        map.put("Лежепеков", "Евгений");
        map.put("Пыль", "Афанасий");

        return map;
    }

    public static void printPeopleMap(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
