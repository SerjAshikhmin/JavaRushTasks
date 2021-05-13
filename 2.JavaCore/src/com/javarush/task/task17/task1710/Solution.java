package com.javarush.task.task17.task1710;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        if (args[0].equals("-c")) { // create
            Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
            if (args[2].equals("м")) allPeople.add(Person.createMale(args[1], date));
            else allPeople.add(Person.createFemale(args[1], date));
            System.out.println(allPeople.size()-1);
        } else // update
            if (args[0].equals("-u")) {
                Person person = allPeople.get(Integer.parseInt(args[1]));

                person.setName(args[2]);
                if (args[3].equals("м")) person.setSex(Sex.MALE);
                else person.setSex(Sex.FEMALE);

                Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
                person.setBirthDate(date);

                allPeople.set(Integer.parseInt(args[1]), person);
            } else // delete
                if (args[0].equals("-d")) {
                    Person person = allPeople.get(Integer.parseInt(args[1]));
                    person.setSex(null);
                    person.setName(null);
                    person.setBirthDate(null);
                    allPeople.set(Integer.parseInt(args[1]), person);
                } else // info
                    if (args[0].equals("-i")) {
                        Person person = allPeople.get(Integer.parseInt(args[1]));

                        String stringDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDate());
                        String sex = " ж";
                        if (person.getSex() == Sex.MALE) sex = " м";

                        System.out.println(person.getName() + sex + " " + stringDate);
                    }
    }
}
