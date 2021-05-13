package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //напишите тут ваш код
        Map<String, Cat> map = new HashMap<String, Cat>();
        map.put("sdf", new Cat("sdf"));
        map.put("asdfg", new Cat("asdfg"));
        map.put("sghm", new Cat("sghm"));
        map.put("hjh", new Cat("hjh"));
        map.put("vbncvbn", new Cat("vbncvbn"));
        map.put("dgf", new Cat("dgf"));
        map.put("tyyj", new Cat("tyyj"));
        map.put("vh,m", new Cat("vh,m"));
        map.put("bnmnm", new Cat("bnmnm"));
        map.put("sfdg", new Cat("sfdg"));


        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //напишите тут ваш код
        Set<Cat> set = new HashSet<Cat>();
        for (Map.Entry<String, Cat> cat : map.entrySet()) {
            set.add(cat.getValue());
        }

        return set;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
