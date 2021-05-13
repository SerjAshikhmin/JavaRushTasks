package com.javarush.task.task14.task1419;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.lang.module.FindException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeoutException;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        exceptions.add(new ArrayIndexOutOfBoundsException());
        exceptions.add(new FileAlreadyExistsException("zsdf"));
        exceptions.add(new IOException());
        exceptions.add(new TooManyListenersException());
        exceptions.add(new TimeoutException());
        exceptions.add(new EmptyStackException());
        exceptions.add(new NullPointerException());
        exceptions.add(new NumberFormatException());
        exceptions.add(new FileNotFoundException());

                //напишите тут ваш код
    }
}
