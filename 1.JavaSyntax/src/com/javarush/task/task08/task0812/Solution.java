package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }

        int lMax = 1;
        for (int i = 0; i < 9; i++){
            if (list.get(i).equals(list.get(i + 1))) {
                int lCurrentMax = 2;
                int j = i + 1;
                while (true) {
                    j++;
                    if (j >= 10) break;
                    if (list.get(i).equals(list.get(j))) {
                        lCurrentMax++;
                    }
                    else {
                        i = j - 1;
                        break;
                    }
                }
                if (lCurrentMax > lMax) lMax = lCurrentMax;
            }
        }
        System.out.println(lMax);

    }
}