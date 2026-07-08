package pedido.estadoPedido;


import exceptions.PedidoException;
import item.Item;
import pedido.Pedido;
import subsistema.Subsistema;

public abstract class EstadoPedido {

    public void cargarItem(Item item, Pedido pedido){
        // El unico estado que hace esto es Borrador
        throw new PedidoException("Operación inválida: No se puede cargar el item");
    }

    public void quitarItem(Item item, Pedido pedido){
        // El unico que hace esto es Borrador
        throw new PedidoException("Operación inválida: No se puede quitar el item");
    }

    public void confirmarPedido(Pedido pedido){
        throw new PedidoException("Operación inválida: El pedido no puede ser confirmado");
    }
    public void cancelarPedido(Pedido pedido){
        // Lo hacen Borrador, Confirmado, En_Preparacion, Enviado
        throw new PedidoException("Operación inválida: El pedido no puede ser cancelado");
    }
    public void prepararPedido(Pedido pedido){
        // Solo lo hace Confirmado
        throw new PedidoException("Operación inválida: El pedido no puede ser preparado");
    }

    public void enviarPedido(Pedido pedido){
        // Solo lo hace EnPreparacion
        throw new PedidoException("Operación inválida: El pedido no puede ser enviado");
    }

    public void entregarPedido(Pedido pedido){
        // Solo lo hace Enviado
        throw new PedidoException("Operación inválida: El pedido no puede ser entregado");
    }

    public void notificar(Pedido pedido, Subsistema subsistema) { }

}
