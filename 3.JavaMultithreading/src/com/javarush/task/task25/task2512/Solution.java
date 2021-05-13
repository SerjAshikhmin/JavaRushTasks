package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Throwable ex = e.getCause();
        List<Throwable> exceptionList = new ArrayList<>();
        exceptionList.add(e);
        while (ex != null) {
            exceptionList.add(ex);
            ex = ex.getCause();
        }

        for (int i = exceptionList.size() - 1; i >= 0; i--) {
            System.out.println(exceptionList.get(i));
        }
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
