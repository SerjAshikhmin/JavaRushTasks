package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", "null");
        System.out.println(getQuery(map));
        System.out.println(map);
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() != null && pair.getKey() != null) {
                query.append(pair.getKey() + " = '" + pair.getValue() + "' and ");
            }
        }
        if (query.length() >= 1) query.delete(query.length() - 5, query.length());
        return query.toString();
    }
}
