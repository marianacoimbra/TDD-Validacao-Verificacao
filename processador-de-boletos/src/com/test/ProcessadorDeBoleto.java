package com.test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProcessadorDeBoleto {
    @Test
    public void deveMarcarFaturaComoPaga() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("111", new Date(02-04-2023), new BigDecimal("500.00")));
        boletos.add(new Boleto("222", new Date(04-04-2023), new BigDecimal("400.00")));
        boletos.add(new Boleto("333", new Date(01-04-2023), new BigDecimal("600.00")));

        Fatura fatura = new Fatura(LocalDate.now(), new BigDecimal("1500.00"), "Cliente");

        ProcessadorDeBoletos processador = new ProcessadorDeBoletos();
        processador.processar(boletos, fatura);

        assertTrue(fatura.isPaga());
    }

    @Test
    public void deveMarcarFaturaComoNaoPaga() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("111", new Date(02-04-2023), new BigDecimal("500.00")));
        boletos.add(new Boleto("222", new Date(04-04-2023), new BigDecimal("400.00")));

        Fatura fatura = new Fatura(LocalDate.now(), new BigDecimal("1500.00"), "Cliente");

        ProcessadorDeBoletos processador = new ProcessadorDeBoletos();
        processador.processar(boletos, fatura);

        assertFalse(fatura.isPaga());
    }
}
