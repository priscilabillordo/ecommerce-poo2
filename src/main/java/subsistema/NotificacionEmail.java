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

    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido estadoNuevo) {
        if (this.requiereNotificar(estadoNuevo)){
            mailSender.enviarMail("cliente@mail.com",
                    "cliente",
                    "El estado del pedido fue actualizado",
                    null);
        }
    }

    @Override
    public boolean requiereNotificar(EstadoPedido estado) {
        return  estado instanceof Confirmado ||
                estado instanceof Enviado    ||
                estado instanceof Entregado;
    }
}
