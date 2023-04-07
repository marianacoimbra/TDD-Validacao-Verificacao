package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NotaFiscalTest {
    public Bill defaultBill;
    public NotaFiscal defaultNotaFiscal;

    @Parameterized.Parameter
    public ServiceType serviceType;

    @Parameterized.Parameter(1)
    public double billValue;

    @Parameterized.Parameter(2)
    public double expectedTax;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Executar testes para cada tipo de serviço com os seus impostos esperados
                { ServiceType.CONSULTING, 1000.0, 250.0 },
                { ServiceType.TRAINING, 1000.0, 150.0 },
                { ServiceType.OTHER, 1000.0, 60.0 }
        });
    }

    @Before
    public void setup() {
        this.defaultBill = new Bill("Davi Sousa", "Rua dos Bobos, 0", serviceType, billValue);
        this.defaultNotaFiscal = new NotaFiscal(defaultBill);
    }

    @Test
    public void shouldCreateNotaFiscal() {
        new NotaFiscal(defaultBill);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrownExceptionOnNullBill() {
        new NotaFiscal(null);
    }

    @Test
    public void shouldGetClientName() {
        assertEquals("Davi Sousa", defaultNotaFiscal.getClientName());
    }

    @Test
    public void shouldGetBillValue() {
        assertEquals(billValue, defaultNotaFiscal.getBillValue(), 0);
    }

    @Test
    public void shouldGetTaxValue() {
        assertEquals(expectedTax, defaultNotaFiscal.getTaxValue(), 0.0001);
    }

    @Test
    public void shouldHasToString() {
        DecimalFormat decimalFormat = new DecimalFormat("R$0.00");
        String formattedBillValue = decimalFormat.format(billValue);
        String formattedTaxValue = decimalFormat.format(expectedTax);

        String expectedString = "Nome do cliente: Davi Sousa\n" +
                "Valor da fatura: " + formattedBillValue + "\n" +
                "Valor do imposto: " + formattedTaxValue;

        assertEquals(expectedString, defaultNotaFiscal.toString());
    }

    @Test
    public void testToStringWithZeroValue() {
        Bill bill = new Bill("João Alves", "Rua dos Bobos, 0", serviceType, 0);
        NotaFiscal notaFiscal = new NotaFiscal(bill);

        String expectedString = "Nome do cliente: João Alves\n" +
                "Valor da fatura: R$0.00\n" +
                "Valor do imposto: R$0.00";

        assertEquals(expectedString, notaFiscal.toString());
    }
}
