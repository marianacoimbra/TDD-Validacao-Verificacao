package com.gerador.notafiscal.models;

import com.gerador.notafiscal.models.enums.ServiceType;

import java.text.DecimalFormat;

public class Invoice {
    private final String clientName;
    private final String clientAddress;
    private final ServiceType serviceType;
    private final double value;

    public Invoice(String clientName, String clientAddress, ServiceType serviceType, double value) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.serviceType = serviceType;
        this.value = value;
    }

    public String getServiceType() {
        return serviceType.getDescription();
    }

    public String getClientName() {
        return clientName;
    }

    public String toString() {
        String result = "Nome do cliente: " + clientName + "\n";
        result += "Endereço do cliente: " + clientAddress + "\n";
        result += "Tipo do serviço: " + serviceType.getDescription() + "\n";

        DecimalFormat decimalFormat = new DecimalFormat("R$0.00");
        String formattedNumber = decimalFormat.format(value);

        result += "Valor da fatura: " + formattedNumber;

        return result;
    }
}
