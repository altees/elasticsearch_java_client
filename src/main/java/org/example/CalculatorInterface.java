package org.example;

@FunctionalInterface
public interface CalculatorInterface<T extends Number, T2 extends Number, R extends Number, O> {
    R perform(T t, T2 t2, O o);
}
