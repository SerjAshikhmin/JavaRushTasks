package com.javarush.task.task20.task2014;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

/* 
Serializable Solution
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution savedObject = new Solution(15);
        //System.out.println(new Solution(4));

        File your_file_name = File.createTempFile("your_file_name", null);

        FileOutputStream outputStream = new FileOutputStream(your_file_name);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedObject);
        System.out.println(savedObject.string);

        FileInputStream inputStream = new FileInputStream(your_file_name);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Solution loadedObject = new Solution(10);
        loadedObject  = (Solution) objectInputStream.readObject();
        System.out.println(loadedObject.string);

    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
