package subsistema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.estadoPedido.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NotificacionEmailTest {
    private NotificacionEmail notificacionEmail;
    private MailSender mailSender;
    private Pedido pedido;

    private EstadoPedido estadoConfirmado;
    private EstadoPedido estadoEnviado;
    private EstadoPedido estadoEntregado;
    private EstadoPedido estadoNoNotificable;

    @BeforeEach
    void setUp() {
        mailSender = mock(MailSender.class);
        pedido     = mock(Pedido.class);
        notificacionEmail = new NotificacionEmail(mailSender);

        estadoConfirmado = new Confirmado();
        estadoEnviado    = new Enviado();
        estadoEntregado  = new Entregado();

        estadoNoNotificable = new Borrador();
    }

    @Test
    void vefircarQueSeMandaUnMailCuandoCambioAConfirmado() {
        notificacionEmail.cambioAConfirmado(pedido);

        verify(mailSender).enviarMail(
                "cliente@mail.com",
                "cliente",
                "El pedido fue confirmado",
                null);
    }

    @Test
    void vefircarQueSeMandaUnMailCuandoCambioAEnviado() {
        notificacionEmail.cambioAEnviado(pedido);

        verify(mailSender).enviarMail(
                "cliente@mail.com",
                "cliente",
                "El pedido fue enviado",
                null);
    }

    @Test
    void vefircarQueSeMandaUnMailCuandoCambioAEntregado() {
        notificacionEmail.cambioAEntregado(pedido);

        verify(mailSender).enviarMail(
                "cliente@mail.com",
                "cliente",
                "El pedido fue entregado",
                null);
    }

    @Test
    void noSeEnviaMailCuandoElPedidoSeCancela() {
        notificacionEmail.cambioACancelado(pedido);

        verifyNoInteractions(mailSender);
    }
}
