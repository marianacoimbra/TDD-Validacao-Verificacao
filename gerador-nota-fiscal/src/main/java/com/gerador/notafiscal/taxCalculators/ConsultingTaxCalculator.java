package com.gerador.notafiscal.taxCalculators;

public class ConsultingTaxCalculator implements TaxCalculator {
    @Override
    public double calculate(double value) {
        return value * 0.25;
    }
}
