package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread t;

    public LoggingStateThread(Thread t) {
        this.t = t;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public void run() {
        super.run();
        State state = null;
        State lastState = null;
        while (state != State.TERMINATED) {
            state = t.getState();
            if (state != lastState) System.out.println(state);
            lastState = state;
        }
    }
}
