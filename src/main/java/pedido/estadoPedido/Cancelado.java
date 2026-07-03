package pedido.estadoPedido;

public class Cancelado extends EstadoPedido {

    public Cancelado() {
        super();
    }

    public boolean generaCupon(){
        return true;
    }
}
