package medioDePago;

import exceptions.MedioDePagoException;
import lombok.Getter;
import pedido.Pedido;
import java.util.UUID;

@Getter
public abstract class MedioDePago {

    private String codigoTransaccion;
    /*
    * Cada instancia de MedioDePago guarda un codigo de transaccion que es generado
    * cuando un pago es exitoso
    * */

    public final void procesarPago(Pedido pedido){
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado(pedido);
    }

    public void notificarResultado(Pedido pedido){
        this.codigoTransaccion = this.generarCodigo();
    }

    protected String generarCodigo(){
        return UUID.randomUUID().toString();
        // Genera un codigo aleatorio
    }


    /*
    * Metodos abstractos que cada medio de pago implementa
    * */

    public abstract void validarDatos() throws MedioDePagoException;
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
}
