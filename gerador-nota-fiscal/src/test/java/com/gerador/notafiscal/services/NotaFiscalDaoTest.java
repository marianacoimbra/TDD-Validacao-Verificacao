package com.gerador.notafiscal.services;

import com.gerador.notafiscal.models.Bill;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class NotaFiscalDaoTest {
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
    public void shouldHaveNoErrorsOnSave() {
        NotaFiscalDao notaFiscalDao = new NotaFiscalDao();
        notaFiscalDao.saveToDB(notaFiscal);

        assertEquals("Salvando nota fiscal do cliente Davi Sousa no banco de dados...\n", outContent.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullNotaFiscal() {
        NotaFiscalDao notaFiscalDao = new NotaFiscalDao();
        notaFiscalDao.saveToDB(null);
    }
}
