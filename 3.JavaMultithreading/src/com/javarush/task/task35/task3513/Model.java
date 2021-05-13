package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();
    private boolean isSaveNeeded = true;
    protected int score;
    protected int maxTile;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        boolean hasEmptyTile = false;
        boolean hasAdjoiningTiles = false;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[i][j].value == 0 || gameTiles[i][j + 1].value == 0) {
                    hasEmptyTile = true;
                    break;
                }
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value) {
                    hasAdjoiningTiles = true;
                    break;
                }
            }
            if (hasAdjoiningTiles || hasEmptyTile) {
                break;
            }
        }

        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (hasAdjoiningTiles || hasEmptyTile) {
                break;
            }
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == gameTiles[i + 1][j].value) {
                    hasAdjoiningTiles = true;
                    break;
                }
            }
        }

        if (hasAdjoiningTiles || hasEmptyTile) {
            return true;
        } else {
            return false;
        }
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
            isSaveNeeded = false;
        }
        boolean isCompressed = false;
        boolean isMerged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i])) isCompressed = true;
            if (mergeTiles(gameTiles[i])) isMerged = true;
        }
        if (isCompressed | isMerged) {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void rollback() {
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }

    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;

        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }
    
    public boolean hasBoardChanged() {
        boolean hasBoardChanged = false;
        Tile[][] prevTiles = (Tile[][]) previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value != prevTiles[i][j].value) {
                    hasBoardChanged = true;
                    break;
                }
            }
            if (hasBoardChanged) break;
        }
        return hasBoardChanged;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        int numberOfEmptyTiles = 0;
        MoveEfficiency moveEfficiency;
        if (!hasBoardChanged()) {
            numberOfEmptyTiles = -1;
            moveEfficiency = new MoveEfficiency(numberOfEmptyTiles, 0, move);
        } else {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    if (gameTiles[i][j].value == 0) {
                        numberOfEmptyTiles++;
                    }
                }
            }
            moveEfficiency = new MoveEfficiency(numberOfEmptyTiles, score, move);
            rollback();
        }

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue queue = new PriorityQueue(4, Collections.reverseOrder());
        MoveEfficiency moveLeft = getMoveEfficiency(this::left);
        MoveEfficiency moveRight = getMoveEfficiency(this::right);
        MoveEfficiency moveUp = getMoveEfficiency(this::up);
        MoveEfficiency moveDown = getMoveEfficiency(this::down);
        queue.add(moveLeft);
        queue.add(moveRight);
        queue.add(moveUp);
        queue.add(moveDown);
        MoveEfficiency bestMove = (MoveEfficiency) queue.poll();
        bestMove.getMove().move();
    }

    protected void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() != 0) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isFieldChanged = false;
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value == 0) {
                for (int j = i + 1; j < FIELD_WIDTH; j++) {
                    if (tiles[j].value != 0) {
                        tiles[i].value ^= tiles[j].value;
                        tiles[j].value ^= tiles[i].value;
                        tiles[i].value ^= tiles[j].value;
                        isFieldChanged = true;
                        break;
                    }
                }
            }
        }
        if (tiles[0].value == 0 && tiles[1].value == 0 && tiles[2].value == 0 && tiles[3].value == 0) isFieldChanged = false;
        return isFieldChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isFieldChanged = false;
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value += tiles[i + 1].value;

                for (int j = i + 1; j < FIELD_WIDTH - 1; j++) {
                    tiles[j].value = tiles[j + 1].value;
                    isFieldChanged = true;
                }
                tiles[FIELD_WIDTH - 1].value = 0;

                score += tiles[i].value;
                if (maxTile < tiles[i].value) {
                    maxTile = tiles[i].value;
                }
            }
        }
        return isFieldChanged;
    }
    
    private void rotate() {
        Tile[][] result = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                result[i][j] = gameTiles[FIELD_WIDTH - j - 1][i];
            }
        }
        gameTiles = result;
    }

    private void saveState(Tile[][] gameTiles) {
        Tile[][] tiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tiles[i][j] = new Tile(gameTiles[i][j].value);
            }
        }
        //tiles = gameTiles.clone();
        previousStates.push(tiles);
        previousScores.push(this.score);
        isSaveNeeded = false;
    }


}
