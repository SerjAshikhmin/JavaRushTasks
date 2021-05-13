package com.javarush.task.task36.task3606;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
        List<File> listFiles = Arrays.asList(new File(packageName).listFiles());
        listFiles.forEach(file -> {
            if (file.isFile() && (file.getName().endsWith(".class"))) {
                try {
                    Class clazz = classLoader.loadClass(file.toPath());
                    hiddenClasses.add(clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class hiddenClass : hiddenClasses) {
            if (hiddenClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase()) && HiddenClass.class.isAssignableFrom(hiddenClass)) {
                try {
                    Constructor[] ctors = hiddenClass.getDeclaredConstructors();
                    Constructor ctor = null;
                    for (int i = 0; i < ctors.length; i++) {
                        ctor = ctors[i];
                        if (ctor.getGenericParameterTypes().length == 0) {
                            hiddenClasses.add(hiddenClass);
                            break;
                        }
                    }
                    ctor.setAccessible(true);
                    return (HiddenClass) ctor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                }
            }
        }
        return null;
    }

}

