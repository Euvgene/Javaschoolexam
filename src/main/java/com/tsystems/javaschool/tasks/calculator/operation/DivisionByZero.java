package com.tsystems.javaschool.tasks.calculator.operation;


import com.tsystems.javaschool.tasks.calculator.exceptions.CalculatorException;

public class DivisionByZero extends CalculatorException {

    public DivisionByZero() {
        this("Division by zero");
    }

    private DivisionByZero(String message) {
        super(message);
    }
}
