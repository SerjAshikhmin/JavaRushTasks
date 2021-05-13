package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код
        int fightResult = 0;

        fightResult = Integer.compare(this.age, anotherCat.age) + Integer.compare(this.strength, anotherCat.strength) +
                Integer.compare(this.weight, anotherCat.weight);

        return fightResult > 0;
    }

    public static void main(String[] args) {

    }
}
