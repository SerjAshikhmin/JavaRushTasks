package com.javarush.task.task32.task3204;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        byte[] password = new byte[8];
        password[0] = (byte) (Math.random() * 10 + 48);
        password[1] = (byte) (Math.random() * 25 + 97);
        password[2] = (byte) (Math.random() * 25 + 65);
        for (int i = 3; i < 8; i++) {
            int symbolType = (int) (Math.random() * 3);
            //System.out.println(symbolType);
            switch (symbolType) {
                case 0:
                    password[i] = (byte) (Math.random() * 10 + 48);
                    //System.out.println(Math.random() * 10);
                    break;
                case 1:
                    password[i] = (byte) (Math.random() * 25 + 97);
                    //System.out.println(Math.random() * 25);
                    break;
                case 2:
                    password[i] = (byte) (Math.random() * 25 + 65);
                    //System.out.println(Math.random() * 25);
                    break;
            }
        }
        //System.out.println(password);
        ByteArrayInputStream bais = new ByteArrayInputStream(password);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (bais.available() > 0) {
            baos.write(bais.read());
        }

        return baos;
    }
}