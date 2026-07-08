package pedido.estadoPedido;

import pedido.Pedido;
import subsistema.Subsistema;

public class Entregado extends EstadoPedido {

    public Entregado(){
        super();
    }

    @Override
    public void notificar(Pedido pedido, Subsistema subsistema) {
        subsistema.cambioAEntregado(pedido);
    }
}
