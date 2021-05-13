package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException{
        Cook cook = new Cook("Вася");
        cook.setQueue(orderQueue);
        Cook cook2 = new Cook("Лаврентий");
        cook2.setQueue(orderQueue);
        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i + 1);
            tablet.setQueue(orderQueue);
            tabletList.add(tablet);
        }

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);
        Thread firstCookThread = new Thread(cook);
        firstCookThread.start();
        Thread secondCookThread = new Thread(cook2);
        secondCookThread.start();

        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL);
        Thread childThread = new Thread(task);
        childThread.start();

        /*DirectorTablet directorTablet = new DirectorTablet();
        System.out.println();
        directorTablet.printAdvertisementProfit();
        System.out.println();
        directorTablet.printCookWorkloading();
        System.out.println();
        directorTablet.printActiveVideoSet();
        System.out.println();
        directorTablet.printArchivedVideoSet();*/
    }
}
