package pedido.estadoPedido;

import ecommerce.NotaDeCredito;
import exceptions.PedidoException;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EnPreparacionTest {

    private EnPreparacion estadoEnPreparacion;
    private Pedido unPedido;
    private Item unItem;

    @BeforeEach
    void setUp(){
        // Mocks
        unPedido = mock(Pedido.class);
        unItem = mock(Item.class);
        // SUT EnPreparacion
        estadoEnPreparacion = new EnPreparacion();
    }

    @Test
    void seCancelaUnPedidoEnPreparacionCorrectamente(){
        estadoEnPreparacion.cancelarPedido(unPedido);
        verify(unPedido).setEstado(isA(Cancelado.class));
        verify(unPedido).reponerStock();
        verify(unPedido).reembolsar(isA(NotaDeCredito.class));
    }

    @Test
    void seEnviaUnPedidoEnPreparacionCorrectamente(){
        estadoEnPreparacion.enviarPedido(unPedido);
        verify(unPedido).setEstado(isA(Enviado.class));
    }

    /*
     * Tests operaciones inválidas
     * Un pedido en estado EnPreparacion no debería poder hacer operaciones fuera de
     * #enviarPedido y #cancelarPedido.
     * */

    @Test
    void noEsPosibleAgregarUnItemEnUnPedidoEnPreparacion(){
        assertThatThrownBy(() -> estadoEnPreparacion.cargarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede cargar el item");
    }

    @Test
    void noEsPosibleQuitarUnItemEnUnPedidoEnPreparacion(){
        assertThatThrownBy(() -> estadoEnPreparacion.quitarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede quitar el item");
    }

    @Test
    void noEsPosibleConfirmarUnPedidoEnEstadoEnPreparacion(){
        assertThatThrownBy(() -> estadoEnPreparacion.confirmarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser confirmado");
    }

    @Test
    void noEsPosiblePrepararUnPedidoEnEstadoEnPreparacion(){
        assertThatThrownBy(() -> estadoEnPreparacion.prepararPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser preparado");
    }

    @Test
    void noEsPosibleEntregarUnPedidoEnEstadoEnPreparacion(){
        assertThatThrownBy(() -> estadoEnPreparacion.entregarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser entregado");
    }

}
