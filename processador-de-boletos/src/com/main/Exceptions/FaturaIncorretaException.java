package com.main.Exceptions;

public class FaturaIncorretaException extends Exception {

    public FaturaIncorretaException() {
        super();
    }

    public FaturaIncorretaException(String mensagem) {
        super(mensagem);
    }
}
