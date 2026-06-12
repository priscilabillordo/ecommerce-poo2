package pedido.estadoPedido;

import pedido.Pedido;

public class Confirmado extends EstadoPedido {

    @Override
    public void prepararPedido(Pedido pedido){
        pedido.setEstado(new En_Preparacion());
    }

    @Override
    public void cancelarPedido(Pedido pedido){
        pedido.setEstado(new Cancelado());
        pedido.reponerStock(); // consultar
    }

}
