package envios.envio;

import envios.EnvioExpressI;

public class EnvioExpress implements MetodoDeEnvio {
    private EnvioExpressI envioExpressI;

    public EnvioExpress(EnvioExpressI envioExpressI) {
        this.envioExpressI = envioExpressI;
    }

    public float costoDeEnvio(float precio, float peso, String direccionEnvio) {
        return envioExpressI.calcularCosto(precio);
    }
}