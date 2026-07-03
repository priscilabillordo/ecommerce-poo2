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
    /*
    * Cuando se actualiza un pedido, el estado sabe responder si su estado es "notificable" para Mail
    * */
        if (estadoNuevo.mandaMail()){
            mailSender.enviarMail("cliente@mail.com",
                    "cliente",
                    "El estado del pedido fue actualizado",
                    null);
        }
    }

}
