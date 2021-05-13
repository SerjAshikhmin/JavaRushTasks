package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest prepare = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            String[] names = prepare.fullyQualifiedNames();
            for (String name : names) {
                System.out.println(name);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest prepare = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class<?>[] values = prepare.value();
            for (Class<?> value : values) {
                System.out.println(value.getSimpleName());
            }
            return true;
        } else {
            return false;
        }
    }
}
