package com.test.functionalTests;
import com.main.Exceptions.BoletosIncorretosException;
import com.main.Exceptions.DadosDoClienteException;
import com.main.Exceptions.FaturaIncorretaException;
import com.main.Exceptions.FaturaNaoPagaException;
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


public class Avl {

    public Boleto boleto1;
    public Boleto boleto2;
    public Boleto boleto3;
    public List<Boleto> listaBoletos;
    public Fatura fatura;
    public ProcessadorDeBoletos processador;

    @Before
    public void init() {
        listaBoletos = new ArrayList<>();
        boleto1 = new Boleto("111", new Date(02-04-2023), new BigDecimal("200.00"));
        boleto2 = new Boleto("333", new Date(01-04-2023), new BigDecimal("300.00"));
        processador = new ProcessadorDeBoletos();
        fatura = new Fatura(LocalDate.now(), new BigDecimal("500.00"), "Cliente");
    }

    @Test
    public void deveSerPagaComsucessoFaturaZerada() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Fatura faturaZerada = new Fatura(LocalDate.now(), new BigDecimal("0.00"), "Cliente");
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);

        processador.processar(listaBoletos, faturaZerada);
        assertTrue(faturaZerada.isPaga());
    }

    @Test
    public void devePagarFaturaComsucesso() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);

        processador.processar(listaBoletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test(expected = FaturaNaoPagaException.class)
    public void deveLancarErroValorDeBoletosIncorretosIgualAZero() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoZerado = new Boleto("0000", new Date(07-05-2023), new BigDecimal("00.00"));
        listaBoletos.add(boletoZerado);

        processador.processar(listaBoletos, fatura);
    }

    @Test(expected = FaturaNaoPagaException.class)
    public void deveLancarErroValorDeFaturaNaoPagaValorInferiorAoValorDaFatura() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        listaBoletos.add(boleto1);

        processador.processar(listaBoletos, fatura);
        assertFalse(fatura.isPaga());
    }
}
