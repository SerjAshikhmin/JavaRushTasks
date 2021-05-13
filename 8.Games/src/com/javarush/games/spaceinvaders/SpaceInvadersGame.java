package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = Integer.MAX_VALUE;
    private List<Star> stars;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private EnemyFleet enemyFleet;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }
        setScore(score);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if (isGameStopped) {
                    createGame();
                } else {
                    Bullet playerBullet = playerShip.fire();
                    if (playerBullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                        playerBullets.add(playerBullet);
                    }
                }
                break;
            case LEFT:
                playerShip.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                playerShip.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT) {
            super.setCellValueEx(x, y, cellColor, value);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case LEFT:
                if (playerShip.getDirection() == Direction.LEFT) {
                    playerShip.setDirection(Direction.UP);
                }
                break;
            case RIGHT:
                if (playerShip.getDirection() == Direction.RIGHT) {
                    playerShip.setDirection(Direction.UP);
                }
                break;
        }
    }



    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        score = 0;
        setScore(score);
        setTurnTimer(40);
        drawScene();
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) {
            showMessageDialog(Color.CYAN, "Отбились..", Color.GREEN, 20);
        } else {
            showMessageDialog(Color.CYAN, "Подбили.. паника.. исчез", Color.RED, 20);
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.BLACK, "");
            }
        }
        stars.forEach(star -> star.draw(this));
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        enemyBullets.forEach(bullet -> bullet.draw(this));
        playerBullets.forEach(bullet -> bullet.draw(this));
        playerShip.draw(this);
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        enemyBullets.forEach(Bullet::move);
        playerBullets.forEach(Bullet::move);
        playerShip.move();
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(bullet -> bullet.y >= HEIGHT - 1 || !bullet.isAlive);
        playerBullets.removeIf(bullet -> bullet.y + bullet.height < 0 || !bullet.isAlive);
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        if (enemyFleet.getBottomBorder() >= playerShip.y) {
            playerShip.kill();
        }
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
        removeDeadBullets();
        if (!playerShip.isAlive) {
            stopGameWithDelay();
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(Math.random() * 64, Math.random() * 40));
        }
    }
}
