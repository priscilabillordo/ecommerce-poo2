package pedido.estadoPedido;

import ecommerce.NotaDeCredito;
import pedido.Pedido;

public class EnPreparacion extends EstadoPedido {

    @Override
    public void cancelarPedido(Pedido pedido){
        pedido.setEstado(new Cancelado());
        pedido.reponerStock();
        pedido.reembolsar(new NotaDeCredito(pedido.costo(), pedido));
    }

    @Override
    public void enviarPedido(Pedido pedido){
        pedido.setEstado(new Enviado());
    }
}
