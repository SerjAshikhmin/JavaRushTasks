package com.javarush.task.task19.task1918;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayDeque<Integer> opens = new ArrayDeque<>();
        ArrayDeque<Integer> closes = new ArrayDeque<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Stack stack = new Stack();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        BufferedReader fReader = new BufferedReader(new FileReader(file));
        String data;
        StringBuilder sb = new StringBuilder("");
        while ((data = fReader.readLine()) != null)
            sb.append(data);
        fReader.close();
        data = sb.toString().replaceAll("\n", " ");

        Pattern pattern1 = Pattern.compile("<" + args[0] + "(.*?)>");
        Pattern pattern2 = Pattern.compile("</" + args[0] + ">");
        Matcher matcher = pattern1.matcher(data);
        while (matcher.find())
            opens.add(matcher.start());
        matcher = pattern2.matcher(data);
        while (matcher.find())
            closes.add(matcher.end());

        for (int closeN : closes) {

            boolean isClosed = false;
            for (int openN: opens) {
                if (openN >= closeN) {
                    int i = (int) stack.pop();
                    map.put(i, closeN);
                    opens.remove(i);
                    isClosed = true;
                    break;
                }
                else {
                    stack.push(openN);
                }
            }
            if (isClosed) continue;
            else {
                int i = (int) stack.pop();
                map.put(i, closeN);
                opens.remove(i);
            }
        }

        for (Map.Entry<Integer, Integer> pair: map.entrySet()) {
            System.out.println(data.substring(pair.getKey(), pair.getValue()));
        }
    }
}
