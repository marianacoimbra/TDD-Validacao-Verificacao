package com.gerador.notafiscal.services;

import com.gerador.notafiscal.models.NotaFiscal;

public class SMTP {
    public void send(NotaFiscal notaFiscal) {
        System.out.println("Enviando nota fiscal do cliente " + notaFiscal.getClientName() + " por email...");
    }
}
