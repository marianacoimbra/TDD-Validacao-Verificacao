package com.main.br.processador.fatura;

import java.math.BigDecimal;
import java.util.Date;

public class Pagamento {
    private BigDecimal valorPago;
    private TipoPagamento tipoPagamento;
    private Date data;

    public Pagamento(BigDecimal valorPago, TipoPagamento tipoPagamento, Date data) {
        this.valorPago = valorPago;
        this.tipoPagamento = tipoPagamento;
        this.data = data;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
