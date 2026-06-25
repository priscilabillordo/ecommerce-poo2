package pedido.estadoPedido;

import pedido.Pedido;

public class Confirmado extends EstadoPedido {

    @Override
    public void prepararPedido(Pedido pedido){
        pedido.setEstado(new EnPreparacion());
    }

    @Override
    public void cancelarPedido(Pedido pedido){
        pedido.setEstado(new Cancelado());
        pedido.reponerStock(); // consultar
    }

}
