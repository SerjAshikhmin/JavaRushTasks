package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    protected void initDishes() {
        for (int i = 0; i < Math.random() * 5; i++) {
            int random = (int)(Math.random() * 5);
            Dish randomDish = Dish.values()[random];
            this.dishes.add(randomDish);
        }
        /*this.dishes.add(Dish.valueOf("Soup"));
        this.dishes.add(Dish.valueOf("Fish"));
        this.dishes.add(Dish.valueOf("Water"));*/
    }
}
