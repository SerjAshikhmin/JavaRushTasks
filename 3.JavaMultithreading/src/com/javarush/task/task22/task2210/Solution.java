package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getTokens("Good news everyone!", "ne"));
    }
    public static String [] getTokens(String query, String delimiter) {
        String[] result;
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        result = new String[list.size()];
        result =  list.toArray(result);
        return result;
    }
}
