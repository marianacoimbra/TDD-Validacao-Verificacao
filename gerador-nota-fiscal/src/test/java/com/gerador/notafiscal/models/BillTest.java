package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BillTest {
    public Bill defaultBill;

    @Before
    public void setup() {
        this.defaultBill = new Bill("Davi Sousa", "Rua dos Bobos, 0", ServiceType.CONSULTING, 245.80);
    }

    @Test
    public void shouldCreateBill() {
        new Bill("João Alves", "Rua dos Bobos, 0", ServiceType.TRAINING, 34.45);
    }

    @Test
    public void shouldCreateBillWithOtherService() {
        new Bill("João Alves", "Rua dos Bobos, 0", ServiceType.OTHER, 34.45);
    }

    @Test()
    public void shouldCreateWithZeroValue() {
        new Bill("João Alves", "Rua dos Bobos, 0", ServiceType.OTHER, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnNegativeValue() {
        new Bill("João Alves", "Rua dos Bobos, 0", ServiceType.OTHER, -34.45);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnNullClientName() {
        new Bill(null, "Rua dos Bobos, 0", ServiceType.OTHER, 34.45);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnNullClientAddress() {
        new Bill("João Alves", null, ServiceType.OTHER, 34.45);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnNullServiceType() {
        new Bill("João Alves", "Rua dos Bobos, 0", null, 34.45);
    }

    @Test
    public void shouldGetBillServiceType() {
        assertEquals(ServiceType.CONSULTING, defaultBill.getServiceType());
    }

    @Test
    public void shouldGetBillServiceTypeDescription() {
        assertEquals("Consultoria", defaultBill.getServiceTypeDescription());
    }

    @Test
    public void shouldGetClientName() {
        assertEquals("Davi Sousa", defaultBill.getClientName());
    }

    @Test
    public void shouldGetBillValue() {
        assertEquals(245.80, defaultBill.getValue(), 0);
    }

    @Test
    public void shouldHasToString() {
        String expectedString = "Nome do cliente: Davi Sousa\n" +
                "Endereço do cliente: Rua dos Bobos, 0\n" +
                "Tipo do serviço: Consultoria\n" +
                "Valor da fatura: R$245.80";

        assertEquals(expectedString, defaultBill.toString());
    }

    @Test
    public void testToStringWithZeroValue() {
        Bill bill = new Bill("João Alves", "Rua dos Bobos, 0", ServiceType.TRAINING, 0);

        String expectedString = "Nome do cliente: João Alves\n" +
                "Endereço do cliente: Rua dos Bobos, 0\n" +
                "Tipo do serviço: Treinamento\n" +
                "Valor da fatura: R$0.00";

        assertEquals(expectedString, bill.toString());
    }
}
