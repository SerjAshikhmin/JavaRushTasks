package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        setWidth(2);
        setHeight(2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        graphics.drawRect(getX() + FIELD_CELL_SIZE, getY() + FIELD_CELL_SIZE, getWidth(), getHeight());
    }
}
