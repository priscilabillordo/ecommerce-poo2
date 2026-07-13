package medioDePago.billeteraVirtual;

import exceptions.MedioDePagoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BilleteraVirtualTest {

    private BilleteraVirtual billeteraVirtual;
    private APIBilletera api;
    private Pedido unPedido;

    @BeforeEach
    void setUp() {
        api = mock(APIBilletera.class);
        unPedido = mock(Pedido.class);
        billeteraVirtual = new BilleteraVirtual(20000, api);
    }

    @Test
    void seInicializaUnaBilleteraVirtualCorrectamente() {
        assertEquals(20000, billeteraVirtual.getSaldo());
        assertEquals(api, billeteraVirtual.getApi());
    }

    @Test
    void seValidanLosDatosDeLaBilletera() {
        billeteraVirtual.validarDatos();

        verify(api).validarSaldo();
    }

    @Test
    void seReservanLosFondosDeLaBilletera() {
        billeteraVirtual.reservarFondos();

        verify(api).bloquearSaldo();
    }

    @Test
    void seEjecutaLaTransaccionDeLaBilletera() {
        billeteraVirtual.ejecutarTransaccion();

        verify(api).acreditar();
    }

    @Test
    void seNotificaElResultadoDeUnaTransaccionDeLaBilletera() {
        billeteraVirtual.notificarResultado(unPedido);

        verify(api).notificar(unPedido);
        assertThat(billeteraVirtual.getCodigoTransaccion()).isNotNull();
    }

    @Test
    void seVerificaQueCuandoUnaBilleteraNoPuedeValidarSusDatos_Falla() {
        // Configuro el mock para que falle
        doThrow(new MedioDePagoException("Los datos no son válidos")).when(api).validarSaldo();

        assertThrows(
                MedioDePagoException.class,
                () -> billeteraVirtual.validarDatos()
        );
    }

    /*
    * Test procesarPago() heredado de MedioDePago
    * */

    @Test
    void seProcesaUnPagoCorrectamente(){
        // Pruebo el template
        billeteraVirtual.procesarPago(unPedido);

        // Verifico interacciones con la api
        verify(api).validarSaldo();
        verify(api).bloquearSaldo();
        verify(api).acreditar();
        verify(api).notificar(unPedido);

        // Verifico que se genera el codigo de transaccion
        assertThat(billeteraVirtual.getCodigoTransaccion()).isNotNull();
    }

    @Test
    void cuandoNoSeProcesaUnPagoLaApiNoHaceNadaMas(){
        // Configuro el mock para que falle
        doThrow(new MedioDePagoException("Los datos no son válidos")).when(api).validarSaldo();

        // Verifico que tira error
        assertThrows(
                MedioDePagoException.class,
                () -> billeteraVirtual.procesarPago(unPedido)
        );
        // Verifico no mas interacciones con la api
        verify(api, never()).bloquearSaldo();
        verify(api, never()).acreditar();
        verify(api, never()).notificar(unPedido);
    }

}