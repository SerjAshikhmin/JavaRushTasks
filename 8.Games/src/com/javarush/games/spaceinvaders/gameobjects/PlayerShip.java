package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.List;

public class PlayerShip extends Ship {

    private Direction direction = Direction.UP;

    public PlayerShip() {
        super(SpaceInvadersGame.WIDTH / 2.0, SpaceInvadersGame.HEIGHT - ShapeMatrix.PLAYER.length - 1);
        setStaticView(ShapeMatrix.PLAYER);
    }

    public void setDirection(Direction direction) {
        if (direction != Direction.DOWN) {
            this.direction = direction;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void kill() {
        if (isAlive) {
            isAlive = false;
            setAnimatedView(false, ShapeMatrix.KILL_PLAYER_ANIMATION_FIRST, ShapeMatrix.KILL_PLAYER_ANIMATION_SECOND, ShapeMatrix.KILL_PLAYER_ANIMATION_THIRD, ShapeMatrix.DEAD_PLAYER);
        }
    }

    @Override
    public Bullet fire() {
        if (isAlive) {
            return new Bullet(x + 2, y - ShapeMatrix.BULLET.length, Direction.UP);
        } else {
            return null;
        }
    }

    public void win() {
        setStaticView(ShapeMatrix.WIN_PLAYER);
    }

    public void move() {
        if (isAlive) {
            switch (direction) {
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            x = x < 0 ? 0 : x;
            x = x + width > SpaceInvadersGame.WIDTH ? SpaceInvadersGame.WIDTH - width : x;
        }
    }

    public void verifyHit(List<Bullet> bullets) {
        if (isAlive) {
            for (Bullet bullet : bullets) {
                if (bullet.isAlive && isCollision(bullet)) {
                    kill();
                    bullet.kill();
                }
            }
        }
    }

}
