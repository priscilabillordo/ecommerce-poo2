package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.Cancelado;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.EstadoPedido;

public class Fidelizacion implements Subsistema {
    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido estadoNuevo) {
        if (this.requiereNotificar(estadoNuevo)) {
            this.envioMensaje("cliente@mail.com");
        }
    }

    @Override
    public boolean requiereNotificar(EstadoPedido estado) {
        return estado instanceof Cancelado;
    }

    private void envioMensaje(String emailCliente) {
        System.out.printf(
                "Cliente: tiene un cupón de descuento del 5%",
                emailCliente
        );
    }
}
