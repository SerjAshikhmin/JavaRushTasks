package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Cat grandfather = new Cat(reader.readLine());
        Cat grandmother = new Cat(reader.readLine());
        Cat father = new Cat(reader.readLine(), null, grandfather);
        Cat mother = new Cat(reader.readLine(), grandmother, null);
        Cat daut = new Cat(reader.readLine(), mother, father);
        Cat son = new Cat(reader.readLine(), mother, father);

        System.out.println(grandfather);
        System.out.println(grandmother);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(daut);
        System.out.println(son);

    }

    public static class Cat {
        String name;
        Cat mother;
        Cat father;


        public Cat (String n, Cat m, Cat f){
            name = n;
            mother = m;
            father = f;
        }

        public Cat (String n){
            name = n;
        }


        @Override
        public String toString(){
            String mothername, fathername;

            if (father == null) {
                fathername = ", no father";
            }
            else {
                fathername = ", father is " + father;
            }

            if (mother == null) {
                mothername = ", no mother";
            }
            else {
                mothername = ", mother is " + mother;
            }

            return "Cat name is " + name + fathername + mothername;
        }

    }

}
