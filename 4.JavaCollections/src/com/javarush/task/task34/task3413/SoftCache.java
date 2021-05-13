package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        //SoftReference<AnyObject> softReference = cacheMap.get(key);
        if (cacheMap.containsKey(key)) {
            return cacheMap.get(key).get();
        } else {
            return null;
        }
        //напишите тут ваш код
    }

    public AnyObject put(Long key, AnyObject value) {
        SoftReference<AnyObject> softReference = null;
        //напишите тут ваш код
        if (cacheMap.containsKey(key)) {
            softReference = cacheMap.get(key);
        }
        cacheMap.put(key, new SoftReference<>(value));
        if (softReference != null) {
            AnyObject obj = softReference.get();
            softReference.clear();
            return obj;
        } else {
            return null;
        }
    }

    public AnyObject remove(Long key) {
        SoftReference<AnyObject> softReference = null;
        //напишите тут ваш код
        if (cacheMap.containsKey(key)) {
            softReference = cacheMap.get(key);
        }
        cacheMap.remove(key);
        if (softReference != null) {
            AnyObject obj = softReference.get();
            softReference.clear();
            return obj;
        } else {
            return null;
        }
    }
}