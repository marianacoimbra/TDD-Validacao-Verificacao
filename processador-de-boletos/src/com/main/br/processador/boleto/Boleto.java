package com.main.br.processador.boleto;

import java.math.BigDecimal;
import java.util.Date;

public class Boleto {

    private String codigo;
    private Date data;
    private BigDecimal valorPago;

    public Boleto(String codigo, Date data, BigDecimal valorPago) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }
}
