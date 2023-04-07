package com.gerador.notafiscal.services;

import com.gerador.notafiscal.models.Bill;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SAPTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public NotaFiscal notaFiscal;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void setup() {
        Bill bill = new Bill("Davi Sousa", "Rua dos Bobos, 0", ServiceType.CONSULTING, 1000.0);
        this.notaFiscal = new NotaFiscal(bill);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldHaveNoErrorsOnSend() {
        SAP sap = new SAP();
        sap.send(notaFiscal);

        assertEquals("Enviando nota fiscal do cliente Davi Sousa para o SAP...\n", outContent.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullNotaFiscal() {
        SAP sap = new SAP();
        sap.send(null);
    }
}
