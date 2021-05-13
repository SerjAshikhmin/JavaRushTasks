package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    private GameObject platform;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        if (score > 0) {
            score--;
        }
        setScore(score);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if (isGameStopped) {
                    createGame();
                    break;
                }
            case RIGHT:
                isRightPressed = true;
                isLeftPressed = false;
                break;
            case LEFT:
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case UP:
                isUpPressed = true;
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case RIGHT:
                isRightPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
            case UP:
                isUpPressed = false;
                break;
        }
    }

    private void createGame() {
        createGameObjects();
        setTurnTimer(50);
        isLeftPressed = false;
        isRightPressed = false;
        isUpPressed = false;
        isGameStopped = false;
        score = 1000;
        setScore(score);
        drawScene();
    }

    private void check() {
        if (rocket.isCollision(platform)) {
            win();
        }
        if (rocket.isCollision(landscape) && !(rocket.isCollision(platform) && rocket.isStopped())) {
            gameOver();
        }
    }

    private void win() {
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Глуши на", Color.BLACK, 16);
        stopTurnTimer();
    }

    private void gameOver() {
        rocket.crash();
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Разбился", Color.BLACK, 16);
        stopTurnTimer();
        score = 0;
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2, 0);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 2, ShapeMatrix.PLATFORM);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i, j, Color.BLACK);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
        platform.draw(this);
    }
}
