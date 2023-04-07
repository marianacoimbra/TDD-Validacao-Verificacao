package com.gerador.notafiscal.services;

import com.gerador.notafiscal.models.NotaFiscal;

public class SAP {
    public void send(NotaFiscal notaFiscal) {
        System.out.println("Enviando nota fiscal do cliente " + notaFiscal.getClientName() + " para o SAP...");
    }
}
