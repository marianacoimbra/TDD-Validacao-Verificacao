package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;

public class Invoice {
    private String clientName;
    private String clientAddress;
    private ServiceType serviceType;
    private double value;

    public Invoice(String clientName, String clientAddress, ServiceType serviceType, double value) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.serviceType = serviceType;
        this.value = value;
    }
}
