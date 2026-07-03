package pedido.estadoPedido;

public class Entregado extends EstadoPedido {

    public Entregado(){
        super();
    }

    public boolean mandaMail(){
        return true;
    }

    public boolean generaComprobante(){
        return true;
    }
}
