package com.javarush.task.task19.task1914;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Решаем пример
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

        Pattern pattern = Pattern.compile("^(\\d+)\\s*([+\\-*])\\s*(\\d+)\\s*=");
        Matcher matcher = pattern.matcher(result);
        int n = 0;
        while (matcher.find()) {
            int n1 = Integer.parseInt(matcher.group(1));
            int n2 = Integer.parseInt(matcher.group(3));
            if (matcher.group(2).equals("+")) n = n1 + n2;
            else if (matcher.group(2).equals("-")) n = n1 - n2;
                else n = n1 * n2;
        }

        System.setOut(consoleStream);
        System.out.println(result.trim() + " " + n);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

