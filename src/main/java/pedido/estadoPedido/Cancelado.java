package pedido.estadoPedido;

import pedido.Pedido;
import subsistema.Subsistema;

public class Cancelado extends EstadoPedido {

    public Cancelado() {
        super();
    }

    @Override
    public void notificar(Pedido pedido, Subsistema subsistema) {
        subsistema.cambioACancelado(pedido);
    }
}
