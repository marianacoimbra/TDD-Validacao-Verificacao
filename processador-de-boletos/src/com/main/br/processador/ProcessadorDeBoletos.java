package com.main.br.processador;

import com.main.br.processador.boleto.Boleto;
import com.main.br.processador.fatura.Fatura;
import com.main.br.processador.fatura.Pagamento;
import com.main.br.processador.fatura.TipoPagamento;

import java.math.BigDecimal;
import java.util.List;

public class ProcessadorDeBoletos {
    public void processar(List<Boleto> boletos, Fatura fatura) {
        BigDecimal valorTotalPago = BigDecimal.ZERO;

        for (Boleto boleto : boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), TipoPagamento.BOLETO, boleto.getData());
            fatura.adicionarPagamento(pagamento);

            valorTotalPago = valorTotalPago.add(boleto.getValorPago());
        }

        if (valorTotalPago.compareTo(fatura.getValorTotal()) >= 0) {
            fatura.marcarComoPaga();
        }
    }
}