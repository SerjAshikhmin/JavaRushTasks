package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        public AmigoThreadFactory() {
            poolNumber.incrementAndGet();
        }

        private static AtomicInteger poolNumber = new AtomicInteger();
        private AtomicInteger threadNumber = new AtomicInteger();

        public Thread newThread(Runnable r) {
            threadNumber.incrementAndGet();
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            thread.setPriority(5);
            thread.setName(Thread.currentThread().getThreadGroup().getName() + "-pool-" + poolNumber + "-thread-" + threadNumber);
            return thread;
        }
    }
}
