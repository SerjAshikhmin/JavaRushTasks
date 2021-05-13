package com.javarush.task.task19.task1916;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        BufferedReader fReader = new BufferedReader(new FileReader(file1));
        String data;
        while ((data = fReader.readLine()) != null) {
            list1.add(data);
        }
        fReader.close();
        fReader = new BufferedReader(new FileReader(file2));
        while ((data = fReader.readLine()) != null) {
            list2.add(data);
        }
        fReader.close();

        int minSize;
        if (list1.size() < list2.size()) minSize = list1.size(); else minSize = list2.size();

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).equals(list2.get(i)))  lines.add(new LineItem(Type.SAME, list1.get(i)));
            else
                if (!list1.get(i).equals(list2.get(i + 1))) {
                    lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                    list2.add(i, "");
                    //continue;
                }
                else
                    if (!list1.get(i).equals(list2.get(i))) {
                        lines.add(new LineItem(Type.ADDED, list2.get(i)));
                        list1.add(i, "");
                    }
        }

        if (list1.get(list1.size() - 1).equals(list2.get(list2.size() - 1)))  lines.add(new LineItem(Type.SAME, list1.get(list1.size() - 1)));
        else
            if (list1.size() > list2.size()) {
                lines.add(new LineItem(Type.REMOVED, list1.get(list1.size() - 1)));
            }
            else if (list1.size() < list2.size()) {
                    lines.add(new LineItem(Type.ADDED, list2.get(list2.size() - 1)));
                }

        for (LineItem line: lines) {
            System.out.println(line.type + " " + line.line);
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
