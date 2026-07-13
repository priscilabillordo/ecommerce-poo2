package medioDePago.comprobanteDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CuponDePagoTest {

    private CuponDePago unCuponDePago;

    @BeforeEach
    void setUp(){
        unCuponDePago = new CuponDePago("1456", "AX-100000-7-10-13");
    }

    @Test
    void seVerificaQueUnComprobanteImprimeSusDatos(){
        assertThat(unCuponDePago.imprimir()).isNotNull();
        assertThat(unCuponDePago.imprimir()).contains("1456", "AX-100000-7-10-13");

    }
}
