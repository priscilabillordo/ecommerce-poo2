package medioDePago;

import exceptions.MedioDePagoException;
import lombok.Getter;
import pedido.Pedido;
import java.util.UUID;

@Getter
public abstract class MedioDePago {

    protected String codigoTransaccion;

    public final void procesarPago(Pedido pedido){
            this.validarDatos();
            this.reservarFondos();
            this.ejecutarTransaccion();
            this.notificarResultado(pedido);
    }

    protected void generarCodigoTransaccion(){
        this.codigoTransaccion = UUID.randomUUID().toString();
    }


    /*
    * Metodo por defecto donde se registra un codigo de transaccion.
    * TransferenciaBancaria y TarjetaDeCredito hace un @Override
    * Ademas, se registra este codigo en el pedido
    * */
    public void notificarResultado(Pedido pedido){
        this.generarCodigoTransaccion();
        pedido.registrarTransaccion(this.codigoTransaccion);
    }

    /*
    * Un pedido registra el codigo de transaccion, la idea era que registre el comprobante
    * pero solo Transferencia y Tarjeta tienen Comprobante, Billetera NO
    * */

    /*
    * Metodos abstractos que cada medio de pago implementa
    * */

    public abstract void validarDatos() throws MedioDePagoException;
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
}
