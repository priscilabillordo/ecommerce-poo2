package subsistema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.Enviado;
import pedido.estadoPedido.EstadoPedido;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GeneradorFacturaTest {
    private GeneradorFactura  generadorFactura;
    private ComprobanteFiscal comprobanteFiscal;
    private EstadoPedido estadoEntregado;
    private EstadoPedido estadoNoEntregado;
    private Pedido pedido;


    @BeforeEach
    void setUp() {
        comprobanteFiscal = mock(ComprobanteFiscal.class);
        generadorFactura  = new GeneradorFactura(comprobanteFiscal);
        estadoEntregado   = new Entregado();
        estadoNoEntregado = new Enviado();

        pedido = mock(Pedido.class);

        when(pedido.getFecha()).thenReturn(LocalDate.of(2026, 6, 28));

        when(pedido.costoTotal()).thenReturn(15000.0);
    }

    @Test
    void verifcarQueSeGeneraComprobanteFiscal() {
        generadorFactura.cambioAEntregado(pedido);

        verify(comprobanteFiscal).generarComprobante(pedido);
    }

    @Test
    void noSeGeneraComprobanteCuandoElPedidoSeEnvia() {
        generadorFactura.cambioAEnviado(pedido);
        verifyNoInteractions(comprobanteFiscal);
    }

    @Test
    void noSeGeneraComprobanteCuandoElPedidoSeCancela() {
        generadorFactura.cambioACancelado(pedido);
        verifyNoInteractions(comprobanteFiscal);
    }

    @Test
    void noSeGeneraComprobanteCuandoElPedidoSeConfirma() {
        generadorFactura.cambioAConfirmado(pedido);
        verifyNoInteractions(comprobanteFiscal);
    }
}
