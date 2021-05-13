package com.javarush.task.task05.task0508;

/* 
Геттеры и сеттеры для класса Person
*/

public class Person {
    String name;
    char sex;
    int age;

    //напишите тут ваш код

    public static void main(String[] args) {

    }
    public void setName (String n){
        name = n;
    }

    public void setAge (int a){
        age = a;
    }

    public void setSex (char s){
        sex = s;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public char getSex(){
        return sex;
    }

}
