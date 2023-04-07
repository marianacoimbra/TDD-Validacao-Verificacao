package com.gerador.notafiscal.services;

import com.gerador.notafiscal.models.NotaFiscal;

public class NotaFiscalDao {
    public void saveToDB(NotaFiscal notaFiscal) {
        if (notaFiscal == null) {
            throw new IllegalArgumentException("Nota fiscal must not be null");
        }

        System.out.println("Salvando nota fiscal do cliente " + notaFiscal.getClientName() + " no banco de dados...");
    }
}
