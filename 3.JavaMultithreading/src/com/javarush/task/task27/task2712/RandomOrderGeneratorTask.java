package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tabletList;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tabletList = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            int random = (int)(Math.random() * tabletList.size());
            Tablet tablet = tabletList.get(random);
            System.out.println();
            tablet.createOrder();
            /*try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

    }
}
