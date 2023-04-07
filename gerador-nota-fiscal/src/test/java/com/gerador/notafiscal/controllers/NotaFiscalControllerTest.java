package com.gerador.notafiscal.controllers;

import com.gerador.notafiscal.models.NotaFiscal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NotaFiscalControllerTest {
    public NotaFiscalController notaFiscalController;

    @Parameterized.Parameter
    public String serviceTypeDescription;

    @Parameterized.Parameter(1)
    public double billValue;

    @Parameterized.Parameter(2)
    public double expectedTax;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Executar testes para cada tipo de serviço com os seus impostos esperados
                { "Consultoria", 1000.0, 250.0 },
                { "Treinamento", 1000.0, 150.0 },
                { "Outro", 1000.0, 60.0 },
                { "Consultoria", 0, 0 },
                { "Treinamento", 0, 0 },
                { "Outro", 0, 0 },
        });
    }

    @Before
    public void setup() {
        this.notaFiscalController = new NotaFiscalController();
    }

    @Test
    public void shouldCreateNotaFiscalController() {
        this.notaFiscalController = new NotaFiscalController();
    }

    @Test
    public void testGenerate() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", serviceTypeDescription, billValue);

        assertEquals(expectedTax, notaFiscal.getTaxValue(), 0.0001);
        assertEquals("Davi Sousa", notaFiscal.getClientName());
        assertEquals(billValue, notaFiscal.getBillValue(), 0);
    }
}
