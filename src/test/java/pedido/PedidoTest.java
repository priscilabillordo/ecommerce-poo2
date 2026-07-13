package pedido;

import ecommerce.EcommerceData;
import ecommerce.NotaDeCredito;
import exceptions.MedioDePagoException;
import exceptions.PedidoException;
import item.Item;
import medioDePago.MedioDePago;
import metodoDeEnvio.MetodoDeEnvio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.Confirmado;
import pedido.estadoPedido.EstadoPedido;
import subsistema.Subsistema;
import venta.Venta;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoTest {
    private Pedido unPedido;
    private Pedido unPedidoConEstado;

    // DOCs
    private Item unItem;
    private Item otroItem;
    private Subsistema unSubsistema;
    private MetodoDeEnvio unMetodoDeEnvio;
    private MetodoDeEnvio otroMetodoDeEnvio;
    private MedioDePago unMedioDePago;
    private MedioDePago otroMedioDePago;
    private EcommerceData unData;
    private EstadoPedido unEstado;
    private EstadoPedido otroEstado;
    private NotaDeCredito unaNotaDeCredito;
    private Venta unaVenta;

    @BeforeEach
    void setUp(){
        unItem = mock(Item.class);
        when(unItem.getPrecioFinal()).thenReturn(2000.0);
        when(unItem.getPeso()).thenReturn(20.0);
        when(unItem.hayStock()).thenReturn(true);

        otroItem = mock(Item.class);
        when(otroItem.getPeso()).thenReturn(10.0);
        when(otroItem.hayStock()).thenReturn(true);

        unSubsistema = mock(Subsistema.class);

        unMetodoDeEnvio   = mock(MetodoDeEnvio.class);
        otroMetodoDeEnvio = mock(MetodoDeEnvio.class);

        unMedioDePago   = mock(MedioDePago.class);
        otroMedioDePago = mock(MedioDePago.class);

        unData = mock(EcommerceData.class);
        unaNotaDeCredito = mock(NotaDeCredito.class);
        unaVenta = mock(Venta.class);
        unPedido = new Pedido("Roque Saenz Peña 200", unMedioDePago, unMetodoDeEnvio, unData);
        when(unMetodoDeEnvio.costoDeEnvio(unPedido)).thenReturn(2500.0);

        unEstado   = mock(EstadoPedido.class);
        otroEstado = mock(EstadoPedido.class);

        unPedidoConEstado = new Pedido("9 de Julio 100", unMedioDePago, unMetodoDeEnvio, unData, unEstado);
        when(unMetodoDeEnvio.costoDeEnvio(unPedidoConEstado)).thenReturn(2500.0);
    }


    @Test
    void seCreaUnPedidoCorrectamente(){
        assertThat(unPedido.getItems()).isEmpty();
        assertThat(unPedido.getEstado()).isInstanceOf(Borrador.class);
        assertThat(unPedido.getDireccionEntrega()).isEqualTo("Roque Saenz Peña 200");
        assertThat(unPedido.getMedioDePago()).isEqualTo(unMedioDePago);
        assertThat(unPedido.getMetodoDeEnvio()).isEqualTo(unMetodoDeEnvio);
        assertThat(unPedido.getData()).isEqualTo(unData);
        assertEquals(null, unPedido.getFecha());
    }

    @Test
    void seSeteaUnPedidoCorrectamente() {
        unPedido.setEstado(otroEstado);
        unPedido.setMetodoDeEnvio(otroMetodoDeEnvio);
        unPedido.setMedioDePago(otroMedioDePago);

        assertThat(unPedido.getEstado()).isEqualTo(otroEstado);
        assertThat(unPedido.getMetodoDeEnvio()).isEqualTo(otroMetodoDeEnvio);
        assertThat(unPedido.getMedioDePago()).isEqualTo(otroMedioDePago);
    }

    @Test
    void verificarElPesoDelPedidoCon3ProductoIguales(){
        unPedido.agregarItem(unItem);
        unPedido.agregarItem(unItem);
        unPedido.agregarItem(unItem);

        assertEquals(60.0, unPedido.peso());
    }

    @Test
    void cuandoHayStockDeUnItem_SePuedeAgregarAlPedido(){
        unPedido.agregarItem(unItem);

        verify(unItem).hayStock();
        assertThat(unPedido.getItems()).contains(unItem);
    }

    @Test
    void cuandoNoHayStockDeUnItem_NoSePuedeAgregarAlPedido(){
        when(unItem.hayStock()).thenReturn(false);

        assertThatThrownBy(() -> unPedido.agregarItem(unItem))
                .isInstanceOf(PedidoException.class)
                .hasMessage("No se puede agregar el item (sin stock)");
    }

    @Test
    void seQuitaUnItemDeUnPedido(){
        unPedido.agregarItem(unItem);

        unPedido.sacarItem(unItem);
        verify(unItem).hayStock();
        assertThat(unPedido.getItems()).doesNotContain(unItem);
    }

    @Test
    void noSeQuitaUnItemDeUnPedidoQueNoLoTiene(){
        when(unItem.hayStock()).thenReturn(false);

        assertThatThrownBy(() -> unPedido.sacarItem(unItem))
                .isInstanceOf(PedidoException.class)
                .hasMessage("El item no está en el pedido");
    }

    @Test
    void seConfirmaUnPedidoCuandoSuPagoFueProcesado(){
        unPedido.confirmar();

        verify(unMedioDePago).procesarPago(unPedido);
        assertThat(unPedido.getEstado()).isInstanceOf(Confirmado.class);
    }

    @Test
    void cuandoSeCancelaUnPedido_seReponeElStockDeUnItem(){
        unPedido.agregarItem(unItem);

        unPedido.confirmar();
        unPedido.cancelar();

        verify(unItem).aumentarStock();
    }

    @Test
    void seCalculaElCostoDeItemsDeUnPedido(){
        unPedido.agregarItem(unItem);
        assertThat(unPedido.costoDeItems()).isEqualTo(2000.0);
    }

    @Test
    void seCalculaElCostoFinalDeUnPedido(){
        unPedido.agregarItem(unItem);
        double costoDePedido = unPedido.costoTotal();
        verify(unMetodoDeEnvio).costoDeEnvio(unPedido);
        assertThat(costoDePedido).isEqualTo(4500.0);
    }

    @Test
    void seCalculaElCostoDeEnvioDeUnPedido() {
        unPedido.agregarItem(unItem);

        double total = unPedido.costoDeEnvio();
        verify(unMetodoDeEnvio).costoDeEnvio(unPedido);
        assertThat(total).isEqualTo(2500.0);
    }

    @Test
    void seCalculaElPesoDeVariosItems() {
        unPedido.agregarItem(unItem);
        unPedido.agregarItem(otroItem);
        assertThat(unPedido.peso()).isEqualTo(30.0);
    }

    @Test
    void seCalculaElPesoDeUnPedido(){
        unPedido.agregarItem(unItem);

        assertThat(unPedido.peso()).isEqualTo(20.0);
    }

    @Test
    void cuandoSeReembolsaAUnCliente_ElPedidoDelegaLaNotaDeCreditoASuData(){
        unPedido.reembolsar(unaNotaDeCredito);

        verify(unData).agregarNota(unaNotaDeCredito);
    }

    @Test
    void seAgregaUnSubsistemaAlPedido(){
        unPedido.addSubsistema(unSubsistema);
        assertThat(unPedido.getSubsistemas()).contains(unSubsistema);
    }

    @Test
    void seEliminaUnSubsistemaDelPedido(){
        unPedido.addSubsistema(unSubsistema);
        unPedido.deleteubsistema(unSubsistema);
        assertThat(unPedido.getSubsistemas()).doesNotContain(unSubsistema);
    }

    @Test
    void verificarQueSeNotificaUnCambioEstado() {
        unPedido.addSubsistema(unSubsistema);
        unPedido.setEstado(unEstado);

        verify(unEstado).notificar(unPedido, unSubsistema);
    }

    @Test
    void verificarQueSeRegistraUnaVentaCorrectamente() {

        unPedido.registrarVenta(unaVenta);

        verify(unData).agregarVenta(unaVenta);
    }

    @Test
    void verificarQueSeRegistraTransaccion() {
        unPedido.registrarTransaccion("123456789");

        assertEquals("123456789", unPedido.getCodigoTransaccion());
    }

    // Tests delegacion al estado de un Pedido
    @Test
    void seInicializaUnPedidoConEstadoCorrectamente(){
        assertThat(unPedidoConEstado.getItems()).isEmpty();
        assertThat(unPedidoConEstado.getEstado()).isEqualTo(unEstado);
        assertThat(unPedidoConEstado.getDireccionEntrega()).isEqualTo("9 de Julio 100");
        assertThat(unPedidoConEstado.getMedioDePago()).isEqualTo(unMedioDePago);
        assertThat(unPedidoConEstado.getMetodoDeEnvio()).isEqualTo(unMetodoDeEnvio);
    }

    @Test
    void cuandoSeAgregaUnItemAUnPedidoElPedidoLoDelegaASuEstado(){
        unPedidoConEstado.agregarItem(unItem);

        verify(unEstado).cargarItem(unItem, unPedidoConEstado);
    }

    @Test
    void cuandoSeQuitaUnItemDeUnPedidoElPedidoLoDelegaASuEstado(){
        unPedidoConEstado.addItem(unItem);

        unPedidoConEstado.sacarItem(unItem);

        verify(unEstado).quitarItem(unItem, unPedidoConEstado);
    }

    @Test
    void cuandoSeQuiereQuitarUnItemDeUnPedidoQueNoTieneNoLoDelega(){
        when(unItem.hayStock()).thenReturn(false);

        assertThatThrownBy(() -> unPedido.sacarItem(unItem))
                .isInstanceOf(PedidoException.class)
                .hasMessage("El item no está en el pedido");

        verifyNoInteractions(unEstado);
    }

    @Test
    void cuandoSePreparaUnPedidoEsteLoDelegaAlEstado(){
        unPedidoConEstado.preparar();

        verify(unEstado).prepararPedido(unPedidoConEstado);
    }


    @Test
    void cuandoSeEnviaUnPedidoEsteLoDelegaAlEstado(){
        unPedidoConEstado.enviar();

        verify(unEstado).enviarPedido(unPedidoConEstado);
    }

    @Test
    void cuandoSeEntregaUnPedidoEsteLoDelegaAlEstado(){
        unPedidoConEstado.entregar();

        verify(unEstado).entregarPedido(unPedidoConEstado);
    }

    @Test
    void cuandoSeCancelaUnPedidoEsteLoDelegaAlEstado(){
        unPedidoConEstado.cancelar();

        verify(unEstado).cancelarPedido(unPedidoConEstado);
    }
}
