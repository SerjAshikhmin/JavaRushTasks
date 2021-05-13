package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {

    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        MyClassLoader classLoader = new MyClassLoader();
        List<File> listFiles = Arrays.asList(new File(pathToAnimals).listFiles());
        listFiles.forEach(file -> {
            if (file.isFile() && file.getName().endsWith(".class")) {
                try {
                    Class clazz = classLoader.loadClass(file.toPath());
                    if (Animal.class.isAssignableFrom(clazz) && clazz.getConstructor() != null) {
                        set.add((Animal) clazz.getConstructor().newInstance());
                    }
                } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        return set;
    }


}
