package com.javarush.task.task19.task1927;
import java.io.*;
/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream  bArray = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(bArray);
        System.setOut(stream);
        testString.printSomething();

        System.setOut(consoleStream);
        String[] aData = bArray.toString().split("\n");
        for (int i = 0; i < aData.length; i++) {
            System.out.println(aData[i]);
            if (i % 2 == 1) System.out.println("JavaRush - курсы Java онлайн");
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
