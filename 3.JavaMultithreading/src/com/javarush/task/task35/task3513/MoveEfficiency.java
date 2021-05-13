package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        MoveEfficiency moveEfficiency = o;
        if (this.numberOfEmptyTiles == moveEfficiency.numberOfEmptyTiles) {
            return Integer.compare(this.score, moveEfficiency.score);
            /*if (this.score == moveEfficiency.score) {
                return 0;
            } else {
                if (this.score > moveEfficiency.score) {
                    return 1;
                } else {
                    return -1;
                }
            }*/
        } else {
            return Integer.compare(this.numberOfEmptyTiles, moveEfficiency.numberOfEmptyTiles);
            /*if (this.numberOfEmptyTiles > moveEfficiency.numberOfEmptyTiles) {
                return 1;
            } else {
                return -1;
            }*/
        }
    }

}
