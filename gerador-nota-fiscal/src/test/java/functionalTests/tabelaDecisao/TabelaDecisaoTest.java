package functionalTests.tabelaDecisao;

import com.gerador.notafiscal.controllers.NotaFiscalController;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.services.NotaFiscalDao;
import com.gerador.notafiscal.services.SAP;
import com.gerador.notafiscal.services.SMTP;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class TabelaDecisaoTest {
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

    @Test(expected = IllegalArgumentException.class)
    public void testRegra1VerificaErro() {
        notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", -1);
    }

    @Test
    public void testRegra2ConsultoriaOk() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Consultoria", 100);

        assertEquals(25, notaFiscal.getTaxValue(), 0.0001);

        verify(notaFiscalDao, times(1)).saveToDB(any());
        verify(sap, times(1)).send(any());
        verify(smtp, times(1)).send(any());
    }

    @Test
    public void testRegra3ConsultoriaOk() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Treinamento", 100);

        assertEquals(15, notaFiscal.getTaxValue(), 0.0001);

        verify(notaFiscalDao, times(1)).saveToDB(any());
        verify(sap, times(1)).send(any());
        verify(smtp, times(1)).send(any());
    }

    @Test
    public void testRegra4ConsultoriaOk() {
        NotaFiscal notaFiscal = notaFiscalController.generate("Davi Sousa", "Rua dos Bobos, 0", "Outro", 100);

        assertEquals(6, notaFiscal.getTaxValue(), 0.0001);

        verify(notaFiscalDao, times(1)).saveToDB(any());
        verify(sap, times(1)).send(any());
        verify(smtp, times(1)).send(any());
    }
}
