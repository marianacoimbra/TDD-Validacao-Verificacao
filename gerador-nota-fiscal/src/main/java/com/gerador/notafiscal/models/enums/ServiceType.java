package com.gerador.notafiscal.models.enums;

public enum ServiceType {
    CONSULTING("consultoria"),
    TRAINING("treinamento"),
    OTHER("outro");

    private final String description;

    ServiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
