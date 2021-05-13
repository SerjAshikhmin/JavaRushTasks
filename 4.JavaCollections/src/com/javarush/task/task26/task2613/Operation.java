package com.javarush.task.task26.task2613;

public enum Operation {

    LOGIN(0),
    INFO(1),
    DEPOSIT(2),
    WITHDRAW(3),
    EXIT(4);

    private int value;

    Operation(int i) {
        this.value = i;
    }

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if (i < 1 || i > 4) {
            throw new IllegalArgumentException();
        }
        Operation operation = null;
        switch (i) {
            case 1:
                operation = INFO;
                break;
            case 2:
                operation = DEPOSIT;
                break;
            case 3:
                operation = WITHDRAW;
                break;
            case 4:
                operation = EXIT;
                break;
        }
        return operation;
    }
}
