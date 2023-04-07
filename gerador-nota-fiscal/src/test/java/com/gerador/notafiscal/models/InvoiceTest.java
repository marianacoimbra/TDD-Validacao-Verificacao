package com.gerador.notafiscal.models;

import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {
    public Invoice defaultInvoice;

    @Before
    public void setup() {
        this.defaultInvoice = new Invoice("Davi Sousa", "Rua dos Bobos, 0", ServiceType.CONSULTING, 245.80);
    }

    @Test
    public void shouldCreateInvoice() {
        new Invoice("João Alves", "Rua dos Bobos, 0", ServiceType.TRAINING, 34.45);
    }
}
