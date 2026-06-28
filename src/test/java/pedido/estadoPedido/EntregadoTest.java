package pedido.estadoPedido;

import exceptions.PedidoException;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class EntregadoTest {

    private Pedido unPedido;
    private Entregado estadoEntregado;
    private Item unItem;

    @BeforeEach
    void setUp(){
        unPedido = mock(Pedido.class);
        unItem = mock(Item.class);
        estadoEntregado = new Entregado();
    }

    /*
     * Tests operaciones inválidas
     * Un pedido en estado Entregado no debería poder hacer ninguna operación
     * */

    @Test
    void noEsPosibleAgregarUnItemEnUnPedidoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.cargarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede cargar el item");
    }

    @Test
    void noEsPosibleQuitarUnItemEnUnPedidoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.quitarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede quitar el item");
    }

    @Test
    void noEsPosibleConfirmarUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.confirmarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser confirmado");
    }

    @Test
    void noEsPosibleCancelarUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.cancelarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser cancelado");
    }

    @Test
    void noEsPosiblePrepararUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.prepararPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser preparado");
    }

    @Test
    void noEsPosibleEnviarUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.enviarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser enviado");
    }

    @Test
    void noEsPosibleEntregarUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEntregado.entregarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser entregado");
    }

}
