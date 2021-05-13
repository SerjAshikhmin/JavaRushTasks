package com.javarush.task.task22.task2201;

public class StringForFirstThreadTooShortException extends RuntimeException {
    @Override
    public synchronized Throwable getCause() {
        return new StringIndexOutOfBoundsException("String index out of range: -1");
    }

    /*public StringForFirstThreadTooShortException(Throwable cause) {
        initCause(cause);
    }*/
}
