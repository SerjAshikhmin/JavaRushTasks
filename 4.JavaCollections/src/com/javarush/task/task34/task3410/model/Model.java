package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

public class Model {

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel;
    LevelLoader levelLoader;

    public Model() {
        this.currentLevel = 1;
        this.levelLoader = new LevelLoader(Paths.get(".\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));
        this.gameObjects = levelLoader.getLevel(currentLevel);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = getGameObjects().getPlayer();
        GameObject stopped = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
                stopped = gameObject;
            }
        }
        if ((stopped == null)) {
            return false;
        }
        if (stopped instanceof Box) {
            Box stoppedBox = (Box) stopped;
            if (checkWallCollision(stoppedBox, direction)) {
                return true;
            }
            for (Box box : gameObjects.getBoxes()) {
                if (stoppedBox.isCollision(box, direction)) {
                    return true;
                }
            }
            determineCoordinatesOffsetAndMove(stoppedBox, direction);
        }
        return false;
    }
        /*for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                for (Box gameObjectsBox : gameObjects.getBoxes()) {
                    if (!box.equals(gameObjectsBox) && box.isCollision(gameObjectsBox, direction)) {
                        return true;
                    }
                }
                for (Wall wall : gameObjects.getWalls()) {
                    if (box.isCollision(wall, direction)) {
                        return true;
                    }
                }
            }
        }
        if (!checkWallCollision(player, direction)) {
            for (Box box : gameObjects.getBoxes()) {
                if (player.isCollision(box, direction)) {
                    determineCoordinatesOffsetAndMove(box, direction);
                }
            }
            return false;
        } else {
            return true;
        }*/

    public void checkCompletion() {
        int boxesInHome = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    boxesInHome++;
                }
            }
        }
        if (boxesInHome == gameObjects.getHomes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (!checkWallCollision(player, direction) && !checkBoxCollisionAndMoveIfAvailable(direction)) {
            determineCoordinatesOffsetAndMove(player, direction);
            checkCompletion();
        }
    }

    private void determineCoordinatesOffsetAndMove(Movable object, Direction direction) {
        int dx = 0;
        int dy = 0;
        if (direction == Direction.LEFT) dx = - GameObject.FIELD_CELL_SIZE;
        if (direction == Direction.RIGHT) dx = GameObject.FIELD_CELL_SIZE;
        if (direction == Direction.DOWN) dy = GameObject.FIELD_CELL_SIZE;
        if (direction == Direction.UP) dy = - GameObject.FIELD_CELL_SIZE;
        object.move(dx, dy);
    }
}
