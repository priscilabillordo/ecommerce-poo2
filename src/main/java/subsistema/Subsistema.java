package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.EstadoPedido;

public interface Subsistema {
    void cambioAConfirmado(Pedido pedido);
    void cambioAEnviado(Pedido pedido);
    void cambioAEntregado(Pedido pedido);
    void cambioACancelado(Pedido pedido);
}
