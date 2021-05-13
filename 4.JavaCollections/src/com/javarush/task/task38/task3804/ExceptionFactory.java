package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getException(Enum e) {
        if (e != null) {
            if (e instanceof ApplicationExceptionMessage) {
                return new Exception(String.valueOf(e.toString().charAt(0)).toUpperCase() + e.toString().replaceAll("_", " ").toLowerCase().substring(1));
            } else {
                if (e instanceof DatabaseExceptionMessage) {
                    return new RuntimeException(String.valueOf(e.toString().charAt(0)).toUpperCase() + e.toString().replaceAll("_", " ").toLowerCase().substring(1));
                } else {
                    if (e instanceof UserExceptionMessage) {
                        return new Error(String.valueOf(e.toString().charAt(0)).toUpperCase() + e.toString().replaceAll("_", " ").toLowerCase().substring(1));
                    }
                }
            }
        }
        return new IllegalArgumentException();
    }

}
