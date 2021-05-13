package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        Hen hen1 = HenFactory.getHen(Country.RUSSIA);
        Hen hen2 = HenFactory.getHen(Country.MOLDOVA);
        Hen hen3 = HenFactory.getHen(Country.UKRAINE);
        System.out.println(hen.getDescription());
        hen.getCountOfEggsPerMonth();
    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen = null;
            //напишите тут ваш код

            if (country.equals(Country.BELARUS)) return new BelarusianHen();
            if (country.equals(Country.RUSSIA)) return new RussianHen();
            if (country.equals(Country.MOLDOVA)) return new MoldovanHen();
            if (country.equals(Country.UKRAINE)) return new UkrainianHen(); else return hen;


        }
    }

}
