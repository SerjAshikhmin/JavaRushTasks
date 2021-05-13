package com.javarush.task.task08.task0817;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<String, String>();
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

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        Map<String, String> map1 = new HashMap<String, String>();
        map1.putAll(map);
        Iterator<Map.Entry<String, String>> iterator1 = map1.entrySet().iterator();

        while (iterator1.hasNext()) {
            Map.Entry<String, String> pair1 = iterator1.next();
            if (Collections.frequency(map.values(), pair1.getValue()) > 1) {
                removeItemFromMapByValue(map, pair1.getValue());
            }
        }
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = createMap();
        removeTheFirstNameDuplicates(map);
    }
}
