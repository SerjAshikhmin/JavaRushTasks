package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int dx = this.getX();
        int dy = this.getY();
        if (direction == Direction.LEFT) dx = dx - FIELD_CELL_SIZE;
        if (direction == Direction.RIGHT) dx = dx + FIELD_CELL_SIZE;
        if (direction == Direction.DOWN) dy = dy + FIELD_CELL_SIZE;
        if (direction == Direction.UP) dy = dy - FIELD_CELL_SIZE;
        if (dx == gameObject.getX() && dy == gameObject.getY()) {
            return true;
        }
        return false;
    }
}
