package unqShop;

import ecommerce.EcommerceData;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class UnqShopTest {

    private UnqShop unqShop;
    private Pedido unPedido;
    private Item unItem;
    private EcommerceData data;

    @BeforeEach
    void setUp(){
        data = mock(EcommerceData.class);
        unItem = mock(Item.class);
        unPedido = mock(Pedido.class);

        unqShop = new UnqShop(data);
    }

    @Test
    void seInicializaUnaUnqShopCorrectamente(){
        assertThat(unqShop.getCatalogo()).isEmpty();
        assertThat(unqShop.getPedidos()).isEmpty();
        assertThat(unqShop.getData()).isEqualTo(data);
    }

    @Test
    void cuandoSeRegistraUnPedido_LaUnqShopLoContiene(){
        unqShop.registrarPedido(unPedido);

        assertThat(unqShop.getPedidos()).contains(unPedido);
    }

    @Test
    void cuandoNoHayPedidosTampocoHayVentas(){
        assertThat(unqShop.ventas()).isEmpty();
    }




}
