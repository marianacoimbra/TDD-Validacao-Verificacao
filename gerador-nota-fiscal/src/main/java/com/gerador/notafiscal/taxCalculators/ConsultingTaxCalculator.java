package com.gerador.notafiscal.taxCalculators;

public class ConsultingTaxCalculator implements TaxCalculator {
    @Override
    public double calculate(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Value to tax must be equal or greater than zero");

        return value * 0.25;
    }
}
