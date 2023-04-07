package com.gerador.notafiscal.taxCalculators;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class TaxCalculatorTest {

    static private double maxDelta = 0.0001;

    @Parameters
    public static Collection<Object[]> calculators() {
        return Arrays.asList(new Object[][] {
                { new ConsultingTaxCalculator(), 0.25 },
        });
    }

    @Parameter
    public TaxCalculator calculator;

    @Parameter(1)
    public double expectedTaxRate;

    @Test
    public void shouldCalculateTax() {
        double initialValue = 1000.0;

        double tax = calculator.calculate(initialValue);

        double expectedTax = initialValue * expectedTaxRate;
        assertEquals(expectedTax, tax, maxDelta);
    }

    @Test
    public void shouldCalculateTaxWithZeroValue() {
        double tax = calculator.calculate(0.0);
        assertEquals(0.0, tax, maxDelta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWithNegativeValue() {
        calculator.calculate(-1000.0);
    }
}
