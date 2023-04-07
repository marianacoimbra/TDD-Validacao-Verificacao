package com.gerador.notafiscal.models.enums;

public enum ServiceType {
    CONSULTING("Consultoria"),
    TRAINING("Treinamento"),
    OTHER("Outro");

    private final String description;

    ServiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
