package functionalTests.avl;

import com.gerador.notafiscal.controllers.NotaFiscalController;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.services.NotaFiscalDao;
import com.gerador.notafiscal.services.SAP;
import com.gerador.notafiscal.services.SMTP;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class AvlTest {
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
    public void testFaturaConsultoriaAcimaLimite() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", 1);

        assertEquals(0.25, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test
    public void testFaturaConsultoriaZerada() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", 0);

        assertEquals(0, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFaturaConsultoriaNegativa() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", -1);
    }

    @Test
    public void testFaturaTreinamentoAcimaLimite() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Treinamento", 1);

        assertEquals(0.15, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test
    public void testFaturaTreinamentoZerada() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Treinamento", 0);

        assertEquals(0, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFaturaTreinamentoNegativa() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Treinamento", -1);
    }

    @Test
    public void testFaturaOutroAcimaLimite() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Outro", 1);

        assertEquals(0.06, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test
    public void testFaturaOutroZerada() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Outro", 0);

        assertEquals(0, notaFiscal.getTaxValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFaturaOutroNegativa() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Outro", -1);
    }
}
