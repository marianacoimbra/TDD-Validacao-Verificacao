package com.gerador.notafiscal;

import com.gerador.notafiscal.models.enums.ServiceType;
import com.gerador.notafiscal.taxCalculators.ConsultingTaxCalculator;
import com.gerador.notafiscal.taxCalculators.DefaultTaxCalculator;
import com.gerador.notafiscal.taxCalculators.TaxCalculator;
import com.gerador.notafiscal.taxCalculators.TrainingTaxCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ServiceTypeTaxMappingTest {
    public ServiceTypeTaxMapping serviceTypeTaxMapping;

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

    @Before
    public void setup() {
        this.serviceTypeTaxMapping = new ServiceTypeTaxMapping();
    }

    @Test
    public void shouldGetCorrectTaxCalculator() {
        TaxCalculator taxCalculator = serviceTypeTaxMapping.getTaxCalculator(serviceType);
        assertEquals(expectedTaxCalculator.getClass(), taxCalculator.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullServiceType() {
        serviceTypeTaxMapping.getTaxCalculator(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnUnknownServiceType() throws NoSuchFieldException, IllegalAccessException {
        // Tornamos o campo privado "mapping" acessível, para modificá-lo nesse teste
        Field mapping = ServiceTypeTaxMapping.class.getDeclaredField("mapping");
        mapping.setAccessible(true);

        // Atualiza o campo "mapping" para ser um Map vazio, sem calculadoras registradas
        ServiceTypeTaxMapping taxMapping = new ServiceTypeTaxMapping();
        mapping.set(taxMapping, new HashMap<ServiceType, TaxCalculator>());

        // Deveria lançar IllegalArgumentException
        taxMapping.getTaxCalculator(serviceType);
    }
}
