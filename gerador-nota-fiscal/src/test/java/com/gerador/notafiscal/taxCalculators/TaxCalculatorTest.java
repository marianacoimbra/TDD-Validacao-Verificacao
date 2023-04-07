package com.gerador.notafiscal.taxCalculators;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TaxCalculatorTest {

    static private final double maxDelta = 0.0001;

    @Parameter
    public TaxCalculator calculator;

    @Parameter(1)
    public double initialValue;

    @Parameter(2)
    public double expectedTax;

    @Parameters
    public static Collection<Object[]> calculators() {
        return Arrays.asList(new Object[][] {
                { new ConsultingTaxCalculator(), 1000.0, 250.0 },
                { new TrainingTaxCalculator(), 1000.0, 150.0 },
                { new DefaultTaxCalculator(), 1000.0, 60.0 }
        });
    }

    @Test
    public void shouldCalculateTax() {
        double tax = calculator.calculate(initialValue);
        assertEquals(expectedTax, tax, maxDelta);
    }

    @Test
    public void shouldCalculateTaxWithZeroValue() {
        double tax = calculator.calculate(0.0);
        assertEquals(0.0, tax, maxDelta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWithNegativeValue() {
        calculator.calculate(-initialValue);
    }
}
