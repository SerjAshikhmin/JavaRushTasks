package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (head.x < 0 || head.x >= SnakeGame.WIDTH || head.y < 0 || head.y >= SnakeGame.HEIGHT || checkCollision(head)) {
            isAlive = false;
        } else {
            snakeParts.add(0, head);
            if (head.x == apple.x && head.y == apple.y) {
                apple.isAlive = false;
            } else {
                removeTail();
            }
        }
    }

    public GameObject createNewHead() {
        GameObject head;
        switch (direction) {
            case LEFT:
                head = new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
                break;
            case DOWN:
                head = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
                break;
            case UP:
                head = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
                break;
            case RIGHT:
                head = new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
                break;
            default:
                head = null;
                break;
        }
        return head;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject snakePart) {
        for (GameObject part : snakeParts) {
            if (part.x == snakePart.x && part.y == snakePart.y) {
                return true;
            }
        }
        return false;
    }

    public void draw(Game game) {
        Color color = Color.DODGERBLUE;
        if (isAlive == false) {
            color = Color.RED;
        }
        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0) {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, color, 75);
            } else {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, 75);
            }
        }
    }

    public int getLength() {
        return snakeParts.size();
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case LEFT:
                if (this.direction == Direction.RIGHT || snakeParts.get(0).y == snakeParts.get(1).y) return;
                break;
            case RIGHT:
                if (this.direction == Direction.LEFT || snakeParts.get(0).y == snakeParts.get(1).y) return;
                break;
            case UP:
                if (this.direction == Direction.DOWN || snakeParts.get(0).x == snakeParts.get(1).x) return;
                break;
            case DOWN:
                if (this.direction == Direction.UP || snakeParts.get(0).x == snakeParts.get(1).x) return;
                break;
        }
        this.direction = direction;
    }
}
