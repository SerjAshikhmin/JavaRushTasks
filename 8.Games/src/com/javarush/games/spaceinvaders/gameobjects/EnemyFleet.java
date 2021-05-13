package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {

    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    public void draw(Game game) {
        ships.forEach(ship -> ship.draw(game));
    }

    public void move() {
        if (!ships.isEmpty()) {
            boolean isDirectionChanged = false;
            if (direction == Direction.LEFT && getLeftBorder() < 0) {
                direction = Direction.RIGHT;
                isDirectionChanged = true;
            }
            if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
                direction = Direction.LEFT;
                isDirectionChanged = true;
            }
            if (isDirectionChanged) {
                ships.forEach(ship -> ship.move(Direction.DOWN, getSpeed()));
            } else {
                ships.forEach(ship -> ship.move(direction, getSpeed()));
            }
        }
    }

    public Bullet fire(Game game) {
        if (!ships.isEmpty()) {
            int fireProbability = game.getRandomNumber(100/SpaceInvadersGame.COMPLEXITY);
            if (fireProbability > 0) {
                return null;
            } else {
                int shipWillFire = game.getRandomNumber(ships.size());
                return ships.get(shipWillFire).fire();
            }
        } else {
            return null;
        }
    }

    public int verifyHit(List<Bullet> bullets) {
        int scoreSumm = 0;
        if (bullets.isEmpty()) {
            return scoreSumm;
        }
        for (EnemyShip ship : ships) {
            for (Bullet bullet : bullets) {
                if (ship.isCollision(bullet) && bullet.isAlive && ship.isAlive) {
                    bullet.kill();
                    ship.kill();
                    scoreSumm += ship.score;
                }
            }
        }
        return scoreSumm;
    }

    public void deleteHiddenShips() {
        ships.removeIf(ship -> !ship.isVisible());
    }

    public double getBottomBorder() {
        return ships.stream()
                .max((o1, o2) -> {
                    if (o1.y + o1.height > o2.y + o2.height) {
                        return 1;
                    } else {
                        if (o1.y + o1.height == o2.y + o2.height) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                })
                .map(ship -> ship.y + ship.height).orElse(0.0);
    }

    public int getShipsCount() {
        return ships.size();
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLUMNS_COUNT; j++) {
                ships.add(new EnemyShip(j * STEP, i * STEP + 12));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }

    private double getLeftBorder() {
        return ships.stream().map(ship -> ship.x).min(Double::compareTo).get();
    }

    private double getRightBorder() {
        return ships.stream().map(ship -> (ship.x + ship.width)).max(Double::compareTo).get();
    }

}
