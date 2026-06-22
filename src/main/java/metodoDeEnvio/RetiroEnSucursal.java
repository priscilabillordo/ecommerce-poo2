package metodoDeEnvio;

import pedido.Pedido;

public class RetiroEnSucursal implements MetodoDeEnvio {

    public double costoDeEnvio(Pedido pedido) {
        return 0;
    }
}


