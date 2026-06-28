package metodoDeEnvio;

import pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class RetiroEnSucursalTest {
    private RetiroEnSucursal retiroEnSucursal;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = mock(Pedido.class);
        retiroEnSucursal = new RetiroEnSucursal();
    }

    @Test
    void verificarElCostoDeRetiroEnSucursal() {
        assertEquals(0, retiroEnSucursal.costoDeEnvio(pedido));
    }
}
