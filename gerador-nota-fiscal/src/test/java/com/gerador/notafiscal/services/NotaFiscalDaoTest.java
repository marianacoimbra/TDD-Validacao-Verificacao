package com.gerador.notafiscal.services;

import com.gerador.notafiscal.models.Bill;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.models.enums.ServiceType;
import org.junit.Before;
import org.junit.Test;

public class NotaFiscalDaoTest {
    public NotaFiscal notaFiscal;

    @Before
    public void setup() {
        Bill bill = new Bill("Davi Sousa", "Rua dos Bobos, 0", ServiceType.CONSULTING, 1000.0);
        this.notaFiscal = new NotaFiscal(bill);
    }

    @Test
    public void shouldHaveNoErrorsOnSave() {
        NotaFiscalDao notaFiscalDao = new NotaFiscalDao();
        notaFiscalDao.saveToDB(notaFiscal);
    }
}
