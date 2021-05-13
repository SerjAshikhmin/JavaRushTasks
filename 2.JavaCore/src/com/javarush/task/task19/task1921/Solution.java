package com.javarush.task.task19.task1921;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String data;
        while ((data = reader.readLine()) != null) {
            String[] dataS = data.split("(?<=\\D)\\s(?=[0-9\\s])");
            Date date = new SimpleDateFormat("dd MM yyyy").parse(dataS[1]);
            PEOPLE.add(new Person(dataS[0], date));
        }
        reader.close();
    }
}
