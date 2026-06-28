package ecommerce;

import pedido.Pedido;

public class NotaDeCredito {

    private double monto;
    private Pedido pedido;

    public NotaDeCredito (double monto, Pedido pedido){
        this.monto = monto;
        this.pedido = pedido;
    }
}
