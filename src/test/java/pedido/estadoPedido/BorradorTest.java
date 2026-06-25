package pedido.estadoPedido;

import exceptions.PedidoException;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class BorradorTest {

    private Borrador estadoBorrador;
    private Pedido unPedido;
    private Item unItem;


    @BeforeEach
    void setUp(){
        // Mocks
        unItem = mock(Item.class);
        unPedido = mock(Pedido.class);
        // SUT Borrador
        estadoBorrador = new Borrador();
    }

    /*
    * Tests del comportamiento de un Pedido en estado Borrador
    * */

    @Test
    void seVerificaQueUnPedidoBorradorPuedeAgregarItems(){
        estadoBorrador.cargarItem(unItem, unPedido);
        verify(unPedido).addItem(unItem);
    }

    @Test
    void seVerificaQueUnPedidoBorradorPuedeQuitarItems(){
        estadoBorrador.quitarItem(unItem, unPedido);
        verify(unPedido).deleteItem(unItem);
    }

    @Test
    void seVerificaQueUnPedidoBorradorEsCancelado(){
        estadoBorrador.cancelarPedido(unPedido);
        verify(unPedido).setEstado(isA(Cancelado.class));
    }

    @Test
    void seVerificaQueUnPedidoBorradorEsConfirmado(){
        estadoBorrador.confirmarPedido(unPedido);
        verify(unPedido).setEstado(isA(Confirmado.class));
    }

    /*
     * Tests operaciones inválidas
     * */

    @Test
    void noEsPosiblePrepararUnPedidoEnEstadoBorrador(){
        assertThatThrownBy(() -> estadoBorrador.prepararPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser preparado");
    }

    @Test
    void noEsPosibleEnviarUnPedidoEnEstadoBorrador(){
        assertThatThrownBy(() -> estadoBorrador.enviarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser enviado");
    }

    @Test
    void noEsPosibleEntregarUnPedidoEnEstadoBorrador(){
        assertThatThrownBy(() -> estadoBorrador.entregarPedido(unPedido))
                .isInstanceOf(PedidoException.class)
                .hasMessage("Operación inválida: El pedido no puede ser entregado");
    }
}

