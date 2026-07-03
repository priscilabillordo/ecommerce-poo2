package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.EstadoPedido;

public interface Subsistema {

    void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido estadoNuevo);

}
