package envios.envio;

import envios.CorreoArgentino;

public class EnvioEstandar implements MetodoDeEnvio {
    private CorreoArgentino correoArgentino;

    public EnvioEstandar(CorreoArgentino correoArgentino) {
        this.correoArgentino = correoArgentino;
    }

    public double costoDeEnvio(double precio, double peso, String direccionEnvio) {
        return (double) correoArgentino.estimarEnvio((float) peso, direccionEnvio);
    }
}