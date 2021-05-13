package com.javarush.task.task19.task1912;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Ридер обертка 2
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream  bArray = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(bArray);
        System.setOut(stream);
        testString.printSomething();
        String result = bArray.toString();
        result = result.replaceAll("te", "??");

        System.setOut(consoleStream);
        System.out.println(result);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
