package medioDePago;

import exceptions.MedioDePagoException;
import lombok.Getter;
import pedido.Pedido;

import java.util.UUID;

@Getter
public abstract class MedioDePago {

    private String codigoTransaccion;

    public final void procesarPago(Pedido pedido){
            this.validarDatos();
            this.reservarFondos();
            this.ejecutarTransaccion();
            this.notificarResultado(pedido);
    }

    protected void generarCodigoTransaccion(){
        this.codigoTransaccion = UUID.randomUUID().toString();
    }


    public void notificarResultado(Pedido pedido){
        this.generarCodigoTransaccion();
        pedido.registrarTransaccion(this.codigoTransaccion);
    }

    public abstract void validarDatos() throws MedioDePagoException;
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
}
