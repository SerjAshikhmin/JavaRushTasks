package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class Boss extends EnemyShip {

    private int frameCount = 0;
    public int score = 100;

    public Boss(double x, double y) {
        super(x, y);
        setAnimatedView(true, ShapeMatrix.BOSS_ANIMATION_FIRST, ShapeMatrix.BOSS_ANIMATION_SECOND);
    }

    @Override
    public void nextFrame() {
        frameCount++;
        if (frameCount % 10 == 0 || !isAlive) {
            super.nextFrame();
        }
    }

    @Override
    public Bullet fire() {
        if (isAlive) {
            return matrix == ShapeMatrix.BOSS_ANIMATION_FIRST ?
                    new Bullet(x + 6, y + height, Direction.DOWN) :
                    new Bullet(x, y + height, Direction.DOWN);
        } else {
            return null;
        }
    }

    @Override
    public void kill() {
        if (isAlive) {
            isAlive = false;
            setAnimatedView(false, ShapeMatrix.KILL_BOSS_ANIMATION_FIRST, ShapeMatrix.KILL_BOSS_ANIMATION_SECOND, ShapeMatrix.KILL_BOSS_ANIMATION_THIRD);
        }
    }
}
