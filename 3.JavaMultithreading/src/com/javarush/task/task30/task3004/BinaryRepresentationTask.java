package com.javarush.task.task30.task3004;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;
    List<BinaryRepresentationTask> subTasks = new LinkedList<>();

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask recTask = new BinaryRepresentationTask(b);
            recTask.fork();
            result = recTask.join() + result;
            subTasks.add(recTask);
        }
        return result;
    }
}
