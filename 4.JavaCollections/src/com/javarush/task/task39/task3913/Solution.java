package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        Set<String> set = logParser.getLoggedUsers(null, null);
        Date dateLogin = logParser.getDateWhenUserLoggedFirstTime("Amig", null, null);
        Date dateDoneTask = logParser.getDateWhenUserSolvedTask("Amigo", 17, null, null);
        Map<Integer, Integer> allSolvedTasks = logParser.getAllSolvedTasksAndTheirNumber(null, null);
        Set<Object> objects = logParser.execute("get event");
        Set<Object> objectSet = logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
    }
}