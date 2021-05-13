package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();

            InputStream inputStream = new FileInputStream(fileName);

            load(inputStream);

            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }


    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        Properties propertiesToFile = new Properties();

        for (Map.Entry<String, String> property : properties.entrySet()) {
            propertiesToFile.put(property.getKey(), property.getValue());
        }
        propertiesToFile.store(outputStream, "");
        writer.flush();
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Properties propertiesFromFile = new Properties();
        propertiesFromFile.load(reader);
        for (Map.Entry<Object, Object> property : propertiesFromFile.entrySet()) {
            properties.put(property.getKey().toString(), property.getValue().toString());
        }
    }

    public static void main(String[] args) {

    }
}
