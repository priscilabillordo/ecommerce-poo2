package pedido.estadoPedido;


import item.Item;
import pedido.Pedido;

public abstract class EstadoPedido {

    public void cargarItem(Item item, Pedido pedido){
        // todo: el unico que hace esto es Borrador
    }

    public void quitarItem(Item item, Pedido pedido){
        // todo: el unico que hace esto es Borrador
    }

    public void confirmarPedido(Pedido pedido){
        // todo: lo hacen Borrador
    }
    public void cancelarPedido(Pedido pedido){
        // todo: lo hacen Borrador, Confirmado, En_Preparacion, Enviado
    }
    public void prepararPedido(Pedido pedido){
        // todo: solo lo hace Confirmado
    }

    public void enviarPedido(Pedido pedido){
        // todo: solo lo hace EnPreparacion
    }

    public void entregarPedido(Pedido pedido){
        // todo: solo lo hace Enviado
    }

    public void reembolsar(Pedido pedido){
        // todo: solo lo hace En_Preparacion y Enviado al cancelarlo
    }

}
