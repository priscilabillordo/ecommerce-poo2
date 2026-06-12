package envios.envio;

import envios.EnvioExpressI;

public class EnvioExpress implements MetodoDeEnvio {
    private EnvioExpressI envioExpressI;

    public EnvioExpress(EnvioExpressI envioExpressI) {
        this.envioExpressI = envioExpressI;
    }

    public double costoDeEnvio(double precio, double peso, String direccionEnvio) {
        return (double) envioExpressI.calcularCosto((float) precio);
    }
}