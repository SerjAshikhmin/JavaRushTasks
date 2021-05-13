package com.javarush.task.task25.task2514;

import java.time.Year;

/*
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            YieldRunnable ye = new YieldRunnable(i);
            Thread t = new Thread(ye);
            t.start();
        }
    }
}
