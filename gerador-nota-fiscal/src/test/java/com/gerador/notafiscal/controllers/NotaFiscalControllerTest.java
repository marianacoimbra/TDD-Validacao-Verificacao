package com.gerador.notafiscal.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

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
                { "Outro", 1000.0, 60.0 }
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
}
