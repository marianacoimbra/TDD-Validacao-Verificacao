package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.Test;

public class TaxInvoiceTest {
    public Invoice defaultInvoice;
    public TaxInvoice defaultTaxInvoice;

    @Before
    public void setup() {
        this.defaultInvoice = new Invoice("Davi Sousa", "Rua dos Bobos, 0", ServiceType.CONSULTING, 245.80);
        this.defaultTaxInvoice = new TaxInvoice(defaultInvoice);
    }

    @Test
    public void shouldCreateConsultingTaxInvoice() {
        Invoice invoice = new Invoice("João Alves", "Rua dos Bobos, 0", ServiceType.CONSULTING, 341.33);
        new TaxInvoice(invoice);
    }

    @Test
    public void shouldCreateTrainingTaxInvoice() {
        Invoice invoice = new Invoice("João Alves", "Rua dos Bobos, 0", ServiceType.TRAINING, 341.33);
        new TaxInvoice(invoice);
    }

    @Test
    public void shouldCreateOtherTaxInvoice() {
        Invoice invoice = new Invoice("João Alves", "Rua dos Bobos, 0", ServiceType.OTHER, 341.33);
        new TaxInvoice(invoice);
    }
}
