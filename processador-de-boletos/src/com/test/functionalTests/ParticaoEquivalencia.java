package com.test.functionalTests;

import com.main.Exceptions.BoletosIncorretosException;
import com.main.Exceptions.DadosDoClienteException;
import com.main.Exceptions.FaturaIncorretaException;
import com.main.Exceptions.FaturaNaoPagaException;
import com.main.br.processador.ProcessadorDeBoletos;
import com.main.br.processador.boleto.Boleto;
import com.main.br.processador.fatura.Fatura;
import org.junit.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ParticaoEquivalencia {

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
        boleto3 = new Boleto("444", new Date(01-04-2023), new BigDecimal("200.00"));
        processador = new ProcessadorDeBoletos();
        fatura = new Fatura(LocalDate.now(), new BigDecimal("500.00"), "Cliente");
    }


    @Test(expected = FaturaIncorretaException.class)
    public void deveLancarExceptionFaturaComValorNegativo() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Fatura faturaNegativa = new Fatura(LocalDate.now(), new BigDecimal("-100.00"), "Cliente");
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);

        processador.processar(listaBoletos, faturaNegativa);
        assertFalse(faturaNegativa.isPaga());
    }

    @Test
    public void deveDarComoPagaFaturaZerada() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Fatura faturaZerada = new Fatura(LocalDate.now(), new BigDecimal("0.00"), "Cliente");
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);

        processador.processar(listaBoletos, faturaZerada);
        assertTrue(faturaZerada.isPaga());
    }

    @Test(expected = BoletosIncorretosException.class)
    public void deveLancarExcecaoBoletoComValorInvalido() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoNegativo = new Boleto("000", new Date(02-04-2023), new BigDecimal("-200.00"));
        listaBoletos.add(boletoNegativo);
        processador.processar(listaBoletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test(expected = FaturaNaoPagaException.class)
    public void deveLancarFaturaNaoPagaCasoBoletoZeradoSejaUnicoPassado() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoZerado = new Boleto("000", new Date(02-04-2023), new BigDecimal("00.00"));
        listaBoletos.add(boletoZerado);
        processador.processar(listaBoletos, fatura);
    }

    @Test
    public void faturaDeveSerPagaComBoletoZeradoEOutrosValidos() throws FaturaIncorretaException, BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException {
        Boleto boletoZerado = new Boleto("000", new Date(02-04-2023), new BigDecimal("00.00"));
        listaBoletos.add(boletoZerado);
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);

        processador.processar(listaBoletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test
    public void deveSerPagaComSucessoBoletoEFaturaComValorIguaisAZero() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {
        Boleto boletoZerado = new Boleto("000", new Date(02-04-2023), new BigDecimal("00.00"));
        Fatura faturaZerada = new Fatura(LocalDate.now(), new BigDecimal("0.00"), "Cliente");

        listaBoletos.add(boletoZerado);

        processador.processar(listaBoletos, faturaZerada);
        assertTrue(faturaZerada.isPaga());
    }

    @Test(expected = DadosDoClienteException.class)
    public void deveLancarExcecaoNomeDeClienteInvalido() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {
        Fatura faturaComNomeInvalido = new Fatura(LocalDate.now(), new BigDecimal("450.00"), "");

        listaBoletos.add(boleto1);

        processador.processar(listaBoletos, faturaComNomeInvalido);
    }

    @Test(expected = BoletosIncorretosException.class)
    public void deveLancarExcecaoListaDeBoletosVazia() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {

        processador.processar(listaBoletos, fatura);
    }


    @Test
    public void deveSerPagaComSucessoBoletoComValorMaiorQueODaFatura() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {
        Boleto boletoMaiorQueAFatura = new Boleto("000", new Date(02-04-2023), new BigDecimal("750.00"));
        listaBoletos.add(boletoMaiorQueAFatura);
        processador.processar(listaBoletos, fatura);
        assertTrue(fatura.isPaga());
    }

    @Test(expected = FaturaNaoPagaException.class)
    public void deveSerMarcadaComoNaoPagaComBoletosMenoresQueOValorDaFatura() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {
        Boleto boleto4 = new Boleto("000", new Date(02-04-2023), new BigDecimal("5.00"));
        Boleto boleto5 = new Boleto("000", new Date(02-04-2023), new BigDecimal("5.00"));
        Boleto boleto6 = new Boleto("000", new Date(02-04-2023), new BigDecimal("5.00"));

        listaBoletos.add(boleto4);
        listaBoletos.add(boleto5);
        listaBoletos.add(boleto6);
        processador.processar(listaBoletos, fatura);
        assertFalse(fatura.isPaga());
    }


    @Test
    public void deveSerPagaComSucessoBoletoComValorIgualODaFatura() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);
        processador.processar(listaBoletos, fatura);
        assertTrue(fatura.isPaga());
    }


    @Test
    public void deveSerPagaComSucessoBoletosComValorMaiorQueODaFatura() throws BoletosIncorretosException, DadosDoClienteException, FaturaNaoPagaException, FaturaIncorretaException {
        listaBoletos.add(boleto1);
        listaBoletos.add(boleto2);
        listaBoletos.add(boleto3);
        processador.processar(listaBoletos, fatura);
        assertTrue(fatura.isPaga());
    }

}
