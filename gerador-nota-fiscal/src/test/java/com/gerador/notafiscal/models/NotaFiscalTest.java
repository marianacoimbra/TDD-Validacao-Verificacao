package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
}
