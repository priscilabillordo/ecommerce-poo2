package metodoDeEnvio;

import pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnvioEstandarTest {
    private EnvioEstandar envioEstandar;
    private CorreoArgentino correoArgentino;
    private Pedido   pedido;

    @BeforeEach
    public void setUp() {
        correoArgentino = mock(CorreoArgentino.class);
        when(correoArgentino.estimarEnvio(500F, "Avenida Siempre Viva")).thenReturn(2000F);

        pedido = mock(Pedido.class);
        when(pedido.getDireccionEntrega()).thenReturn("Avenida Siempre Viva");
        when(pedido.peso()).thenReturn(500.0);

        envioEstandar = new EnvioEstandar(correoArgentino);
    }

    @Test
    void verificarElCostoDeEnvioEstandar() {
        assertEquals(2000.0, envioEstandar.costoDeEnvio(pedido));
    }
}