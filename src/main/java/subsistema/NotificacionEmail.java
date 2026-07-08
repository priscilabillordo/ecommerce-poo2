package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.Confirmado;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.Enviado;
import pedido.estadoPedido.EstadoPedido;

public class NotificacionEmail implements Subsistema {
    private MailSender mailSender;

    public NotificacionEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    private void mandarMail(String cambioDeEstado) {
        mailSender.enviarMail("cliente@mail.com",
                "cliente",
                "El pedido fue " + cambioDeEstado,
                null);
    }

    @Override
    public void cambioAConfirmado(Pedido pedido) {
        this.mandarMail("confirmado");
    }

    @Override
    public void cambioAEnviado(Pedido pedido) {
        this.mandarMail("enviado");
    }

    @Override
    public void cambioAEntregado(Pedido pedido) {
        this.mandarMail("entregado");
    }

    @Override
    public void cambioACancelado(Pedido pedido) { }
}
