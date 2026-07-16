package unqShop;

import criterioDeBusqueda.CriterioDeBusqueda;
import ecommerce.EcommerceData;
import ecommerce.NotaDeCredito;
import formato.Formato;
import item.Item;
import metodoDeEnvio.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import reporte.Reporte;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class UnqShopTest {

    private UnqShop unqShop;
    private Pedido unPedido;
    private Item unItem;
    private EcommerceData data;
    private Reporte unReporte;
    private Formato unFormato;
    private CriterioDeBusqueda unCriterio;
    private Sucursal sucursal;

    @BeforeEach
    void setUp(){
        data = mock(EcommerceData.class);
        when(data.getNotasDeCredito()).thenReturn(Collections.emptyList());
        unItem = mock(Item.class);
        unCriterio = mock(CriterioDeBusqueda.class);
        List<Item> itemsEsperados = List.of(unItem);
        when(unCriterio.filtrarItems(anyList())).thenReturn(itemsEsperados);
        unPedido = mock(Pedido.class);
        unReporte = mock(Reporte.class);
        unFormato = mock(Formato.class);
        sucursal = mock(Sucursal.class);
        when(unReporte.generarReporte(anyList())).thenReturn(unReporte);
        when(unFormato.exportar(unReporte)).thenReturn("Producto,Cantidad,PrecioPromedio");
        unqShop = new UnqShop(data);
    }

    @Test
    void seInicializaUnaUnqShopCorrectamente(){
        assertThat(unqShop.getCatalogo()).isEmpty();
        assertThat(unqShop.getPedidos()).isEmpty();
        assertThat(unqShop.getData()).isEqualTo(data);
        assertThat(unqShop.getSucursales()).isEmpty();
    }

    @Test
    void seVerificaQueElComercioUnqShopAgregaUnItemASuCatalogo(){
        unqShop.agregarItem(unItem);

        assertThat(unqShop.getCatalogo()).contains(unItem);
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

    @Test
    void cuandoSeSolicitaUnReporteEsteEsElEsperado(){
        String reporteExportado = unqShop.generarReporte(unReporte, unFormato);
        verify(unReporte).generarReporte(unqShop.ventas());
        verify(unFormato).exportar(unReporte);

        assertThat(reporteExportado).isEqualTo("Producto,Cantidad,PrecioPromedio");
    }

    @Test
    void cuandoSeFiltranItemsPorCriterio_SeObtieneUnResultadoEsperado(){
        unqShop.agregarItem(unItem);
        List<Item> itemsFiltrados = unqShop.buscarItems(unCriterio);
        assertThat(itemsFiltrados).contains(unItem);
    }

    @Test
    void seVerificaQueUnUnqShopNoTieneNotasDeCreditoRegistradas(){
        List<NotaDeCredito> notasDeCredito = unqShop.notasDeCredito();
        verify(data).getNotasDeCredito();
        assertThat(notasDeCredito).isEmpty();
    }

    @Test
    void seRegistraUnaSucursalEnElEcommerce(){
        unqShop.registrarSucursal(sucursal);

        assertThat(unqShop.getSucursales()).contains(sucursal);
    }
}
