package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private boolean isGameStopped;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isGameStopped) {
            openTile(x, y);
        } else {
            restart();
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void markTile(int x, int y) {
        GameObject currentObject = gameField[y][x];
        if (!currentObject.isOpen && countFlags != 0 && !currentObject.isFlag) {
            currentObject.isFlag = true;
            countFlags--;
            setCellColor(x, y, Color.DARKRED);
            setCellValue(x, y, FLAG);
            return;
        } else {
            if (currentObject.isFlag && !currentObject.isOpen) {
                currentObject.isFlag = false;
                countFlags++;
                setCellColor(x, y, Color.LIGHTSLATEGRAY);
                setCellValue(x, y, "");
            }
        }
    }

    private void openTile(int x, int y) {
        GameObject currentObject = gameField[y][x];
        if (!currentObject.isOpen && !currentObject.isFlag && !isGameStopped) {
            setCellColor(x, y, Color.MOCCASIN);
            if (currentObject.isMine) {
                setCellValueEx(x, y, Color.RED, MINE);
                currentObject.isOpen = true;
                countClosedTiles--;
                gameOver();
            } else {
                setCellNumber(x, y, currentObject.countMineNeighbors);
                currentObject.isOpen = true;
                countClosedTiles--;
                score += 5;
                setScore(score);
                if (currentObject.countMineNeighbors == 0) {
                    getNeighbors(currentObject).stream().filter(n -> !n.isOpen).forEach(n -> openTile(n.x, n.y));
                    setCellColor(x, y, Color.LIGHTGRAY);
                    setCellValue(x, y, "");
                }
                if (countClosedTiles == countMinesOnField) {
                    win();
                }
            }
        }
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.LIGHTSLATEGRAY);
                setCellValue(x, y, "");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Усе, взорвался!", Color.BLACK, 16);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Выграл, красава!", Color.BLACK, 16);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        setScore(0);
        countMinesOnField = 0;
        createGame();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                GameObject currentObject = gameField[j][i];
                if (!currentObject.isMine) {
                    List<GameObject> neighbors = getNeighbors(currentObject);
                    int countMines = (int) neighbors.stream().filter(n -> n.isMine).count();
                    currentObject.countMineNeighbors = countMines;
                }
            }
        }
    }
}