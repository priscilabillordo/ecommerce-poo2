package pedido.estadoPedido;

import pedido.NotaDeCredito;
import pedido.Pedido;

public class Enviado extends EstadoPedido {

    @Override
    public void cancelarPedido(Pedido pedido){
        pedido.setEstado(new Cancelado());
        pedido.reembolsar(new NotaDeCredito(pedido.costoDeItems(), pedido));
    }

    @Override
    public void entregarPedido(Pedido pedido){
        pedido.setEstado(new Entregado());
    }
}
