package medioDePago.billeteraVirtual;

import exceptions.MedioDePagoException;
import exceptions.PedidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BilleteraVirtualTest {

    private BilleteraVirtual billeteraVirtual;
    private APIBilletera api;
    private Pedido pedido;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        api    = mock(APIBilletera.class);

        billeteraVirtual = new BilleteraVirtual(20000, api);
    }

    @Test
    void seInicializaUnaBilleteraVirtualCorrectamente(){
        assertEquals(20000, billeteraVirtual.getSaldo());
        assertEquals(api, billeteraVirtual.getApi());
    }

    @Test
    void seValidanLosDatosDeLaBilletera(){
        billeteraVirtual.validarDatos();

        verify(api).validarSaldo();
    }

    @Test
    void seReservanLosFondosDeLaBilletera(){
        billeteraVirtual.reservarFondos();

        verify(api).bloquearSaldo();
    }

    @Test
    void seEjecutaLaTransaccionDeLaBilletera(){
        billeteraVirtual.ejecutarTransaccion();

        verify(api).acreditar();
    }

    @Test
    void seNotificaElResultadoDeUnaTransaccionDeLaBilletera(){
        billeteraVirtual.notificarResultado(pedido);

        verify(api).notificar(pedido);
    }


    // Test procesarPago() heredado de MedioDePago


    @Test
    void seProcesaUnPagoCorrectamente(){
        // Pruebo el template
        billeteraVirtual.procesarPago(pedido);

        // Verifico interacciones con la api
        verify(api).validarSaldo();
        verify(api).bloquearSaldo();
        verify(api).acreditar();
        verify(api).notificar(pedido);
    }

    @Test
    void cuandoNoSeProcesaUnPagoLaApiNoHaceNadaMas(){
        // Configuro el mock para que falle
        doThrow(new MedioDePagoException("Los datos no son válidos")).when(api).validarSaldo();

        // Pruebo el template con el caso que falla
        assertThrows(MedioDePagoException.class,
                () -> billeteraVirtual.procesarPago(pedido));

        // Verifico no mas interacciones con la api
        verify(api, never()).bloquearSaldo();
        verify(api, never()).acreditar();
        verify(api, never()).notificar(pedido);
    }
}
