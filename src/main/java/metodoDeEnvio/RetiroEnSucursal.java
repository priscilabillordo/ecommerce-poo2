package metodoDeEnvio;

import lombok.Getter;
import pedido.Pedido;

@Getter
public class RetiroEnSucursal implements MetodoDeEnvio {

    private Sucursal sucursal;

    public RetiroEnSucursal(Sucursal sucursal){
        this.sucursal = sucursal;
    }

    @Override
    public double costoDeEnvio(Pedido pedido) {
        return 0;
    }

    @Override
    public int estimacionDeDias(Pedido pedido) {
        return this.sucursal.hayStock(pedido) ? 0 : 3;
    }
}


