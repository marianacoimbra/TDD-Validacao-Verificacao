package com.gerador.notafiscal.models;

import com.gerador.notafiscal.mappings.ServiceTypeTaxMapping;
import com.gerador.notafiscal.taxCalculators.TaxCalculator;

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
        ServiceTypeTaxMapping mapping = new ServiceTypeTaxMapping();
        TaxCalculator taxCalculator = mapping.getTaxCalculator(bill.getServiceType());
        return taxCalculator.calculate(getBillValue());
    }
}
