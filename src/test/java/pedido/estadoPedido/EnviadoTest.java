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

public class EnviadoTest {

    private Enviado estadoEnviado;
    private Pedido unPedido;
    private Item unItem;

    @BeforeEach
    void setUp(){
        // Mocks
        unPedido = mock(Pedido.class);
        unItem = mock(Item.class);
        // SUT Enviado
        estadoEnviado = new Enviado();
    }

    @Test
    void seCancelaUnPedidoEnviadoCorrectamente(){
        estadoEnviado.cancelarPedido(unPedido);
        verify(unPedido).setEstado(isA(Cancelado.class));
        verify(unPedido).reembolsar(isA(NotaDeCredito.class));
    }

    @Test
    void seEntregaUnPedidoEnviadoCorrectamente(){
        estadoEnviado.entregarPedido(unPedido);
        verify(unPedido).setEstado(isA(Entregado.class));
    }

    /*
     * Tests operaciones inválidas
     * Un pedido en estado Enviado no debería poder hacer operaciones fuera de
     * #cancelarPedido y #entregarPedido.
     * */

    @Test
    void noEsPosibleAgregarUnItemEnUnPedidoEntregado(){
        assertThatThrownBy(() -> estadoEnviado.cargarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede cargar el item");
    }

    @Test
    void noEsPosibleQuitarUnItemEnUnPedidoEntregado(){
        assertThatThrownBy(() -> estadoEnviado.quitarItem(unItem, unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: No se puede quitar el item");
    }

    @Test
    void noEsPosibleConfirmarUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEnviado.confirmarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser confirmado");
    }

    @Test
    void noEsPosiblePrepararUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEnviado.prepararPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser preparado");
    }

    @Test
    void noEsPosibleEnviarUnPedidoEnEstadoEntregado(){
        assertThatThrownBy(() -> estadoEnviado.enviarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser enviado");
    }

}
