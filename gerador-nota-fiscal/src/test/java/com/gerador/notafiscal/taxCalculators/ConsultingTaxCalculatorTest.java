package com.gerador.notafiscal.taxCalculators;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

public class ConsultingTaxCalculatorTest {

    static public double maxDelta = 0.0001;

    @Rule
    public NegativeValueToTaxCalculatorException thrown = NegativeValueToTaxCalculatorException.none();

    @Test
    public void shouldCalculateTax() {
        ConsultingTaxCalculator consultingTaxCalculator = new ConsultingTaxCalculator();
        double tax = consultingTaxCalculator.calculate(1000.0);
        assertEquals(250.0, tax, maxDelta);
    }

    @Test
    public void shouldCalculateTaxWithZeroValue() {
        ConsultingTaxCalculator consultingTaxCalculator = new ConsultingTaxCalculator();
        double tax = consultingTaxCalculator.calculate(0.0);
        assertEquals(0.0, tax, maxDelta);
    }

    @Test
    public void shouldThrowErrorWithNegativeValue() {
        ConsultingTaxCalculator consultingTaxCalculator = new ConsultingTaxCalculator();

        thrown.expect(NegativeValueToTaxCalculatorException.class);
        thrown.expectMessage("Valor para o calculo de imposto deve ser maior que zero");

        consultingTaxCalculator.calculate(-1000.0);
    }
}
