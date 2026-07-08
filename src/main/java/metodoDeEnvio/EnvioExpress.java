package metodoDeEnvio;

import pedido.Pedido;

public class EnvioExpress implements MetodoDeEnvio {
    private EnvioExpressI envioExpressI;

    public EnvioExpress(EnvioExpressI envioExpressI) {
        this.envioExpressI = envioExpressI;
    }

    @Override
    public double costoDeEnvio(Pedido pedido) {
        float precio = (float) pedido.costoDeItems();
        return (double) envioExpressI.calcularCosto(precio);
    }

    @Override
    public int estimacionDeDias(Pedido pedido) {
        return 1;
    }
}