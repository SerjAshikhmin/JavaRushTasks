package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    static {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void main(String[] args) {

    }

    public static Planet thePlanet;

    //add static block here - добавьте статический блок тут

    public static void readKeyFromConsoleAndInitPlanet() {
        // implement step #5 here - реализуйте задание №5 тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String planetName = "";
        try {
            planetName = reader.readLine();
        } catch (IOException e) {e.printStackTrace();}
        if (planetName.equals(Planet.EARTH)) thePlanet = Earth.getInstance();
        else
            if (planetName.equals(Planet.SUN)) thePlanet = Sun.getInstance();
            else
                if (planetName.equals(Planet.MOON)) thePlanet = Moon.getInstance();
                else thePlanet = null;
    }
}
