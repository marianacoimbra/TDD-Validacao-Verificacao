package functionalTests.particaoEq;

import com.gerador.notafiscal.controllers.NotaFiscalController;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.services.NotaFiscalDao;
import com.gerador.notafiscal.services.SAP;
import com.gerador.notafiscal.services.SMTP;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class ParticaoEqTest {
    public NotaFiscalController notaFiscalController;
    public NotaFiscalDao notaFiscalDao;
    public SAP sap;
    public SMTP smtp;

    @Before
    public void setup() {
        this.notaFiscalDao = mock(NotaFiscalDao.class);
        this.sap = mock(SAP.class);
        this.smtp = mock(SMTP.class);
        this.notaFiscalController = new NotaFiscalController(notaFiscalDao, sap, smtp);
    }

    @Test
    public void testValorValido() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", 100);

        assertEquals(25, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValorInvalido() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", -1);
    }

    @Test
    public void testTipoConsultoria() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", 100);

        assertEquals(25, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipoConsultoriaInvalido() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", -1);
    }

    @Test
    public void testTipoTreinamentoValido() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Treinamento", 100);

        assertEquals(15, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipoTreinamentoInvalido() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Treinamento", -1);
    }

    @Test
    public void testTipoOutroValido() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Outro", 100);

        assertEquals(6, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipoOutroInvalido() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Outro", -1);
    }
}
