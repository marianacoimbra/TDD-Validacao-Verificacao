package com.gerador.notafiscal;

import com.gerador.notafiscal.models.enums.ServiceType;
import com.gerador.notafiscal.taxCalculators.ConsultingTaxCalculator;
import com.gerador.notafiscal.taxCalculators.DefaultTaxCalculator;
import com.gerador.notafiscal.taxCalculators.TaxCalculator;
import com.gerador.notafiscal.taxCalculators.TrainingTaxCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ServiceTypeTaxMappingTest {
    @Parameter
    public ServiceType serviceType;

    @Parameter(1)
    public TaxCalculator expectedTaxCalculator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Executar testes para cada tipo de serviço com os seus calculadores
                { ServiceType.CONSULTING, new ConsultingTaxCalculator() },
                { ServiceType.TRAINING, new TrainingTaxCalculator() },
                { ServiceType.OTHER, new DefaultTaxCalculator() }
        });
    }

    @Test
    public void shouldGetCorrectTaxCalculator() {
        ServiceTypeTaxMapping taxMapping = new ServiceTypeTaxMapping();
        TaxCalculator taxCalculator = taxMapping.getTaxCalculator(serviceType);
        assertEquals(expectedTaxCalculator.getClass(), taxCalculator.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullServiceType() {
        ServiceTypeTaxMapping taxMapping = new ServiceTypeTaxMapping();
        taxMapping.getTaxCalculator(null);
    }
}
