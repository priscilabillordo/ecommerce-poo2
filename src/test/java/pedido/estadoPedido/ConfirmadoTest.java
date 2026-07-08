package pedido.estadoPedido;

import exceptions.PedidoException;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import subsistema.Subsistema;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConfirmadoTest {

    private Confirmado estadoConfirmado;
    private Pedido unPedido;
    private Subsistema unSubsistema;
    private Item unItem;

    @BeforeEach
    void setUp(){
        unSubsistema = mock(Subsistema.class);
        unPedido = mock(Pedido.class);
        unItem = mock(Item.class);
        estadoConfirmado = new Confirmado();
    }

    @Test
    void sePreparaUnPedidoConfirmadoCorrectamente(){
        estadoConfirmado.prepararPedido(unPedido);
        verify(unPedido).setEstado(isA(EnPreparacion.class));
    }

    @Test
    void seNotificaCambiarAConfimado() {
        estadoConfirmado.notificar(unPedido, unSubsistema);

        verify(unSubsistema).cambioAConfirmado(unPedido);
    }

    @Test
    void seCancelaUnPedidoConfirmadoCorrectamente(){
        estadoConfirmado.cancelarPedido(unPedido);
        verify(unPedido).setEstado(isA(Cancelado.class));
    }

    /*
     * Tests operaciones inválidas
     * Un pedido en estado Confirmado no debería poder hacer operaciones fuera de
     * #prepararPedido y #cancelarPedido.
     * */

    @Test
    void noEsPosibleAgregarUnItemEnUnPedidoConfirmado(){
        assertThatThrownBy(() -> estadoConfirmado.cargarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede cargar el item");
    }

    @Test
    void noEsPosibleQuitarUnItemEnUnPedidoConfirmado(){
        assertThatThrownBy(() -> estadoConfirmado.quitarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede quitar el item");
    }

    @Test
    void noEsPosibleConfirmarUnPedidoEnEstadoConfirmado(){
        assertThatThrownBy(() -> estadoConfirmado.confirmarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser confirmado");
    }

    @Test
    void noEsPosibleEnviarUnPedidoEnEstadoConfirmado(){
        assertThatThrownBy(() -> estadoConfirmado.enviarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser enviado");
    }

    @Test
    void noEsPosibleEntregarUnPedidoEnEstadoConfirmado(){
        assertThatThrownBy(() -> estadoConfirmado.entregarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser entregado");
    }

}
