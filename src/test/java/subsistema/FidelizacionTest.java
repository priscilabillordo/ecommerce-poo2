package subsistema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.Cancelado;
import pedido.estadoPedido.EstadoPedido;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FidelizacionTest {
    private Fidelizacion fidelizacion;
    private Cupon cupon;
    private EstadoPedido estadoCancelado;
    private EstadoPedido estadoNoCancelado;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        cupon = mock(Cupon.class);
        fidelizacion      = new Fidelizacion(cupon);
        estadoCancelado   = new Cancelado();
        estadoNoCancelado = new Borrador();
        pedido = mock(Pedido.class);
    }

    @Test
    void verificarQueSeGeneraUnCupon() {
        fidelizacion.cambioACancelado(pedido);

        verify(cupon).generarCupon(pedido);
    }

    @Test
    void noSeCreaUnCuponCuandoElPedidoSeEnvia() {
        fidelizacion.cambioAEnviado(pedido);
        verifyNoInteractions(cupon);
    }

    @Test
    void noSeCreaUnCuponCuandoElPedidoSeEntrega() {
        fidelizacion.cambioAEntregado(pedido);
        verifyNoInteractions(cupon);
    }

    @Test
    void noSeCreaUnCuponCuandoElPedidoSeConfirma() {
        fidelizacion.cambioAConfirmado(pedido);
        verifyNoInteractions(cupon);
    }
}