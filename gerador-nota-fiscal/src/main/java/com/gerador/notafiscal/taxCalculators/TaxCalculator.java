package com.gerador.notafiscal.taxCalculators;

import java.util.Objects;

public class TaxCalculator {
    private final double taxRate;

    public TaxCalculator(double taxRate) {
        this.taxRate = taxRate;
    }

    public double calculate(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Value to tax must be equal or greater than zero");

        return value * taxRate;
    }
}
