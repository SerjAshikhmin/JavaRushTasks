package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread t;
    @Override
    public void run() {
        while (!t.interrupted()) {
            System.out.println(t.getName());
            try {
                t.sleep(100);
            } catch (InterruptedException e) {break;}
        }
    }

    public void start(String threadName) {
        t = new Thread(this, threadName);
        t.start();
    }

    public void stop(){
        t.interrupt();
    }
}
