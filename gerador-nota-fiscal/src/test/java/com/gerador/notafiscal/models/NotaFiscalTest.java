package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NotaFiscalTest {
    public Bill defaultBill;
    public NotaFiscal defaultNotaFiscal;

    @Parameterized.Parameter
    public ServiceType serviceType;

    @Parameterized.Parameter(1)
    public double initialValue;

    @Parameterized.Parameter(2)
    public double expectedTax;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Run tests for each service type and its expected taxes
                { ServiceType.CONSULTING, 1000.0, 250.0 },
                { ServiceType.TRAINING, 1000.0, 150.0 },
                { ServiceType.OTHER, 1000.0, 60.0 }
        });
    }

    @Before
    public void setup() {
        this.defaultBill = new Bill("Davi Sousa", "Rua dos Bobos, 0", ServiceType.CONSULTING, 245.80);
        this.defaultNotaFiscal = new NotaFiscal(defaultBill);
    }

    @Test
    public void shouldCreateNotaFiscal() {
        Bill bill = new Bill("João Alves", "Rua dos Bobos, 0", serviceType, initialValue);
        new NotaFiscal(bill);
    }
}
