package com.example.util;
import com.example.util.Calculator;

public class CalculatorTest {

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println(calc.plus(1,3));
        System.out.println(calc.minus(5,3));
    }
}
