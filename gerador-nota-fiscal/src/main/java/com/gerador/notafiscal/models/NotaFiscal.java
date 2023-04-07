package com.gerador.notafiscal.models;

import com.gerador.notafiscal.mappings.ServiceTypeTaxMapping;
import com.gerador.notafiscal.taxCalculators.TaxCalculator;

import java.text.DecimalFormat;

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

    public String toString() {
        String result = "Nome do cliente: " + getClientName() + "\n";

        DecimalFormat decimalFormat = new DecimalFormat("R$0.00");
        String formattedBillValue = decimalFormat.format(getBillValue());
        String formattedTaxValue = decimalFormat.format(getTaxValue());

        result += "Valor da fatura: " + formattedBillValue + "\n";
        result += "Valor do imposto: " + formattedTaxValue;

        return result;
    }
}
