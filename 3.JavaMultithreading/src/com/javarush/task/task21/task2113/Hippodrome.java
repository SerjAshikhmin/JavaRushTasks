package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException{
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse: horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse: horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        int max = 0;
        Horse winner = null;
        for (Horse horse : horses) {
            if (max < horse.getDistance())
                winner = horse;
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException{
        game = new Hippodrome(new ArrayList<>());
        game.getHorses().add(new Horse("Merin", 3, 0));
        game.getHorses().add(new Horse("Artur", 3, 0));
        game.getHorses().add(new Horse("Eleonora", 3, 0));
        game.run();
        game.printWinner();
        System.out.println(game.horses.get(0).getDistance());
        System.out.println(game.horses.get(0).getName());
        System.out.println(game.horses.get(1).getDistance());
        System.out.println(game.horses.get(1).getName());
        System.out.println(game.horses.get(2).getDistance());
        System.out.println(game.horses.get(2).getName());
    }
}
