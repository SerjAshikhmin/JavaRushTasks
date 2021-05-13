package com.javarush.task.task34.task3408;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (!cache.containsKey(key)) {
            Class[] params = {key.getClass()};
            cache.put(key, clazz.getConstructor(params).newInstance(key));
        }
        return cache.get(key);
    }

    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method getKeyMethod = obj.getClass().getDeclaredMethod("getKey", null);
            getKeyMethod.setAccessible(true);
            K key = (K) getKeyMethod.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
