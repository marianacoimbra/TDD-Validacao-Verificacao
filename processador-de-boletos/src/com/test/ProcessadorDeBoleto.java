package com.test;

import com.main.br.processador.ProcessadorDeBoletos;
import com.main.br.processador.boleto.Boleto;
import com.main.br.processador.fatura.Fatura;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessadorDeBoleto {
    public Boleto boleto1;
    public Boleto boleto2;
    public Boleto boleto3;
    public List<Boleto> listaBoletos;
    public Fatura fatura;
    public ProcessadorDeBoletos processador;

    @Before
    public void init() {
        listaBoletos = new ArrayList<>();
        boleto1 = new Boleto("111", new Date(02-04-2023), new BigDecimal("500.00"));
        boleto2 = new Boleto("333", new Date(01-04-2023), new BigDecimal("600.00"));
        boleto3 = new Boleto("222", new Date(04-04-2023), new BigDecimal("400.00"));
        processador = new ProcessadorDeBoletos();
        fatura = new Fatura(LocalDate.now(), new BigDecimal("1500.00"), "Cliente");
    }

    @Test
    public void deveMarcarFaturaComoPaga() {
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);
        listaBoletos.add(boleto3);
        processador.processar(listaBoletos, fatura);
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

    @Test(expected = IllegalArgumentException.class)
    public void processarComListaVaziaDeveLancarExcecao() {
        // given
        List<Boleto> boletosVazios = new ArrayList<>();
        Fatura fatura = new Fatura(LocalDate.now(), new BigDecimal("1500.00"), "Cliente");

        // when
        processador.processar(boletosVazios, fatura);

        // then expect an IllegalArgumentException to be thrown
    }

}
