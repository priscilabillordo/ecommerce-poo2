package subsistema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.Enviado;
import pedido.estadoPedido.EstadoPedido;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneradorFacturaTest {
    private GeneradorFactura generadorFactura;
    private EstadoPedido estadoEntregado;
    private EstadoPedido estadoNoEntregado;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        generadorFactura  = new GeneradorFactura();
        estadoEntregado   = new Entregado();
        estadoNoEntregado = new Enviado();

        pedido = mock(Pedido.class);

        when(pedido.getFecha()).thenReturn(LocalDate.of(2026, 6, 28));

        when(pedido.costoTotal()).thenReturn(15000.0);
    }

}
