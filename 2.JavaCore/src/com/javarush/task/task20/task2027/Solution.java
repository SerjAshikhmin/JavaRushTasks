package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        /*int[][] crossword = new int[][]{
                {'f', 'e', 'e', 'e', 'l', 'e'},
                {'u', 's', 'n', 'n', 'n', 'o'},
                {'l', 'e', 'n', 'o', 'n', 'e'},
                {'m', 'm', 'n', 'n', 'n', 'h'},
                {'p', 'e', 'e', 'e', 'j', 'e'},
        };*/
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        System.out.println(detectAllWords(crossword, "home", "same"));
        /*List<Word> words = (detectAllWords(crossword, "one"));
        for (Word word:words) {
            System.out.println(word);
        }*/
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        int[] dx = {1, 0, -1, 0, 1, -1, -1, 1};
        int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};
        for (String word : words) {
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (crossword[i][j] == word.charAt(0)) {
                        for (int k = 0; k < dx.length; k++) {
                            boolean isFound = true;
                            for (int l = 0; l < word.length(); l++) {
                                if (i + dx[k] * l >= crossword.length || j + dy[k] * l >= crossword[i].length || i + dx[k] * l < 0 || j + dy[k] * l < 0
                                        || crossword[i + dx[k] * l][j + dy[k] * l] != word.charAt(l)) {
                                    isFound = false;
                                    break;
                                }
                            }
                            if (isFound) {
                                Word founded = new Word(word);
                                founded.setStartPoint(j, i);
                                founded.setEndPoint(j + dy[k] * (word.length() - 1), i + dx[k] * (word.length() - 1));
                                result.add(founded);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
