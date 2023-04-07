package com.gerador.notafiscal.models.enums;

public enum ServiceType {
    CONSULTING("Consultoria"),
    TRAINING("Treinamento"),
    OTHER("Outro");

    private final String description;

    ServiceType(String description) {
        this.description = description;
    }

    public static ServiceType getFromDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Service type description must not be null");
        }

        ServiceType[] serviceTypes = ServiceType.values();

        for (ServiceType serviceType : serviceTypes) {
            if (serviceType.getDescription().equals(description)) {
                return serviceType;
            }
        }

        return ServiceType.OTHER;
    }

    public String getDescription() {
        return description;
    }
}
