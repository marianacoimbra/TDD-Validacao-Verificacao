package com.gerador.notafiscal.models;

public class NotaFiscal {
    private final Bill bill;

    public NotaFiscal(Bill bill) {
        if (bill == null) {
            throw new IllegalArgumentException("Bill must not be null");
        }

        this.bill = bill;
    }

    public String getClientName() {
        return bill.getClientName();
    }

    public double getBillValue() {
        return bill.getValue();
    }

    public double getTaxValue() {
        return 0;
    }
}
