package medioDePago.transferenciaBancaria;

import exceptions.MedioDePagoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransferenciaBancariaTest {

    private TransferenciaBancaria transferenciaBancaria;
    private APITransferencia api;
    private Pedido unPedido;

    @BeforeEach
    void setUp(){
        unPedido = mock(Pedido.class);
        api = mock(APITransferencia.class);
        transferenciaBancaria = new TransferenciaBancaria("1234567891011121314151","priscilaCuenta", api);
    }

    @Test
    void seInicializaUnaCuentaParaTransferirCorrectamente(){
        assertEquals("1234567891011121314151", transferenciaBancaria.getCbu());
        assertEquals("priscilaCuenta", transferenciaBancaria.getAlias());
        assertEquals(api, transferenciaBancaria.getApi());
    }

    @Test
    void seValidanLosDatosDeLaCuentaATransferir(){
        transferenciaBancaria.validarDatos();

        verify(api).validarCuenta();
    }

    @Test
    void seReservanLosFondosDeLaCuentaATransferir(){
        transferenciaBancaria.reservarFondos();

        verifyNoInteractions(api);
    }

    @Test
    void seEjecutaUnaTransaccionDeLaCuenta(){
        transferenciaBancaria.ejecutarTransaccion();

        verify(api).transferir();
    }

    @Test
    void seGeneraUnComprobanteAlNotificarLaTransferencia(){
        transferenciaBancaria.notificarResultado(unPedido);

        verify(unPedido).registrarTransaccion(anyString());
        assertThat(transferenciaBancaria.getCodigoTransaccion()).isNotNull();
        assertThat(transferenciaBancaria.getComprobanteCBU()).isNotNull();
    }

    @Test
    void seVerificaQueCuandoUnaTransferenciaNoPuedeValidarSusDatos_Falla() {
        // Configuro el mock para que falle
        doThrow(new MedioDePagoException("Los datos no son válidos")).when(api).validarCuenta();

        assertThrows(
                MedioDePagoException.class,
                () -> transferenciaBancaria.validarDatos()
        );
    }


}
