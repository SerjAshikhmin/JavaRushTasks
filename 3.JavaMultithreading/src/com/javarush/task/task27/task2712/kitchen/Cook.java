package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Restaurant;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %smin", order, order.getTotalCookingTime()));
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {

        }
        StatisticManager statisticManager = StatisticManager.getInstance();
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public void run() {
        while (true) {
            boolean isCookingOrder = false;
            if (!this.isBusy()) {
                if (queue.size() != 0) {
                    Order order = queue.poll();
                    this.startCookingOrder(order);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
