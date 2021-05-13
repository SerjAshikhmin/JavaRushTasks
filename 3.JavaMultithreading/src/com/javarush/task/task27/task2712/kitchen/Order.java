package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes = new ArrayList<>();

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public String toString() {
        return String.format("Your order: %s of %s", dishes, tablet);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getTotalCookingTime() {
        int coockingTime = 0;
        for (Dish dish : dishes) {
            coockingTime += dish.getDuration();
        }
        return coockingTime;
    }

    public boolean isEmpty() {
        return dishes.size() == 0;
    }
}
