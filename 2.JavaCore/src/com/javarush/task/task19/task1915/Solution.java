package com.javarush.task.task19.task1915;
import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        PrintStream consoleStream = System.out;
        ByteArrayOutputStream  bArray = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(bArray);
        System.setOut(stream);
        testString.printSomething();
        String result = bArray.toString();
        System.setOut(consoleStream);

        System.out.println(result);
        FileOutputStream writer = new FileOutputStream(file);
        bArray.writeTo(writer);
        writer.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

