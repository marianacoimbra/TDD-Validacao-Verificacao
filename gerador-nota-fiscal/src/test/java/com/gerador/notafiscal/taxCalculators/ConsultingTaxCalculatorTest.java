package com.gerador.notafiscal.taxCalculators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConsultingTaxCalculatorTest {

    static public double maxDelta = 0.0001;

    @Test
    public void shouldCalculateTax() {
        ConsultingTaxCalculator consultingTaxCalculator = new ConsultingTaxCalculator();
        double tax = consultingTaxCalculator.calculate(1000.0);
        assertEquals(250.0, tax, maxDelta);
    }
}
