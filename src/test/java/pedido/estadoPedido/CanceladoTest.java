package pedido.estadoPedido;

import exceptions.PedidoException;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class CanceladoTest {

    private Pedido unPedido;
    private Cancelado estadoCancelado;
    private Item unItem;

    @BeforeEach
    void setUp(){
        unPedido = mock(Pedido.class);
        unItem = mock(Item.class);
        estadoCancelado = new Cancelado();
    }


    /*
     * Tests operaciones inválidas
     * Un pedido en estado Cancelado no debería poder hacer ninguna operación
     * */
    @Test
    void noEsPosibleAgregarUnItemEnUnPedidoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.cargarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede cargar el item");
    }

    @Test
    void noEsPosibleQuitarUnItemEnUnPedidoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.quitarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede quitar el item");
    }

    @Test
    void noEsPosibleConfirmarUnPedidoEnEstadoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.confirmarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser confirmado");
    }

    @Test
    void noEsPosibleCancelarUnPedidoEnEstadoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.cancelarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser cancelado");
    }

    @Test
    void noEsPosiblePrepararUnPedidoEnEstadoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.prepararPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser preparado");
    }

    @Test
    void noEsPosibleEnviarUnPedidoEnEstadoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.enviarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser enviado");
    }

    @Test
    void noEsPosibleEntregarUnPedidoEnEstadoCancelado(){
        assertThatThrownBy(() -> estadoCancelado.entregarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser entregado");
    }

}
