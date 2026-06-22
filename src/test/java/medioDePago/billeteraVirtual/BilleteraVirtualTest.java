package medioDePago.billeteraVirtual;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BilleteraVirtualTest {

    private BilleteraVirtual billeteraVirtual;
    private APIBilletera api;

    @BeforeEach
    void setUp(){
        api = mock(APIBilletera.class);
        billeteraVirtual = new BilleteraVirtual(20000, api);
    }

    @Test
    void seChequeaLaAsignacionDeSaldoDeUnaBilletera(){
        assertEquals(20000, billeteraVirtual.getSaldo());
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


    // testear excepciones NO SE aun

}
