package com.gerador.notafiscal;

import com.gerador.notafiscal.models.enums.ServiceType;
import com.gerador.notafiscal.taxCalculators.ConsultingTaxCalculator;
import com.gerador.notafiscal.taxCalculators.DefaultTaxCalculator;
import com.gerador.notafiscal.taxCalculators.TaxCalculator;
import com.gerador.notafiscal.taxCalculators.TrainingTaxCalculator;

import java.util.HashMap;
import java.util.Map;

public class ServiceTypeTaxMapping {
    private final Map<ServiceType, TaxCalculator> mapping;

    public ServiceTypeTaxMapping() {
        this.mapping = new HashMap<>();
        addTaxCalculatorsMapping();
    }

    private void addTaxCalculatorsMapping() {
        mapping.put(ServiceType.CONSULTING, new ConsultingTaxCalculator());
        mapping.put(ServiceType.TRAINING, new TrainingTaxCalculator());
        mapping.put(ServiceType.OTHER, new DefaultTaxCalculator());
    }

    public TaxCalculator getTaxCalculator(ServiceType serviceType) {
        if (serviceType == null) {
            throw new IllegalArgumentException("Service type must not be null");
        }

        TaxCalculator taxCalculator = mapping.get(serviceType);
        if (taxCalculator == null) {
            throw new IllegalArgumentException("Service type must be mapped to a tax calculator");
        }

        return taxCalculator;
    }
}
