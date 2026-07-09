package medioDePago.transferenciaBancaria;

import medioDePago.tarjetaDeCredito.CuponDePago;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransferenciaBancariaTest {

    private TransferenciaBancaria transferenciaBancaria;
    private APITransferencia api;
    private Pedido pedido;


    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        api    = mock(APITransferencia.class);
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

        verifyNoInteractions(api); // Porque no hace nada
    }

    @Test
    void seEjecutaUnaTransaccionDeLaCuenta(){
        transferenciaBancaria.ejecutarTransaccion();

        verify(api).transferir();
    }

    @Test
    void seGeneraUnComprobanteAlNotificarLaTransferencia(){
        transferenciaBancaria.notificarResultado(pedido);
        verify(pedido).registrarTransaccion(any(ComprobanteCBU.class));
    }
}
