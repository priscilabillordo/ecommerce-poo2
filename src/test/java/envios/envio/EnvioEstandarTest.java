package envios.envio;

import envios.CorreoArgentino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnvioEstandarTest {
    private EnvioEstandar    envioEstandar;
    private CorreoArgentino correoArgentino;

    @BeforeEach
    public void setUp() {
        correoArgentino = mock(CorreoArgentino.class);
        when(correoArgentino.estimarEnvio(500, "No hay direccion, se retira en sucursal")).thenReturn(2000F);

        envioEstandar = new EnvioEstandar(correoArgentino);
    }

    @Test
    void verificarElCostoDeEnvioEstandar() {
        assertEquals(2000F, envioEstandar.costoDeEnvio(1999, 500, "No hay direccion, se retira en sucursal"));
    }
}