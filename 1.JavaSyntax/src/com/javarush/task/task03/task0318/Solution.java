package com.javarush.task.task03.task0318;

/* 
План по захвату мира
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        int years = Integer.parseInt(bufferedReader.readLine());
        String name = bufferedReader.readLine();

        System.out.println(name + " захватит мир через " + years + " лет. Му-ха-ха!");

    }
}
