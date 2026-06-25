package pedido;

import ecommerce.EcommerceData;
import exceptions.PedidoException;
import item.Item;
import medioDePago.MedioDePago;
import metodoDeEnvio.MetodoDeEnvio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.EstadoPedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class PedidoTest {

    // SUT Pedido
    private Pedido unPedido;

    // DOCs
    private Item unItem;
    private MetodoDeEnvio unMetodoDeEnvio;
    private MedioDePago unMedioDePago;
    private EcommerceData unData;

    @BeforeEach
    void setUp(){
        unItem = mock(Item.class);
        when(unItem.getPrecioFinal()).thenReturn(2000.0);
        when(unItem.getPeso()).thenReturn(20.0);
        unMetodoDeEnvio = mock(MetodoDeEnvio.class);
        unMedioDePago = mock(MedioDePago.class);
        unData = mock(EcommerceData.class);
        unPedido = new Pedido("Roque Saenz Peña 200", unMedioDePago, unMetodoDeEnvio, unData);
        when(unMetodoDeEnvio.costoDeEnvio(unPedido)).thenReturn(2500.0);

    }

    @Test
    void seCreaUnPedidoCorrectamente(){
        assertThat(unPedido.getItems()).isEmpty();
        assertThat(unPedido.getEstado()).isInstanceOf(Borrador.class);
        assertThat(unPedido.getDireccionEntrega()).isEqualTo("Roque Saenz Peña 200");
        assertThat(unPedido.getMedioDePago()).isEqualTo(unMedioDePago);
        assertThat(unPedido.getMetodoDeEnvio()).isEqualTo(unMetodoDeEnvio);
    }

    @Test
    void seAgregaUnItemAUnPedido(){
        unPedido.agregarItem(unItem);
        assertThat(unPedido.getItems()).contains(unItem);
    }

    @Test
    void seQuitaUnItemDeUnPedido(){
        unPedido.agregarItem(unItem);

        unPedido.sacarItem(unItem);
        assertThat(unPedido.getItems()).doesNotContain(unItem);
    }

    @Test
    void noSeQuitaUnItemDeUnPedidoQueNoLoTiene(){
        assertThatThrownBy(() -> unPedido.sacarItem(unItem))
                .isInstanceOf(PedidoException.class)
                .hasMessage("El item no está en el pedido");
    }

    @Test
    void seCalculaElCostoDeItemsDeUnPedido(){
        unPedido.agregarItem(unItem);

        assertThat(unPedido.costoDeItems()).isEqualTo(2000.0);
    }

    @Test
    void seCalculaElCostoFinalDeUnPedido(){
        double costoDePedido = unPedido.costo();
        verify(unMetodoDeEnvio).costoDeEnvio(unPedido);
        assertThat(costoDePedido).isEqualTo(2500.0);
    }

    @Test
    void seCalculaElPesoDeUnPedido(){
        unPedido.agregarItem(unItem);

        assertThat(unPedido.peso()).isEqualTo(20.0);
    }

}
