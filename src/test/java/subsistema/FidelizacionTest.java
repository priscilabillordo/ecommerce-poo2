package subsistema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.Cancelado;
import pedido.estadoPedido.EstadoPedido;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class FidelizacionTest {
    private Fidelizacion fidelizacion;
    private EstadoPedido estadoCancelado;
    private EstadoPedido estadoNoCancelado;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        fidelizacion      = new Fidelizacion();
        estadoCancelado   = new Cancelado();
        estadoNoCancelado = new Borrador();
        pedido = mock(Pedido.class);
    }

}