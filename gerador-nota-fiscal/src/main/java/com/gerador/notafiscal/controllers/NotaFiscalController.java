package com.gerador.notafiscal.controllers;

import com.gerador.notafiscal.models.Bill;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.models.enums.ServiceType;
import com.gerador.notafiscal.services.NotaFiscalDao;
import com.gerador.notafiscal.services.SAP;
import com.gerador.notafiscal.services.SMTP;

public class NotaFiscalController {
    public NotaFiscalDao notaFiscalDao;
    public SAP sap;
    public SMTP smtp;

    public NotaFiscalController(NotaFiscalDao notaFiscalDao, SAP sap, SMTP smtp) {
        this.notaFiscalDao = notaFiscalDao;
        this.sap = sap;
        this.smtp = smtp;
    }

    public NotaFiscalController() {
        this.notaFiscalDao = new NotaFiscalDao();
    }

    public NotaFiscal generate(String clientName, String clientAddress, String serviceTypeDescription, double billValue) {
        if (clientName == null || clientAddress == null || serviceTypeDescription == null) {
            throw new IllegalArgumentException("Bill information must be not null");
        }

        ServiceType serviceType = ServiceType.getFromDescription(serviceTypeDescription);
        Bill bill = new Bill(clientName, clientAddress, serviceType, billValue);

        NotaFiscal notaFiscal = new NotaFiscal(bill);
        sendNotaFiscalWithServices(notaFiscal);

        return notaFiscal;
    }

    private void sendNotaFiscalWithServices(NotaFiscal notaFiscal) {
        notaFiscalDao.saveToDB(notaFiscal);
        sap.send(notaFiscal);
        smtp.send(notaFiscal);
    }
}
