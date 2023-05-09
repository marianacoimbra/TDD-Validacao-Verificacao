package com.main.Exceptions;

public class FaturaNaoPagaException extends  Exception {
    public FaturaNaoPagaException() {
        super();
    }

    public FaturaNaoPagaException(String mensagem) {
        super(mensagem);
    }
}
