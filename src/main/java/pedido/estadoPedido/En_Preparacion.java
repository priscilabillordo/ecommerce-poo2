package pedido.estadoPedido;

import pedido.NotaDeCredito;
import pedido.Pedido;

public class En_Preparacion extends EstadoPedido {

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
