package medioDePago.tarjetaDeCredito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TarjetaDeCreditoTest {

    private TarjetaDeCredito tarjeta;
    private APITarjetaCredito api;
    private Pedido unPedido;

    @BeforeEach
    void setUp(){
        api = mock(APITarjetaCredito.class);
        unPedido = mock(Pedido.class);
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
    void seEjecutaLaTransaccionDeLaTarjeta(){
        tarjeta.ejecutarTransaccion();

        verify(api).transferir();
    }

    @Test
    void cuandoSeNotificaElResultadoDeUnaTransaccionSeGeneraUnCupon(){
        tarjeta.notificarResultado(unPedido);

        assertThat(tarjeta.getCupon()).isInstanceOf(CuponDePago.class);
    }
}
