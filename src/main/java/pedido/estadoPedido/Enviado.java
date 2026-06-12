package pedido.estadoPedido;

import pedido.Pedido;

public class Enviado extends EstadoPedido {

    @Override
    public void cancelarPedido(Pedido pedido){
        pedido.setEstado(new Cancelado());
        pedido.reembolsar();
    }

    @Override
    public void entregarPedido(Pedido pedido){
        pedido.setEstado(new Entregado());
    }
}
