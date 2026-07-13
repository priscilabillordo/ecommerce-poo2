package medioDePago.comprobanteDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ComprobanteCBUTest {

    private ComprobanteCBU unComprobanteCBU;

    @BeforeEach
    void setUp(){
        unComprobanteCBU = new ComprobanteCBU("AX-100000-7-10-13", "20000004565245588752");
    }

    @Test
    void seVerificaQueUnComprobanteImprimeSusDatos(){
        assertThat(unComprobanteCBU.imprimir()).isNotNull();
        assertThat(unComprobanteCBU.imprimir()).contains("AX-100000-7-10-13", "20000004565245588752");
    }
}
