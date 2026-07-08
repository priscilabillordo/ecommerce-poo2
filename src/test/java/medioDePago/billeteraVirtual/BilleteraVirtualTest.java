package medioDePago.billeteraVirtual;

import exceptions.MedioDePagoException;
import exceptions.PedidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BilleteraVirtualTest {

    private BilleteraVirtual billeteraVirtual;
    private APIBilletera api;

    /*
    @BeforeEach
    void setUp(){
        api = mock(APIBilletera.class);
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
        billeteraVirtual.notificarResultado();

        verify(api).notificar();
    }

    /*
    * Test procesarPago() heredado de MedioDePago
    * */

    /*
    @Test
    void seProcesaUnPagoCorrectamente(){
        // Pruebo el template
        billeteraVirtual.procesarPago();

        // Verifico interacciones con la api
        verify(api).validarSaldo();
        verify(api).bloquearSaldo();
        verify(api).acreditar();
        verify(api).notificar();
    }

    @Test
    void cuandoNoSeProcesaUnPagoLaApiNoHaceNadaMas(){
        // Configuro el mock para que falle
        doThrow(new MedioDePagoException("Los datos no son válidos")).when(api).validarSaldo();

        // Pruebo el template con el caso que falla
        billeteraVirtual.procesarPago();

        // Verifico no mas interacciones con la api
        verify(api, never()).bloquearSaldo();
        verify(api, never()).acreditar();
        verify(api, never()).notificar();
    }
    */
}
