package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 10;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private FinishLine finishLine;
    private ProgressBar progressBar;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {
            finishLine.show();
        }
        if (finishLine.isCrossed(player)) {
            win();
        } else {
            if (roadManager.checkCrush(player)) {
                gameOver();
            } else {
                moveAll();
                roadManager.generateNewRoadObjects(this);
            }
        }
        score -= 5;
        setScore(score);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case UP:
                player.speed = 2;
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case LEFT:
                if (player.getDirection() == Direction.LEFT) {
                    player.setDirection(Direction.NONE);
                }
                break;
            case RIGHT:
                if (player.getDirection() == Direction.RIGHT) {
                    player.setDirection(Direction.NONE);
                }
                break;
            case UP:
                player.speed = 1;
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Жив, цел, орёл!", Color.GREEN, 16);
        stopTurnTimer();
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
        player.move();
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        isGameStopped = false;
        score = 3500;
        setScore(score);
        setTurnTimer(40);
        drawScene();
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Опять разбился", Color.BLACK, 16);
        stopTurnTimer();
        player.stop();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i == CENTER_X) {
                    setCellColor(i, j, Color.WHITESMOKE);
                } else {
                    if (i >= ROADSIDE_WIDTH && i < WIDTH - ROADSIDE_WIDTH) {
                        setCellColor(i, j, Color.DIMGREY);
                    } else {
                        setCellColor(i, j, Color.GREEN);
                    }
                }
            }
        }
    }
}
