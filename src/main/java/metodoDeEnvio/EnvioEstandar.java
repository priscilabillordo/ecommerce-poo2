package metodoDeEnvio;

import pedido.Pedido;

import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    public int estimacionDeDias(Pedido pedido) {
        return ThreadLocalRandom.current().nextInt(5, 8);
        /*
        * Devuelve un int aleatorio entre 5 y 7 (el 8 excluido)
        *  */
    }
}