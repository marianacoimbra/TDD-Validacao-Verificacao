package com.gerador.notafiscal.controllers;

import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.services.NotaFiscalDao;
import com.gerador.notafiscal.services.SAP;
import com.gerador.notafiscal.services.SMTP;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NotaFiscalControllerTest {
    public NotaFiscalController notaFiscalController;
    public NotaFiscalDao notaFiscalDao;
    public SAP sap;
    public SMTP smtp;

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
        });
    }

    @Before
    public void setup() {
        this.notaFiscalDao = mock(NotaFiscalDao.class);
        this.sap = mock(SAP.class);
        this.smtp = mock(SMTP.class);
        this.notaFiscalController = new NotaFiscalController(notaFiscalDao, sap, smtp);
    }

    @Test
    public void shouldCreateNotaFiscalController() {
        NotaFiscalController controller = new NotaFiscalController();
        assertNotNull(controller.notaFiscalDao);
        assertNotNull(controller.sap);
        assertNotNull(controller.smtp);
    }

    @Test
    public void testGenerate() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", serviceTypeDescription, billValue);

        assertEquals(expectedTax, notaFiscal.getTaxValue(), 0.0001);
        assertEquals("Davi Sousa", notaFiscal.getClientName());
        assertEquals(billValue, notaFiscal.getBillValue(), 0);

        verify(notaFiscalDao, times(1)).saveToDB(any());
        verify(sap, times(1)).send(any());
        verify(smtp, times(1)).send(any());
    }

    @Test
    public void testGenerateWithZeroBillValue() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", serviceTypeDescription, 0);

        assertEquals(0, notaFiscal.getTaxValue(), 0);
        assertEquals("Davi Sousa", notaFiscal.getClientName());
        assertEquals(0, notaFiscal.getBillValue(), 0);

        verify(notaFiscalDao, times(1)).saveToDB(any());
        verify(sap, times(1)).send(any());
        verify(smtp, times(1)).send(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullClientName() {
        notaFiscalController.generate(null, "Rua dos Bobos, 0", serviceTypeDescription, billValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullClientAddress() {
        notaFiscalController.generate("Davi Sousa", null, serviceTypeDescription, billValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullServiceType() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", null, billValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNegativeBillValue() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", serviceTypeDescription, -billValue);
    }
}
