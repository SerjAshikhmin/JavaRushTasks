package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};
    public static char[] consonant = new char[]{'б', 'в', 'г', 'д', 'ж', 'з', 'к', 'л', 'м', 'н', 'п', 'р', 'с',
    'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'Ъ', 'ь', '.', ',', '!', '?', '-', ':', ';'};

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        String sVowels = "";
        String sConsonant = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String st = reader.readLine();

        for (int i = 0; i < st.length(); i++){
            char c = st.charAt(i);
            if (isVowel(c)) sVowels = sVowels + c + " ";
            if (isConsonant(c)) sConsonant = sConsonant + c + " ";
        }

        System.out.println(sVowels);
        System.out.println(sConsonant);
    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам
        for (char d : vowels) {  // ищем среди массива гласных
            if (c == d) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConsonant(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам
        for (char d : consonant) {  // ищем среди массива согласных
            if (c == d) {
                return true;
            }
        }
        return false;
    }
}