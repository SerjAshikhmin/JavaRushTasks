package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        testStrategy(strategy, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String string : strings) {
            set.add(shortener.getId(string));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long key : keys) {
            set.add(shortener.getString(key));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        long startTime = (new Date()).getTime();
        Set<Long> keys = getIds(shortener, strings);
        long endTime = (new Date()).getTime();
        Helper.printMessage("getIds time = " + String.valueOf(endTime - startTime));

        startTime = (new Date()).getTime();
        Set<String> testStrings = getStrings(shortener, keys);
        endTime = (new Date()).getTime();
        Helper.printMessage("getStrings time = " + String.valueOf(endTime - startTime));

        if (testStrings.equals(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
