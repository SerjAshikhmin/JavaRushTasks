package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        public void close() throws IOException {
            fileScanner.close();
        }

        public Person read() throws IOException, ParseException {
            String[] data = fileScanner.nextLine().split(" ", 4);
            return new Person(data[1], data[2], data[0], new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH).parse(data[3]));
        }
    }
}
