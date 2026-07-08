package pedido.estadoPedido;

import pedido.Pedido;
import subsistema.Subsistema;

public class Confirmado extends EstadoPedido {

    @Override
    public void prepararPedido(Pedido pedido){
        pedido.setEstado(new EnPreparacion());
    }

    @Override
    public void cancelarPedido(Pedido pedido){
        pedido.setEstado(new Cancelado());
        pedido.reponerStock();
    }

    @Override
    public void notificar(Pedido pedido, Subsistema subsistema) {
        subsistema.cambioAConfirmado(pedido);
    }

}
