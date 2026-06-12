package envios.envio;

import envios.CorreoArgentino;

public class EnvioEstandar implements MetodoDeEnvio {
    private CorreoArgentino correoArgentino;

    public EnvioEstandar(CorreoArgentino correoArgentino) {
        this.correoArgentino = correoArgentino;
    }

    public float costoDeEnvio(float precioTotalPedido, float peso, String direccionEnvio) {
        //Direccion
        return correoArgentino.estimarEnvio(peso, direccionEnvio);
    }
}