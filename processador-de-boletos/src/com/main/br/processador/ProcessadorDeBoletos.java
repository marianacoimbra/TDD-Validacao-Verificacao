package com.main.br.processador;

import com.main.Exceptions.BoletosIncorretosException;
import com.main.Exceptions.DadosDoClienteException;
import com.main.Exceptions.FaturaIncorretaException;
import com.main.Exceptions.FaturaNaoPagaException;
import com.main.br.processador.boleto.Boleto;
import com.main.br.processador.fatura.Fatura;
import com.main.br.processador.fatura.Pagamento;
import com.main.br.processador.fatura.TipoPagamento;

import java.math.BigDecimal;
import java.util.List;

public class ProcessadorDeBoletos {
    public void processar(List<Boleto> boletos, Fatura fatura) throws BoletosIncorretosException, FaturaIncorretaException, DadosDoClienteException, FaturaNaoPagaException {

        checkBoletos(boletos);
        checkFatura(fatura);
        checkDados(fatura);

        BigDecimal valorTotalPago = BigDecimal.ZERO;

        for (Boleto boleto : boletos) {
            if(boleto.getValorPago().compareTo(BigDecimal.ZERO) < 0) {
                throw new BoletosIncorretosException("O valor do boleto deve ser positivo");
            }
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), TipoPagamento.BOLETO, boleto.getData());
            fatura.adicionarPagamento(pagamento);

            valorTotalPago = valorTotalPago.add(boleto.getValorPago());
        }

        if (valorTotalPago.compareTo(fatura.getValorTotal()) >= 0) {
            fatura.marcarComoPaga();
        } else {
            throw new FaturaNaoPagaException("A fatura não foi paga, valor inferior ao valor da fatura");
        }
    }

    public void checkBoletos(List<Boleto> boletos) throws BoletosIncorretosException {
        if (boletos == null || boletos.isEmpty()) {
            throw new BoletosIncorretosException("Erro: nenhum valor passado nos boletos.");
        }
    }


    public void checkFatura(Fatura fatura) throws FaturaIncorretaException {
        if (fatura.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new FaturaIncorretaException("Erro: fatura negativa.\n");
        }
    }

    public void checkDados(Fatura fatura) throws DadosDoClienteException {
        if (fatura.getNomeCliente() == "" || fatura.getNomeCliente().equals(null) ) {
            throw new DadosDoClienteException(".");
        }
    }
}