package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.*;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        File file = new File("C:/wrongfile.txt");
        FileReader fileReader = new FileReader(file);
        fileReader.read();
    }

    public static void main(String[] args) {

    }
}
