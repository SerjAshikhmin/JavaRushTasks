package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) {
    }

    public static class Thread1 extends Thread {
        public void run() {
            while (true) {}
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            while (true) {
                try {
                    sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }

    public static class Thread3 extends Thread {
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Thread4 extends Thread implements Message {
        public boolean isCancel = false;
        public void showWarning() {
            isCancel = true;
        }

        public void run() {
            while (!isCancel) {}
        }
    }

    public static class Thread5 extends Thread {
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int sum = 0;
            while (true) {
                try {
                    String text = reader.readLine();
                    if (text.equals("N")) break;
                    else sum += Integer.parseInt(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(sum);
        }
    }
}