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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TabelaDeDecisao {

    public Boleto boletoInvalido;
    public Boleto boletoValido;
    public List<Boleto> listaBoletos;
    public Fatura faturaInvalida;
    public Fatura faturaValida;
    public ProcessadorDeBoletos processador;
    public Fatura nomeClienteValido;
    public Fatura nomeClienteInvalido;

    @Before
    public void init() {
        listaBoletos = new ArrayList<>();
        boletoInvalido = new Boleto("111", new Date(02-04-2023), new BigDecimal("-200.00"));
        boletoValido = new Boleto("222", new Date(02-04-2023), new BigDecimal("200.00"));
        processador = new ProcessadorDeBoletos();
        faturaInvalida = new Fatura(LocalDate.now(), new BigDecimal("-500.00"), "Cliente");
        faturaValida = new Fatura(LocalDate.now(), new BigDecimal("500.00"), "Cliente");
        nomeClienteValido = new Fatura(LocalDate.now(), new BigDecimal("200.00"), "Cliente");
        nomeClienteInvalido = new Fatura(LocalDate.now(), new BigDecimal("500.00"), "");
    }

    @Test(expected = BoletosIncorretosException.class)
    public void naoDeveCriarPagamentosCasoDeTeste1() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        processador.processar(listaBoletos,nomeClienteInvalido);
        assertTrue(nomeClienteInvalido.getPagamentos().isEmpty());
    }

    @Test(expected = DadosDoClienteException.class)
    public void naoDeveCriarPagamentosCasoDeTeste2() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        listaBoletos.add(boletoInvalido);
        processador.processar(listaBoletos, nomeClienteInvalido);
        assertTrue(nomeClienteInvalido.getPagamentos().isEmpty());
    }

    @Test(expected = BoletosIncorretosException.class)
    public void naoDeveCriarPagamentosCasoDeTeste3() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        processador.processar(listaBoletos, faturaInvalida);
        assertTrue(faturaInvalida.getPagamentos().isEmpty());
    }

    @Test(expected = FaturaIncorretaException.class)
    public void naoDeveCriarPagamentosCasoDeTeste4() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        listaBoletos.add(boletoValido);
        processador.processar(listaBoletos, faturaInvalida);
        assertTrue(faturaInvalida.getPagamentos().isEmpty());
    }

    @Test(expected = BoletosIncorretosException.class)
    public void naoDeveCriarPagamentosCasoDeTeste5() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        processador.processar(listaBoletos, nomeClienteValido);
        assertTrue(faturaInvalida.getPagamentos().isEmpty());
    }

    @Test(expected = BoletosIncorretosException.class)
    public void naoDeveCriarPagamentosCasoDeTeste6() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
       listaBoletos.add(boletoInvalido);
        processador.processar(listaBoletos, nomeClienteValido);
        assertTrue(faturaInvalida.getPagamentos().isEmpty());
    }

    @Test(expected = BoletosIncorretosException.class)
    public void naoDeveCriarPagamentosCasoDeTeste7() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        processador.processar(listaBoletos, nomeClienteValido);
        assertTrue(faturaInvalida.getPagamentos().isEmpty());
    }

    @Test
    public void naoDeveCriarPagamentosCasoDeTeste8() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        listaBoletos.add(boletoValido);
        processador.processar(listaBoletos, nomeClienteValido);
        assertFalse(nomeClienteValido.getPagamentos().isEmpty());
        assertTrue(nomeClienteValido.isPaga());
    }

    @Test(expected = FaturaNaoPagaException.class)
    public void naoDeveCriarPagamentosCasoDeTeste9() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoValidoMenorQueFatura = new Boleto("0", new Date(02-04-2023), new BigDecimal("10.00"));
        Boleto boletoValidoMenorQueFatura2 = new Boleto("1", new Date(02-04-2023), new BigDecimal("10.00"));

        listaBoletos.add(boletoValidoMenorQueFatura);
        listaBoletos.add(boletoValidoMenorQueFatura2);
        processador.processar(listaBoletos, nomeClienteValido);
        assertFalse(nomeClienteValido.getPagamentos().isEmpty());
        assertFalse(nomeClienteValido.isPaga());
    }

    @Test
    public void naoDeveCriarPagamentosCasoDeTeste10() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoValido2 = new Boleto("0", new Date(02-04-2023), new BigDecimal("300.00"));

        listaBoletos.add(boletoValido);
        listaBoletos.add(boletoValido2);
        processador.processar(listaBoletos, faturaValida);
        assertFalse(faturaValida.getPagamentos().isEmpty());
        assertTrue(faturaValida.isPaga());
    }

    @Test
    public void naoDeveCriarPagamentosCasoDeTeste11() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoValido2 = new Boleto("0", new Date(02-04-2023), new BigDecimal("400.00"));

        listaBoletos.add(boletoValido);
        listaBoletos.add(boletoValido2);
        processador.processar(listaBoletos, faturaValida);
        assertFalse(faturaValida.getPagamentos().isEmpty());
        assertTrue(faturaValida.isPaga());
    }

}
