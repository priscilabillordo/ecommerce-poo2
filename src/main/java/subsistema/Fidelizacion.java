package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.Cancelado;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.EstadoPedido;

public class Fidelizacion implements Subsistema {
    private final Cupon cupon;

    public Fidelizacion(Cupon cupon) {
        this.cupon = cupon;
    }

    @Override
    public void cambioACancelado(Pedido pedido) {
        this.cupon.generarCupon(pedido);
    }

    @Override
    public void cambioAEnviado(Pedido pedido) { }

    @Override
    public void cambioAEntregado(Pedido pedido) { }

    @Override
    public void cambioAConfirmado(Pedido pedido) { }

}
