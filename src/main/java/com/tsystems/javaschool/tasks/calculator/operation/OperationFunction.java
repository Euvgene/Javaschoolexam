package com.tsystems.javaschool.tasks.calculator.operation;

@FunctionalInterface
public interface OperationFunction<T> {

    T perform(T t1, T t2);
}
