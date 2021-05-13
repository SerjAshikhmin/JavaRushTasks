package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {

    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;
    private int passedCarsCount = 0;
    private List<RoadObject> items = new ArrayList<>();

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    public void draw(Game game) {
        for (RoadObject item : items) {
            item.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject item : items) {
            item.move(item.speed + boost, items);
        }
        deletePassedItems();
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    public boolean checkCrush(PlayerCar player) {
        for (RoadObject item : items) {
            if (item.isCollision(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isThornExists() {
        for (RoadObject item : items) {
            if (item.type == RoadObjectType.THORN) {
                return true;
            }
        }
        return false;
    }

    private boolean isMovingCarExists() {
        for (RoadObject item : items) {
            if (item.type == RoadObjectType.DRUNK_CAR) {
                return true;
            }
        }
        return false;
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject item : items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }

    private void generateThorn(Game game) {
        int random = game.getRandomNumber(100);
        if (random < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateMovingCar(Game game) {
        int random = game.getRandomNumber(100);
        if (random < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    private void generateRegularCar(Game game) {
        int carProbability = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if (carProbability < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        switch (type) {
            case THORN:
                return new Thorn(x, y);
            case DRUNK_CAR:
                return new MovingCar(x, y);
            default:
                return new Car(type, x, y);
        }
    }

    private void deletePassedItems() {
        List<RoadObject> itemsCopy = new ArrayList<>(items);
        for (RoadObject item : itemsCopy) {
            if (item.y >= RacerGame.HEIGHT) {
                items.remove(item);
                if (item.type != RoadObjectType.THORN) {
                    passedCarsCount++;
                }
            }
        }
        //items.removeIf(item -> item.y >= RacerGame.HEIGHT);
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject newObject = createRoadObject(type, x, y);
        if (newObject != null && isRoadSpaceFree(newObject)) {
            items.add(newObject);
        }
    }

}
