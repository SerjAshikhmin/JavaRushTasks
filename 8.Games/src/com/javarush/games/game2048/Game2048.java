package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {

    private static final int SIDE = 6;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score;
    private int maxTileValue = Integer.MAX_VALUE;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
            }
        } else {
            if (!canUserMove()) {
                gameOver();
                return;
            }
            switch (key) {
                case LEFT:
                    moveLeft();
                    drawScene();
                    break;
                case RIGHT:
                    moveRight();
                    drawScene();
                    break;
                case UP:
                    moveUp();
                    drawScene();
                    break;
                case DOWN:
                    moveDown();
                    drawScene();
                    break;
            }
        }
    }

    private int getMaxTileValue() {
        int maxCellValue = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[j][i] > maxCellValue) {
                    maxCellValue = gameField[j][i];
                }
            }
        }
        return maxCellValue;
    }

    private boolean canUserMove() {
        boolean canMove = false;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[j][i] == 0) {
                    canMove = true;
                }
            }
        }
        if (!canMove) {
            int[][] tempField = gameField;
            canMove = canMerged(canMove, tempField);
            rotateClockwise();
            canMove = canMerged(canMove, tempField);
            rotateClockwise();
            canMove = canMerged(canMove, tempField);
            rotateClockwise();
            canMove = canMerged(canMove, tempField);
            rotateClockwise();
            gameField = tempField;
        }
        return canMove;
    }

    private boolean canMerged(boolean canMove, int[][] tempField) {
        for (int i = 0; i < tempField.length; i++) {
            if (mergeRow(gameField[i])) {
                canMove = true;
            }
        }
        return canMove;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Как бы все, но вообще нет", Color.BLACK, 16);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.CYAN, "Геймовер", Color.BLACK, 16);
    }

    private void moveLeft() {
        boolean isChanged = false;
        for (int i = 0; i < gameField.length; i++) {
            if (compressRow(gameField[i])) {
                isChanged = true;
            }
            if (mergeRow(gameField[i])) {
                compressRow(gameField[i]);
                isChanged = true;
            }
        }
        if (isChanged) {
            createNewNumber();
        }
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] result = new int [SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                result[i][j] = gameField[SIDE - j - 1][i];
            }
        }
        gameField = result;
    }

    private void createGame() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = 0;
            }
        }
        createNewNumber();
        createNewNumber();
        score = 0;
        setScore(0);
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.DARKGRAY;
            case 2:
                return Color.LIGHTGREY;
            case 4:
                return Color.KHAKI;
            case 8:
                return Color.ORANGE;
            case 16:
                return Color.DARKORANGE;
            case 32:
                return Color.INDIANRED;
            case 64:
                return Color.RED;
            case 128:
                return Color.LIGHTYELLOW;
            case 256:
                return Color.LIGHTGOLDENRODYELLOW;
            case 512:
                return Color.YELLOW;
            case 1024:
                return Color.DARKKHAKI;
            case 2048:
                return Color.BLACK;
            default:
                return Color.NONE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color color = getColorByValue(value);
        String text = "";
        if (value != 0) {
            text = String.valueOf(value);
        }
        setCellValueEx(x, y, color, text);
    }

    private boolean mergeRow(int[] row) {
        boolean isMerged = false;
        for (int i = 0; i < row.length; i++) {
            if (i < row.length - 1 && row[i] == row[i + 1] && row[i] != 0) {
                row[i] = row[i] * 2;
                row[i + 1] = 0;
                isMerged = true;
                score += row[i];
                setScore(score);
            }
        }
        return isMerged;
    }

    private boolean compressRow(int[] row) {
        boolean isCompressed = false;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0 && i < row.length - 1 && row[i + 1] != 0) {
                int tempI = row[i];
                row[i] = row[i + 1];
                row[i + 1] = tempI;
                isCompressed = true;
            }
        }
        if (isCompressed) {
            compressRow(row);
        }
        return isCompressed;
    }

    private void createNewNumber() {
        if (getMaxTileValue() == maxTileValue) {
            win();
        }
        int i;
        int j;
        do {
            i = getRandomNumber(SIDE);
            j = getRandomNumber(SIDE);
        } while (gameField[j][i] != 0);
        if (getRandomNumber(10) == 9) {
            gameField[j][i] = 4;
        } else {
            gameField[j][i] = 2;
        }
    }

    private void drawScene() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                setCellColoredNumber(i, j, gameField[j][i]);
            }
        }
    }
}
