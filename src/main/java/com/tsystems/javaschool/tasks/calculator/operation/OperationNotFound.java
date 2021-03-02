package com.tsystems.javaschool.tasks.calculator.operation;


import com.tsystems.javaschool.tasks.calculator.exceptions.CalculatorException;

public class OperationNotFound extends CalculatorException {

    public OperationNotFound(char symbol) {
        this("Operation not found for: " + symbol);
    }

    private OperationNotFound(String message) {
        super(message);
    }
}
