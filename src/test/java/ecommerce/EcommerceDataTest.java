package ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import venta.Venta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class EcommerceDataTest {
    private EcommerceData ecommerceData;
    private Venta venta;
    private NotaDeCredito notaDeCredito;

    @BeforeEach
    void setUp() {
        ecommerceData = new EcommerceData();

        venta = mock(Venta.class);
        notaDeCredito = mock(NotaDeCredito.class);
    }

    @Test
    void verificarQueEcommerceDataSeInicializaConListasVacias() {
        assertTrue(ecommerceData.getVentas().isEmpty());
        assertTrue(ecommerceData.getNotasDeCredito().isEmpty());
    }

    @Test
    void verificarAgregarUnaVentaAEcommerce() {
        ecommerceData.agregarVenta(venta);
        assertTrue(ecommerceData.getVentas().contains(venta));
    }

    @Test
    void verificarAgregarUnaNotaDeCreditoAEcommerce() {
        ecommerceData.agregarNota(notaDeCredito);
        assertTrue(ecommerceData.getNotasDeCredito().contains(notaDeCredito));
    }
}
