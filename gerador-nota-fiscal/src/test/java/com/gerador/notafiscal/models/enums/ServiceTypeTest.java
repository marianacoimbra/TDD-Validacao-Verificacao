package com.gerador.notafiscal.models.enums;

import com.gerador.notafiscal.App;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceTypeTest {
    @Test
    public void shouldGetServiceTypeFromDescription() {
        String description = "Consultoria";
        ServiceType serviceType = ServiceType.getFromDescription(description);

        assertEquals(description, serviceType.getDescription());
    }

    @Test
    public void shouldGetServiceTypeFromTrainingDescription() {
        String description = "Treinamento";
        ServiceType serviceType = ServiceType.getFromDescription(description);

        assertEquals(description, serviceType.getDescription());
    }

    @Test
    public void shouldGetServiceTypeFromOtherDescription() {
        String description = "Outro";
        ServiceType serviceType = ServiceType.getFromDescription(description);

        assertEquals(description, serviceType.getDescription());
    }

    @Test
    public void shouldGetServiceTypeFromUnknownDescription() {
        String description = "Qualquer tipo";
        ServiceType serviceType = ServiceType.getFromDescription(description);

        assertEquals("Outro", serviceType.getDescription());
    }

    @Test
    public void shouldGetListOfAllServiceTypeDescriptions() {
        List<String> expected = Arrays.asList("Consultoria", "Treinamento", "Outro");
        List<String> generatedList = ServiceType.getDescriptions();

        assertEquals(expected, generatedList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrownErrorForNullDescription() {
        ServiceType.getFromDescription(null);
    }
}
