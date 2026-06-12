package envios.envio;

import envios.EnvioExpressI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnvioExpressTest {
    private EnvioExpress  envioExpress;
    private EnvioExpressI envioExpressI;

    @BeforeEach
    public void setUp() {
        envioExpressI = mock(EnvioExpressI.class);
        when(envioExpressI.calcularCosto(500)).thenReturn(800F);

        envioExpress = new EnvioExpress(envioExpressI);
    }

    @Test
    void verificarElCostoDeEnvioExpress() {
        assertEquals(800, envioExpress.costoDeEnvio(500, 500, "Avenida Siempre Viva"));
    }
}