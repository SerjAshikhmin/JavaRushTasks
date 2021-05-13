package com.javarush.task.task20.task2026;

import java.util.HashSet;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int recCount = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                String s = i + "" + j;
                if (a[i][j] == 1) {
                    int topX = i;
                    int topY = j;
                    int downX = topX;
                    int downY = topY;

                    if (i < a.length - 1)
                        for (int k = i + 1; k < a.length; k++) {
                            if (a[k][j] == 0) {
                                downX = k - 1;
                                break;
                            }
                            if (k == a.length - 1) downX = k;
                        }

                    if (j < a.length - 1)
                        for (int k = j + 1; k < a.length; k++) {
                            if (a[i][k] == 0) {
                                downY = k - 1;
                                break;
                            }
                            if (k == a.length - 1) downY = k;
                        }

                    for (int k = topX; k <= downX; k++)
                        for (int c = topY; c <= downY; c++) {
                            a[k][c] = 0;
                        }
                    recCount++;
                }
            }
        }

        return recCount;
    }
}
