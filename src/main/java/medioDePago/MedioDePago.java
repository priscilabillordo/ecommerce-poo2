package medioDePago;

import exceptions.MedioDePagoException;

public abstract class MedioDePago {

    public final void procesarPago(){
        try {
            this.validarDatos();
            this.reservarFondos();
            this.ejecutarTransaccion();
            this.notificarResultado();
        } catch (MedioDePagoException e){
            System.out.println("No se pudo procesar el pago");
        }
    }

    public abstract void validarDatos() throws MedioDePagoException;
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
    public abstract void notificarResultado();
}
