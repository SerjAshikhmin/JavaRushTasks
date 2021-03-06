package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, V extends Convertable> Map<K, V> convert(List<V> list) {
        Map<K, V> result = new HashMap();
        list.forEach(value -> {
            K key = (K) value.getKey();
            result.put(key, value);
        });
        return result;
    }
}
