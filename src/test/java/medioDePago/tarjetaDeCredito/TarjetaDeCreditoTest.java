package medioDePago.tarjetaDeCredito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TarjetaDeCreditoTest {

    private TarjetaDeCredito tarjeta;
    private APITarjetaCredito api;

    @BeforeEach
    void setUp(){
        api = mock(APITarjetaCredito.class);
        tarjeta = new TarjetaDeCredito("1234-5678-7654-3210", "100", LocalDate.of(2030,6,29), api);
    }

    @Test
    void seChequeaLaAsignacionDeDatosDeLaTarjeta(){
        assertEquals("1234-5678-7654-3210", tarjeta.getNumeroDeTarjeta());
        assertEquals("100", tarjeta.getCvv());
        assertEquals(LocalDate.of(2030,6,29), tarjeta.getVencimiento());
        assertEquals(api, tarjeta.getApi());
    }

    @Test
    void seValidanLosDatosDeLaTarjeta(){
        tarjeta.validarDatos();

        verify(api).validarTarjeta();
    }

    @Test
    void seReservanFondosDeLaTarjeta(){
        tarjeta.reservarFondos();

        verify(api).preautorizar();
    }

    @Test
    void seEjecutaLaTransanccionDeLaTarjeta(){
        tarjeta.ejecutarTransaccion();

        verify(api).transferir();
    }

    @Test
    void seNotificaElResultadoDeUnaTransferencia(){
        tarjeta.notificarResultado();

        verify(api).generarCupon();
    }

    // testear casos donde pueda romper?
}
