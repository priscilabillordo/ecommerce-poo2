package envios.envio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetiroEnSucursalTest {
    private RetiroEnSucursal retiroEnSucursal;

    @BeforeEach
    public void setUp() {
        retiroEnSucursal = new RetiroEnSucursal();
    }

    @Test
    void verificarElCostoDeRetiroEnSucursal() {
        assertEquals(0, retiroEnSucursal.costoDeEnvio(1999, 500, "No hay direccion, se retira en sucursal"));
    }
}
