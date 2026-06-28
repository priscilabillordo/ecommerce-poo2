package metodoDeEnvio;

import pedido.Pedido;

public interface MetodoDeEnvio {
    public double costoDeEnvio(Pedido pedido);
}