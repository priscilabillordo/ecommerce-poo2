package subsistema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.estadoPedido.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
    void verificaQueElEstadoEsConfirmadoDeNotificacion() {
        notificacionEmail.actualizar(pedido, estadoNoNotificable, estadoConfirmado);
        assertTrue(notificacionEmail.requiereNotificar(estadoConfirmado));
        verify(mailSender).enviarMail("cliente@mail.com",
                "cliente",
                "El estado del pedido fue actualizado",
                null);
    }


    @Test
    void verificaQueElEstadoEsEnviadoDeNotificacion() {
        notificacionEmail.actualizar(pedido, estadoNoNotificable, estadoEnviado);
        assertTrue(notificacionEmail.requiereNotificar(estadoEnviado));
        verify(mailSender).enviarMail("cliente@mail.com",
                "cliente",
                "El estado del pedido fue actualizado",
                null);
    }


    @Test
    void verificaQueElEstadoEsEntregadoDeNotificacion() {
        notificacionEmail.actualizar(pedido, estadoNoNotificable, estadoEntregado);
        assertTrue(notificacionEmail.requiereNotificar(estadoEntregado));
        verify(mailSender).enviarMail("cliente@mail.com",
                "cliente",
                "El estado del pedido fue actualizado",
                null);
    }


    @Test
    void verificaQueElEstadoNoCorrespondeDeNotificacion() {
        notificacionEmail.actualizar(pedido, estadoNoNotificable, estadoNoNotificable);
        assertFalse(notificacionEmail.requiereNotificar(estadoNoNotificable));
    }
}
