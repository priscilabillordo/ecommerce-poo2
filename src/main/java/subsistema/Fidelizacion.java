package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.Cancelado;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.EstadoPedido;

public class Fidelizacion implements Subsistema {
    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido estadoNuevo) {
        if (estadoNuevo.generaCupon()) {
            this.envioMensaje("cliente@mail.com");
        }
    }

    private void envioMensaje(String emailCliente) {
        System.out.printf(
                "Cliente %s: tiene un cupón de descuento del 5 porciento",
                emailCliente
        );
    }
}
