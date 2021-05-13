package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }
    
    public static void createPerson() {
        
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        switch (args[0]) {
            // create
            case ("-c"): {
                for (int i = 0; i < args.length / 3; i++) {
                    Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i * 3 + 3]);
                    if (args[i * 3 + 2].equals("м")) allPeople.add(Person.createMale(args[i * 3 + 1], date));
                    else allPeople.add(Person.createFemale(args[i * 3 + 1], date));
                    System.out.println(allPeople.size() - 1);
                }
                break;
            }
            // update
            case ("-u"): {
                for (int i = 0; i < args.length / 4; i++) {
                    Person person = allPeople.get(Integer.parseInt(args[i * 4 + 1]));

                    person.setName(args[i * 4 + 2]);
                    if (args[i * 4 + 3].equals("м")) person.setSex(Sex.MALE);
                    else person.setSex(Sex.FEMALE);

                    Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i * 4 + 4]);
                    person.setBirthDate(date);

                    allPeople.set(Integer.parseInt(args[i * 4 + 1]), person);
                }
                break;
            }
            // delete
            case ("-d"): {
                for (int i = 0; i < args.length - 1; i++) {
                    Person person = allPeople.get(Integer.parseInt(args[i + 1]));
                    person.setSex(null);
                    person.setName(null);
                    person.setBirthDate(null);
                    allPeople.set(Integer.parseInt(args[i + 1]), person);
                }
                break;
            }
            // info
            case ("-i"): {
                for (int i = 0; i < args.length - 1; i++) {
                    Person person = allPeople.get(Integer.parseInt(args[i + 1]));

                    String stringDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDate());
                    String sex = " ж";
                    if (person.getSex() == Sex.MALE) sex = " м";

                    System.out.println(person.getName() + sex + " " + stringDate);
                }
                break;
            }
        }
    }
}
