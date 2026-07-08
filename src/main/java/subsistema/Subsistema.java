package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.EstadoPedido;

public interface Subsistema {
    void cambioAEntregado(Pedido pedido);

    void cambioAEnviado(Pedido pedido);

    void cambioACancelado(Pedido pedido);

    void cambioAConfirmado(Pedido pedido);
}
