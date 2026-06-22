package metodoDeEnvio;

import pedido.Pedido;

public class EnvioEstandar implements MetodoDeEnvio {
    private CorreoArgentino correoArgentino;

    public EnvioEstandar(CorreoArgentino correoArgentino) {
        this.correoArgentino = correoArgentino;
    }

    @Override
    public double costoDeEnvio(Pedido pedido) {
        float  peso     = (float) pedido.peso();
        String dirEnvio = pedido.getDireccionEntrega();
        return (double) correoArgentino.estimarEnvio(peso, dirEnvio);
    }
}